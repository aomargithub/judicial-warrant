package com.informatique.gov.judicialwarrant.utils;

public class RandomPasswordUtil {

	public static String generate(int length) {
		
		
		Long password = new Double(Math.random()*Math.pow(10, length)).longValue();
	       
	       return password.toString();
	    }
}
