/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HK
 */
public class JwtApp {

    private final java.sql.Timestamp curent_Date = new java.sql.Timestamp(new java.util.Date().getTime());
    private final String SECRET_KEY = curent_Date.toString();
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    
    public String generateJWT(String cardNo) throws Exception {
        String payload = "{\"card_no\":\"" + cardNo + "\"}";
        String PART1 = doBASE64(JWT_HEADER);
        String PART2 = doBASE64(payload);
        String PART1_PART2 = PART1 + "." + PART2;
        String PART3 = doBASE64(doHMACSHA256(PART1_PART2, SECRET_KEY));
        String JWT_TOKEN = PART1_PART2 + "." + PART3;
        return JWT_TOKEN;
    }

    private String doHMACSHA256(String part1AndPart2, String secretKey) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
        byte[] hashBytes = mac.doFinal(part1AndPart2.getBytes());
        String hash = doBASE64(hashBytes);
        return hash;
    }

    private static String doBASE64(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        String base64 = encoder.encodeToString(bytes);
        return base64;
    }

    private String doBASE64(String input) {
        byte[] bytes = input.getBytes(Charset.forName("UTF-8"));
        String base64 = doBASE64(bytes);
        return base64;
    }

    private String decodeBASE64(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString));
    }

    public String getValue(String jwt) throws JSONException {
        String[] parts = jwt.split("\\.");
        String PART2 = parts[1];
        JSONObject json = new JSONObject(decodeBASE64(PART2));
        String carno = json.getString("card_no");
        return carno;
    }

    public boolean validateJWT(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");
        String PART1 = parts[0];
        String PART2 = parts[1];
        String PART3 = parts[2];
        String PART1_PART2 = PART1 + "." + PART2;
        String jwtSignature = doBASE64(doHMACSHA256(PART1_PART2, SECRET_KEY));
        return jwtSignature.equals(PART3);
    }
}
