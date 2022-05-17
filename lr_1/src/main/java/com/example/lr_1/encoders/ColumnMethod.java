package com.example.lr_1.encoders;

public class ColumnMethod {

    public static String encrypt(String textToEncode, String encryptionKey){
        StringBuilder msg = new StringBuilder(textToEncode.replace(" ", ""));

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(encryptionKey);

        // in case characters don't fit the entire grid perfectly.
        int extraLetters = msg.length() % encryptionKey.length();
        //System.out.println(extraLetters);
        int dummyCharacters = encryptionKey.length() - extraLetters;
//        System.out.println(dummyCharacters);

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                msg.append(".");
            }
        }

        int numOfRows = msg.length() / encryptionKey.length();

        // Converting message into a grid
        char[][] arr = new char[numOfRows][encryptionKey.length()];

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < encryptionKey.length(); j++) {
                arr[i][j] = msg.charAt(z);
                z++;
            }

        }

        // cipher text generation
        StringBuilder cipherText = new StringBuilder();
        String numLoc = getNumberLocation(encryptionKey, kywrdNumList);

        for (int i = 0, k = 0; i < numOfRows; i++, k++) {
            int d;
            if (k == encryptionKey.length()){
                break;
            } else {
                d = Character.getNumericValue(numLoc.charAt(k));
            }
            for (int j = 0; j < numOfRows; j++) {
                cipherText.append(arr[j][d]);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String textToDecode, String decryptionKey){
        int numOfRows = textToDecode.length() / decryptionKey.length();

        // array with dummy values
        char[][] arr = new char[numOfRows][decryptionKey.length()];

        // assigning numbers to keywords
        int[] kywrdNumList = keywordNumAssign(decryptionKey);

        String numLoc = getNumberLocation(decryptionKey, kywrdNumList);

        for (int i = 0, k = 0; i < textToDecode.length(); i++, k++) {
            int d = 0;
            if (k == decryptionKey.length()){
                k = 0;
            } else {
                d = Character.getNumericValue(numLoc.charAt(k));
            }

            for (int j = 0; j < numOfRows; j++, i++) {
                arr[j][d] = textToDecode.charAt(i);
            } // for loop
            --i;
        }

        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < decryptionKey.length(); j++) {
                plainText.append(arr[i][j]);
            } // inner for loop
        } // for loop

        return plainText.toString();

    } // decryption function

    private static String getNumberLocation(String keyword, int[] kywrdNumList) {
        String numLoc = "";
        for (int i = 1; i < keyword.length() + 1; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (kywrdNumList[j] == i){
                    numLoc += j;
                }
            }
        }
        return numLoc;
    } // getting number location function

    private static int[] keywordNumAssign(String keyword) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] kywrdNumList = new int[keyword.length()];

        int init = 0;
        for (int i = 0; i < alpha.length(); i ++){
            for (int j = 0; j < keyword.length(); j++) {
                if (alpha.charAt(i) == keyword.charAt(j)){
                    init++;
                    kywrdNumList[j] = init;
                }
            } // inner for
        } // for
        return kywrdNumList;
    } // keyword number assignment function


}
