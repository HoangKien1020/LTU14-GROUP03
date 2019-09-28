package Server;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    public String encrypt(String password){
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hashed;
    }

    public Boolean unencrypt(String candidate, String hashed){
        if (BCrypt.checkpw(candidate, hashed))
            return true;
        else
            return false;
    }
}
