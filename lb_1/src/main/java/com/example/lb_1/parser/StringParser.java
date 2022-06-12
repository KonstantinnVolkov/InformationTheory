package com.example.lb_1.parser;

public class StringParser {

    static final String forbiddenSymbolsGeneral = "\n!@#№$;%^:&?*()_+=|/\\\"'<>{}[]~`"
            + 1234567890;
    static final String forbiddenSymbolsForEn = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + forbiddenSymbolsGeneral;
    static final String forbiddenSymbolsForRu = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + forbiddenSymbolsGeneral;
    static final String forbiddenSymbolsForNum =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public static String deleteRusSymbols(String str) {
        StringBuilder stringToParse = new StringBuilder(str.toLowerCase());
        System.out.println("String to parse: " + stringToParse);
        for (int i = 0; i < stringToParse.length(); i++) {
            if (forbiddenSymbolsForEn.contains(String.valueOf(stringToParse.charAt(i)))) {
                stringToParse.deleteCharAt(i);
                i--;
            }
        }
        System.out.println("Parsed string: " + stringToParse);
        return new String(stringToParse);
    }

    public static String deleteEngSymbols(String str) {
        StringBuilder stringToParse = new StringBuilder(str.toLowerCase());
        System.out.println("String to parse: " + stringToParse + "\n");
        for (int i = 0; i < stringToParse.length(); i++) {
            if (forbiddenSymbolsForRu.contains(String.valueOf(stringToParse.charAt(i)))) {
                stringToParse.deleteCharAt(i);
                i--;
            }
        }
        System.out.println("Parsed string: " + stringToParse);
        return new String(stringToParse);
    }

    public static int deleteSymbFromNum(String key) {
        StringBuilder stringToParse = new StringBuilder(key.toLowerCase());
        System.out.println("String to parse: " + stringToParse + "\n");
        for (int i = 0; i < stringToParse.length(); i++) {
            if (forbiddenSymbolsForNum.contains(String.valueOf(stringToParse.charAt(i)))) {
                stringToParse.deleteCharAt(i);
                i--;
            }
        }
        System.out.println("Parsed string: " + stringToParse);
        return Integer.parseInt(stringToParse.toString());


    }
}
