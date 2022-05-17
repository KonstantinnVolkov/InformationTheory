package com.example.lr_1.encoders;

public class VigenereCipher {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static String encrypt (String textToEncode, String key){
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;
        int keyLength = key.length();
        for (char c : textToEncode.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                encryptedText.append(ALPHABET.charAt( ( ALPHABET.indexOf(c) + (ALPHABET.indexOf(key.charAt(keyIndex)))) % ALPHABET.length()));
                keyIndex++;
                if (keyIndex % key.length() == 0) {
                    keyIndex = 0;
                    StringBuilder keyTemp = new StringBuilder(key);
                    key = "";
                    for (int i = 0; i < keyLength; i++) {
                        key += ALPHABET.charAt((ALPHABET.indexOf(keyTemp.charAt(i)) + 1) % 33);
                    }
                }
            }
            else
                encryptedText.append(c);
        }
        return encryptedText.toString();
    }


    public static String decrypt (String textToDecode, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;
        int keyLength = key.length();
        for (char c : textToDecode.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                int index;
                int var = (ALPHABET.indexOf(c) - (ALPHABET.indexOf(key.charAt(keyIndex)))) % ALPHABET.length();
                if (var < 0) {
                    index = ALPHABET.length() - Math.abs(var);
                }
                else {
                    index = var;
                }
                decryptedText.append(ALPHABET.charAt(index));
                keyIndex++;
                if (keyIndex % key.length() == 0) {
                    keyIndex = 0;
                    StringBuilder keyTemp = new StringBuilder(key);
                    key = "";
                    for (int i = 0; i < keyLength; i++) {
                        key += ALPHABET.charAt((ALPHABET.indexOf(keyTemp.charAt(i)) + 1) % ALPHABET.length());
                    }
                }
            }
            else decryptedText.append(c);
        }
        return decryptedText.toString();
    }
}
