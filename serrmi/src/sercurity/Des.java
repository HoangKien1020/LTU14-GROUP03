package sercurity;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Des {
	static String SECRET_KEY = "dccan159";

	public static String decrypt(String encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] byteDecrypted = cipher.doFinal(extract(encrypted));
			String decrypted = new String(byteDecrypted);
			return decrypted;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] extract(String t) {
		int d = t.length() / 2;
		byte[] ret = new byte[d];
		for (int i = 0; i < d; i++) {
			ret[i] = mbyte(t.charAt(2 * i), t.charAt(2 * i + 1));
		}
		return ret;
	}

	private static byte mbyte(char d, char c) {
		// TODO Auto-generated method stub
		int a, b;
		if ((d >= '0') && (d <= '9')) {
			a = d - '0';
		} else {
			a = d - 'a' + 10;
		}

		if ((c >= '0') && (c <= '9')) {
			b = c - '0';
		} else {
			b = c - 'a' + 10;
		}
		// System.out.println(a * 16 + b);
		return (byte) (a * 16 + b);
	}

	public static String encrypt(String original) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] byteEncrypted = cipher.doFinal(original.getBytes());

			// String encrypted = Base64.getEncoder().encodeToString(byteEncrypted);
			StringBuilder sb = new StringBuilder();
			for (byte b : byteEncrypted) {
				sb.append(String.format("%02x", b));
			}
			// System.out.println(sb.toString().length());
			return sb.toString();
			// return encrypted;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}