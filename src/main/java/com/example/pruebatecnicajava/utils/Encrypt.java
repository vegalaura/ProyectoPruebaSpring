package com.example.pruebatecnicajava.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypt {
    public static String encriptarSHABase64(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(Encrypt.encriptarSHABase64("contraLaura"));
    }
}
