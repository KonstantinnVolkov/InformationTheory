package com.example.lr_4.utils;

import java.math.BigInteger;

public class Hash {

    private static String RUS_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static String EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static BigInteger hashFunc (BigInteger r, String message) {
        message = message.toUpperCase();
        BigInteger hash = new BigInteger("100");
        for (int i = 0; i < message.length(); i++) {
            int index = message.charAt(i); //ASCII
            hash = hash.add(BigInteger.valueOf(index)); // Hi = (Hi-1 + index)^2 % r
            hash = hash.pow(2);
            hash = hash.mod(r);
        }
        System.out.println("Hash: " + hash);
        return hash;
    }
}
