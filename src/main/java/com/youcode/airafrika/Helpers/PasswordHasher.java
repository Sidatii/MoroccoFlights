package com.youcode.airafrika.Helpers;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordHasher {

    public static String PasswordHash(String password) {
        String hash = null;
        try {
            if (Objects.equals(password, ""))
                throw new Exception("Password cannot be Null");
            String salt = BCrypt.gensalt();
            Objects.requireNonNull(salt, "Salt Cannot be Null");
            hash = BCrypt.hashpw(password, salt);
        }catch (Exception exception) {
            Logger.getLogger(PasswordHasher.class.getName()).log(Level.SEVERE, "An Error Occurred in PasswordHash Method", exception);
        }
        return hash;
    }

    public static boolean PasswordVerify(String password, String password_hash) {
        boolean isValid = false;
        try {
            if (Objects.equals(password, "") && Objects.equals(password_hash, ""))
                throw new Exception("Password and Password Hash cannot be Null");
            String salt = password_hash.substring(0, 29);
            Objects.requireNonNull(salt, "Salt cannot be Null");
            isValid = BCrypt.checkpw(password, password_hash);
        }catch (Exception exception) {
            Logger.getLogger(PasswordHasher.class.getName()).log(Level.SEVERE, "An Error Occurred With Password Verify Method", exception);
        }
        return isValid;
    }
}
