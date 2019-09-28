package Server;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

public class JWToken {

    public String signToken(String cardID) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            Date issuedAt = new Date();

            Calendar now = Calendar.getInstance();
            now.add(Calendar.MINUTE, 5);
            Date expiresAt = now.getTime();

            String signedToken = com.auth0.jwt.JWT.create()
                    .withClaim("cardID", cardID)
                    .withIssuedAt(issuedAt)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
            token = signedToken;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            System.out.println(exception);
            return false;
        }
    }
}
