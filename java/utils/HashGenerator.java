/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Admin
 */
public class HashGenerator {

    final protected static char[] hexArray = "0123456789ABCDEF"
            .toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

// Change this to something else.
    private static String SALT = "EYX*grX-C@^76E%a##E^KHP3Q9$YEqzYLp+eXAvE4+eX2s*tWg";

// A password hashing method.
    public static String hashPassword(String in) {
        String hashed = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes());        // <-- Prepend SALT.
            md.update(in.getBytes());

            byte[] out = md.digest();
            hashed = bytesToHex(out);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            return hashed;
        }
    }
}
