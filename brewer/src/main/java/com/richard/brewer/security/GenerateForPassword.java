package com.richard.brewer.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateForPassword {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}

}
