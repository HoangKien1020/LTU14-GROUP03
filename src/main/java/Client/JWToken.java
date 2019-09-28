package Client;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JWToken {


    public String getAccountID(String token) {
        String accountID = "";
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim accountIDClaim = jwt.getClaim("accountID");
            accountID = accountIDClaim.asString();
        } catch (JWTDecodeException exception){
            //Invalid token
            System.out.println(exception);
        }
        return accountID;
    }


}
