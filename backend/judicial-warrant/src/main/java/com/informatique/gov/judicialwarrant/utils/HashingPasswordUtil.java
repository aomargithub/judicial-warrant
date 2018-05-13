package com.informatique.gov.judicialwarrant.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;

public class HashingPasswordUtil {

	public static String hash(String originalString) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(
		  originalString.getBytes(StandardCharsets.UTF_8));
		String sha256hex = new String(Hex.encode(hash));
		return sha256hex;
	}
	
}
