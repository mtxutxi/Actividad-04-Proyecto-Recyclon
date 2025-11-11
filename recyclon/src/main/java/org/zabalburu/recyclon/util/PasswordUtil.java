package org.zabalburu.recyclon.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }

    public static void main(String[] args) { // prueba
        System.out.println(PasswordUtil.hashPassword("passw0rd")); // printea el hash de esta contraseña y se pega debajo (prueba)
        System.out.println(PasswordUtil.verifyPassword("passw0rd", "$2a$12$x7Ea5/2qRDvRy3Q9e.Oj2OmcXUFnK6BO8yQL3FEGcwu5Wbb5BcUMK"));

    }
}