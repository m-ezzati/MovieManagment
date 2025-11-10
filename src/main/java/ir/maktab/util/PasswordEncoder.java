package ir.maktab.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public final class
PasswordEncoder {

    private PasswordEncoder(){}

    private static final int COST = 12;

    public static String encode(String password){
        return BCrypt.withDefaults()
                .hashToString(COST, password.toCharArray());
    }

    public static boolean verify(String password, String hashPassword){
        return BCrypt.verifyer()
                .verify(password.toCharArray(), hashPassword.toCharArray())
                .verified;
    }}
