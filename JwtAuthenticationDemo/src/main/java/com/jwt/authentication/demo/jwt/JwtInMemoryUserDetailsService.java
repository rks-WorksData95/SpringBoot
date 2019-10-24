package com.jwt.authentication.demo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.authentication.demo.model.user;
import com.jwt.authentication.demo.model.userInformation;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	 @Autowired
	  user userRepo;
		
	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    
		userInformation userInfo = userRepo.findByUsername(username);
		
		System.out.println("User ::::::::::::::::::::=> "+userInfo);
		
		if(userInfo==null) {
	      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	    }

	    return new JwtUserDetails(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getRoles());
	    
	  }

}


