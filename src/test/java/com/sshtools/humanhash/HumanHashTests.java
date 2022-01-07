package com.sshtools.humanhash;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class HumanHashTests {

	@Test
	public void testMD5() throws UnsupportedEncodingException, NoSuchAlgorithmException {	
		testDigest(MessageDigest.getInstance("MD5"));
	}

	@Test
	public void testSHA1() throws UnsupportedEncodingException, NoSuchAlgorithmException {	
		testDigest(MessageDigest.getInstance("SHA1"));
	}
	
	@Test
	public void testSHA256() throws UnsupportedEncodingException, NoSuchAlgorithmException {	
		testDigest(MessageDigest.getInstance("SHA-256"));
	}
	
	@Test
	public void testSHA512() throws UnsupportedEncodingException, NoSuchAlgorithmException {	
		testDigest(MessageDigest.getInstance("SHA-512"));
	}
	
	private void testDigest(MessageDigest digest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		digest.update("Testing HumanHashGenerator output".getBytes("UTF-8"));
		
		byte[] output = digest.digest();
		String humanString = new HumanHashGenerator(output).build();
		String humanString2 = new HumanHashGenerator(output).build();
		
		System.out.println(String.format("Comparing %s to %s", humanString, humanString2));
		assertEquals("", humanString, humanString2);
	}
}
