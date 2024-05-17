package com.example.bai_tap_mahoa;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Data_Encryption {
    public static String encryptString(String data) {
        try {
            // Tạo thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Mã hóa String thành byte[]
            byte[] encodedHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            // Chuyển byte[] thành hex string
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

