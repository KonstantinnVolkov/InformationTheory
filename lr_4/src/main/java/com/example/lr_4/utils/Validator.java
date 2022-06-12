package com.example.lr_4.utils;

import java.math.BigInteger;

public class Validator {

    private final static String P_NOT_PRIME = "P is not prime!";
    private final static String Q_NOT_PRIME = "Q is not prime!";
    private final static String NOT_COPRIME = "Kc and EulerFunc aren't coprime!";
    private final static String INCORRECT_CLOSED_KEY = "Incorrect closed key!";

    private static boolean isPrime(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        if (n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
            return false;
        }
        for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(n) < 0; i = i.add(BigInteger.valueOf(2))) {
            if (n.mod(i).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCoprime(BigInteger a, BigInteger b) {
        return a.gcd(a).compareTo(b) == 0;
    }

    private static boolean compareKeyWithEuler(BigInteger Kc, BigInteger eulerFuncRes) {
        //if kc >= eulerFuncRes or kc <= 1
        if (Kc.compareTo(eulerFuncRes) >= 0 ||
                Kc.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        else return true;
    }

    public static String validate(BigInteger p, BigInteger q, BigInteger Kc, BigInteger euler) {
        if (isPrime(p) == false) {
            return P_NOT_PRIME;
        }
        if (isPrime(q) == false) {
            return Q_NOT_PRIME;
        }
        if (!isCoprime(Kc, euler) == false) {
            return NOT_COPRIME;
        }
        if (compareKeyWithEuler(Kc, euler) == false) {
            return INCORRECT_CLOSED_KEY;
        }
        else {
            return "OK";
        }
    }
}
