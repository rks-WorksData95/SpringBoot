package com.jwt.authentication.demo.jwt.resource;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.demo.jwt.JwtTokenUtil;
import com.jwt.authentication.demo.jwt.JwtUserDetails;
import com.jwt.authentication.demo.model.Message;
import com.jwt.authentication.demo.model.TokenExpirationMessage;
import com.jwt.authentication.demo.model.TokenVerification;
import com.jwt.authentication.demo.model.TokenVerificationReposetory;
import com.jwt.authentication.demo.model.user;
import com.jwt.authentication.demo.model.userInformation;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController {

  public final int tokenExpirationDay = 0;
  public final int tokenExpirationHours = 23;
  public final int tokenExpirationMinutes = 59;
  public final int tokenExpirationSeconds = 59;
	
  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  user userRepo;
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService;
  
  @Autowired
  TokenVerificationReposetory tokenVerificationRepo;

  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {

	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	  
	userInformation user = userRepo.findByUsername(authenticationRequest.getUsername());
	
	TokenVerification userTokenDetails =  tokenVerificationRepo.findByUserId(user.getId());
	
	System.out.println("Created Date ::::::::=> "+userTokenDetails.getCreatedDate());

	// find duration between two dates
//	Period period = calculateDuration(userTokenDetails.getCreatedDate());
//	System.out.println("Date Time ::::::::=> "+(tokenExpirationDay - period.getDays())+" Days "+(tokenExpirationHours - period.getHours())+" Hours "+(tokenExpirationMinutes - period.getMinutes())+" Minutes "+(tokenExpirationSeconds - period.getSeconds())+" Seconds");
//	System.out.println("Token Expired Remaining Time ::::::::=> "+(tokenExpirationHours - period.getHours())+" Hours "+(tokenExpirationMinutes - period.getMinutes())+" Minutes "+(tokenExpirationSeconds - period.getSeconds())+" Seconds");
	
	DateTime expiredDate = new DateTime(userTokenDetails.getCreatedDate());
	expiredDate = expiredDate.plusHours(1);
	
	System.out.println("Created Date : "+userTokenDetails.getCreatedDate());
	System.out.println("Expired Date : "+expiredDate.toDate());
	
	//Find total days, hours, minutes and seconds of two dates
//	Days days = Days.daysBetween(createdDate, currentDate);	
//	Hours hours = Hours.hoursBetween(createdDate, currentDate);
//	Minutes minutes = Minutes.minutesBetween(createdDate, currentDate);
//	Seconds seconds = Seconds.secondsBetween(createdDate, currentDate);
	
//	System.out.println("Date Time ::::::::=> "+days.getDays()+" "+hours.getHours()+" "+minutes.getMinutes()+" "+seconds.getSeconds());

//	if((tokenExpirationDay - period.getDays()) < 0 || (tokenExpirationHours - period.getHours()) < 0 || (tokenExpirationMinutes - period.getMinutes()) < 0 || (tokenExpirationSeconds - period.getSeconds()) < 0) {
//	return new ResponseEntity<Message>(new Message("SignIn failed! Your account activation token has expired."), HttpStatus.FORBIDDEN);
	
//	if(!new Date().before(expiredDate.toDate())) {
//		return new ResponseEntity<Message>(new Message("SignIn failed! Your account activation token has expired."), HttpStatus.FORBIDDEN);
//	}else {
//		if(user.getIsEnabled() == true) {
//			
//		    final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//		    
//		    final String token = jwtTokenUtil.generateToken(userDetails);
//	
//		    return ResponseEntity.ok(new JwtTokenResponse(token));
//		}else {
//			return new ResponseEntity<Message>(new Message(""+expiredDate), HttpStatus.FORBIDDEN);
//		}
//	} 
	
	if(user.getIsEnabled() == true) {
		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtTokenResponse(token));
	}else {
		if(!new Date().before(expiredDate.toDate())) {
			return new ResponseEntity<Message>(new Message("SignIn failed! Your account activation token has expired;generateToken"), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<TokenExpirationMessage>(new TokenExpirationMessage(expiredDate.toString(), "SignIn failed! Account is not activated, Please activate your account. Your activation token will expire in"), HttpStatus.FORBIDDEN);
		}
	}
  }
  
  //calculate expiration times of user account activation token
  public Period calculateDuration(Date tokenCreatedDate) {
	  DateTime createdDate = new DateTime(tokenCreatedDate);
	  DateTime currentDate = new DateTime();
	  System.out.println("createdDate ::::::::=> "+createdDate);
	  System.out.println("currentDate ::::::::=> "+currentDate);
	  Interval interval = new Interval(createdDate, currentDate);
	  return interval.toPeriod();
  }

  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("User Disabled", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Invalid Credentials", e);
    }
  }
}

