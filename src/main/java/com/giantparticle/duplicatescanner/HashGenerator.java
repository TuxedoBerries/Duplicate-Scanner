/**
 * Author:	Juan Silva <juan.silva@giantparticle.com>
 * Date:	September 09, 2016
 * Copyright (c) Giant Particle All rights reserved.
 */
package com.giantparticle.duplicatescanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;

/**
 *	Generate MD5 from a file.
 */
public class HashGenerator {

	private byte[] buffer;
	private MessageDigest md;

	/**
	 * Main constructor
	 */
	public HashGenerator() {
		buffer = new byte[4096];
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException exception) {
			Logger.getGlobal().severe("No such algorithm exception");
		}
	}

	/**
	 * Generate MD5 byte array hash from the given file path.
	 * @param stringPath File to read to generate the MD5 hash.
	 * @return MD5 byte array hash.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public byte[] GenerateMD5(String stringPath) throws FileNotFoundException, IOException {
		md.reset();

		FileInputStream fis = new FileInputStream(stringPath);
		int bytesRead = 0;
		while ((bytesRead = fis.read(buffer)) != -1) {
			md.update(buffer, 0, bytesRead);
		}
		fis.close();

		return md.digest();
	}

	/**
	 * Generate MD5 string hash from the given file path.
	 * @param stringPath File to read to generate the MD5 hash.
	 * @return MD5 string hash.
	 */
	public String GenerateMD5String(String stringPath) {
		byte[] bytes = null;
		try {
			bytes = GenerateMD5(stringPath);
		} catch (FileNotFoundException fException) {
			Logger.getGlobal().severe(String.format("File not found exception: %s", stringPath));
		} catch (IOException ioException) {
			Logger.getGlobal().severe(String.format("IO exception: %s", stringPath));
		}
		if (bytes == null)
			return null;

		return Hex.encodeHexString(bytes);
	}
}
