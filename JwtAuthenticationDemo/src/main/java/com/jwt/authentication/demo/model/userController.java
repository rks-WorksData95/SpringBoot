package com.jwt.authentication.demo.model;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.demo.jwt.resource.JwtTokenRequest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class userController {

	@Autowired
	user userRepo;
	
	@Autowired
	roleReposetory roleRepo;
	
	@Autowired
	TokenVerificationReposetory tokenVerificationRepo;
	
	@Autowired
	PasswordResetTokenReposetory passwordResetTokenRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@PostMapping(path = "/forgotPassword")
	public ResponseEntity<Message> forgotPassword(@RequestParam("userEmail") String userEmail) throws MessagingException, IOException{
		System.out.println("Forgot Password Recovery Email Id : "+userEmail);
		
		userInformation forgotpasswordUser = userRepo.findByEmailId(userEmail);
		
		System.out.println("Forgot Password User : "+forgotpasswordUser);
		
		if(forgotpasswordUser == null) {
			return new ResponseEntity<Message>(new Message("We could not find an account for that e-mail adrress."), HttpStatus.UNAUTHORIZED);
		}
		
		PasswordResetToken passwordResetTokenDetail = passwordResetTokenRepo.findByUserId(forgotpasswordUser.getId());
		System.out.println("Forgot Password Token Detail : "+passwordResetTokenDetail);
		
		if(passwordResetTokenDetail == null) {
			PasswordResetToken createNewPasswordResetToken = new PasswordResetToken(forgotpasswordUser);
			passwordResetTokenRepo.save(createNewPasswordResetToken);
			sendEmailForPasswordReset(createNewPasswordResetToken.getToken());
		}else {
			passwordResetTokenDetail.setCreatedDate(new Date());
			passwordResetTokenDetail.setToken(UUID.randomUUID().toString());
			PasswordResetToken updatedTokenDb = passwordResetTokenRepo.save(passwordResetTokenDetail);
			sendEmailForPasswordReset(updatedTokenDb.getToken());
		}
		
		return new ResponseEntity<Message>(new Message("You've successfully requested a new password reset!"), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/verifyPasswordResetToken")
	public ResponseEntity<Message> verifyPasswordResetToken(@RequestParam("token") String token){
		System.out.println("Password Reset Token : "+token);
		
		PasswordResetToken passwordResetTokenDetails = passwordResetTokenRepo.findByToken(token);
		System.out.println("Password Reset Token Details : "+passwordResetTokenDetails);
		
		if(passwordResetTokenDetails == null) {
			return new ResponseEntity<Message>(new Message("This reset password link is invalid or broken!"), HttpStatus.UNAUTHORIZED);	
		}
		
		DateTime passwordResetTokenExpiredDate = new DateTime(passwordResetTokenDetails.getCreatedDate());
		passwordResetTokenExpiredDate = passwordResetTokenExpiredDate.plusMinutes(5);
		
		if(new Date().after(passwordResetTokenExpiredDate.toDate())) {
			return new ResponseEntity<Message>(new Message("Oops! Your password reset token has expired."), HttpStatus.UNAUTHORIZED);
		}	
		
		return new ResponseEntity<Message>(new Message("Token Verify successfully!"), HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/resetPassword")
	public ResponseEntity<Message> resetPassword(@RequestParam("password") String password, @RequestParam("token") String token){
		System.out.println("New Password : "+password);
		System.out.println("Token : "+token);
		
		PasswordResetToken passwordResetTokenDetails = passwordResetTokenRepo.findByToken(token);
		
		if(passwordResetTokenDetails == null) {
			return new ResponseEntity<Message>(new Message("Error! This token is invalid or broken."), HttpStatus.UNAUTHORIZED);
		}
		
		userInformation resetPasswordUser = passwordResetTokenDetails.getUserInformation();
		
		System.out.println("Reset Password Token Details : "+passwordResetTokenDetails);
		System.out.println("Reset Password User Details : "+resetPasswordUser);
		
		resetPasswordUser.setPassword(passwordEncoder.encode(password));
		userInformation resetPasswordUpdatedDB = userRepo.save(resetPasswordUser);
		
		if(resetPasswordUpdatedDB == null) {
			return new ResponseEntity<Message>(new Message("Error! Your password not reset."), HttpStatus.BAD_REQUEST);
		}else {
			passwordResetTokenRepo.delete(passwordResetTokenDetails);
			return new ResponseEntity<Message>(new Message("Succes! Your new password reset successfully!"), HttpStatus.OK);
		}
	}
	
	@PostMapping(path = "/generateNewToken")
	public ResponseEntity<Message> generateNewToken(@RequestBody JwtTokenRequest authenticationRequest) throws MessagingException, IOException {
		System.out.println("Method Called:::::::::::::::::=> "+authenticationRequest.getUsername());
			
		userInformation user = userRepo.findByUsername(authenticationRequest.getUsername());
		TokenVerification tokenVerificatiom = tokenVerificationRepo.findByUserId(user.getId());
		tokenVerificatiom.setConfirmToken(UUID.randomUUID().toString());
		tokenVerificatiom.setCreatedDate(new Date());
		TokenVerification UpdatedTokenDetails = tokenVerificationRepo.save(tokenVerificatiom);
		System.out.println("token :::::::::=> "+tokenVerificatiom);
		if(UpdatedTokenDetails != null) {
			sendEmailWithAttachment(UpdatedTokenDetails.getConfirmToken());
			return new ResponseEntity<Message>(new Message("Your new token generated and activation link sent to your registed email."), HttpStatus.OK);
		}else {
			return new ResponseEntity<Message>(new Message("Request failed! Token not generated."), HttpStatus.OK);
		}
	}
	
	@PostMapping(path = "/varifyUserAccount")
	public ResponseEntity<Message> verifyUser(@RequestParam("token") String token){
		System.out.println("Token : "+token);
		TokenVerification tokenVerification = tokenVerificationRepo.findByConfirmToken(token);
		
		if(tokenVerification != null) {
			
			DateTime expiredDate = new DateTime(tokenVerification.getCreatedDate());
			expiredDate = expiredDate.plusHours(1);
			
			if(tokenVerification.getUserInformation().getIsEnabled() == true) {
				return new ResponseEntity<Message>(new Message("Your account is already activated."), HttpStatus.OK);
			}else {
				if(!new Date().before(expiredDate.toDate())) {
					return new ResponseEntity<Message>(new Message("Oops! Your account activation token has expired."), HttpStatus.UNAUTHORIZED);
				}else {
					userInformation verifiedUserInfo = userRepo.findByEmailId(tokenVerification.getUserInformation().getEmailId());
					verifiedUserInfo.setIsEnabled(true);
					userRepo.save(verifiedUserInfo);
					return new ResponseEntity<Message>(new Message("Your account has been activated and email is verified successfully!"), HttpStatus.OK);
				}
			}
		}else {
			return new ResponseEntity<Message>(new Message("The account activation link is invalid or broken!"), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(path = "/userInfo")
	public ResponseEntity<Message> userInfo(@RequestBody userInformation user) throws MessagingException, IOException{
		
		System.out.println("************ USERINFO ***************");
		
		if(userRepo.existsByUsername(user.getUsername())) {
			return new ResponseEntity<Message>(new Message("Username is already taken!"), HttpStatus.CONFLICT);
		}
		
		if(userRepo.existsByEmailId(user.getEmailId())) {	
			return new ResponseEntity<Message>(new Message("Email Address already in use!"), HttpStatus.CONFLICT);
		}
		
		Set<Role> roles= new HashSet<Role>();
		roles.add(roleRepo.findByRoleName("USER"));
		
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setIsEnabled(false);
		
		userInformation userDB = userRepo.save(user);
		
		if(userDB==null) {
			
			return new ResponseEntity<Message>(new Message("User Not Register Successfully!"), HttpStatus.BAD_REQUEST);
			
		}else {
			
//			SimpleMailMessage msg=new SimpleMailMessage();
//			msg.setTo("ramsadhu777@gmail.com");
//			msg.setSubject("Mail Testing");
//			msg.setText("Hello World \n Spring Boot Email");
//			javaMailSender.send(msg);
			
//			String token = UUID.randomUUID().toString();
		
			TokenVerification tokenVerificatiom = new TokenVerification(userDB);
			tokenVerificationRepo.save(tokenVerificatiom);
			
			sendEmailWithAttachment(tokenVerificatiom.getConfirmToken());
			
			return new ResponseEntity<Message>(new Message("User Register Successfully! account activation link has been sent to your registered email."), HttpStatus.OK);
			
		}
	}
	
	void sendEmailWithAttachment(String token) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("ramsadhu777@gmail.com");

        helper.setSubject("User Account Verification");
        
        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1> <br> <a href=\"http://localhost:8080/varifyUser/ramsadhu\">Activate Token</a>", true);
        helper.setText("<h1>User account verification using below activation link!</h1> <br>" + "<a href=\"http://localhost:4200/VerifyUserAccount/"+token+"\">Click on this link to Activate your account</a>", true);
        
//        FileSystemResource file = new FileSystemResource("D:\\Ram-CD\\FullStack-Angualr With Spring Boot\\STS-Workspace\\JwtAuthenticationDemo\\Images\\Penguins.jpg");
//        
//        helper.addAttachment("Penguins.jpg", file);

        javaMailSender.send(msg);

    }
	
	void sendEmailForPasswordReset(String token) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("ramsadhu777@gmail.com");

        helper.setSubject("User Password Reset");
        
        helper.setText("<h1>Reset password using below link!</h1> <br>" + "<a href=\"http://localhost:4200/ResetPassword/"+token+"\">Click on this link to reset your password</a>", true);
        
        javaMailSender.send(msg);

    }
	
}
