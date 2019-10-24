package com.cignex.microservices.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LimitConfiguration {
	
	private int maximum;
	private int minimum;
	
}
