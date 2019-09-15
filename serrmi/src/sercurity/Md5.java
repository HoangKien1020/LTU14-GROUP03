package sercurity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author TVD nguon gg
 */
public class Md5 {

	public static String hashStr(String text) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hashStr("dccan"));
	}

}