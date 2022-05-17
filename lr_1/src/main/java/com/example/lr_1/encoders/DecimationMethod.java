package com.example.lr_1.encoders;

public class DecimationMethod {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt (String textToEncode, int encryptionKey){
        StringBuilder encryptedStr = new StringBuilder();
            for (int i = 0; i < textToEncode.length(); i++) {
                if (textToEncode.charAt(i) == ' ' || textToEncode.charAt(i) == '\n' ) {
                    encryptedStr.append(' ');
                    continue;
                }
                encryptedStr.append(ALPHABET.charAt((ALPHABET.indexOf(textToEncode.charAt(i)) * encryptionKey) % ALPHABET.length()));
            }
            return encryptedStr.toString();
    }

    public static String decrypt (String textToDecode, int decryptionKey){
        StringBuilder decryptedStr = new StringBuilder();
            for (int i = 0; i < textToDecode.length(); i++) {
                if (textToDecode.charAt(i) == ' ' || textToDecode.charAt(i) == '\n' ) {
                    decryptedStr.append(' ');
                    continue;
                }
                else {
                    for (int j = 0; j < decryptionKey; j++) {
                        int offset = ALPHABET.indexOf(textToDecode.charAt(i)) + j * ALPHABET.length();
                        if (offset % decryptionKey == 0) {

                            decryptedStr.append(ALPHABET.charAt((offset / decryptionKey ) % ALPHABET.length()));
                            break;
                        }
                    }
                }
            }
            return decryptedStr.toString();
    }
}