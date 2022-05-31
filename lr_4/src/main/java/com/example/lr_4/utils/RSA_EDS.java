package com.example.lr_4.utils;

import com.example.lr_4.utils.keys.OpenedKey;

import java.math.BigInteger;

public class RSA_EDS {

    public static BigInteger EDS (BigInteger m, BigInteger d, BigInteger r) {
        BigInteger s;
        s = m.pow(d.intValue()).mod(r);
        return s;
    }

    public static OpenedKey getOpenedKey(BigInteger e, BigInteger r) {
        //if e less than r
        if (e.compareTo(r) == -1) {
            return null;
        }
        BigInteger bufE = e;
        BigInteger bufR = r;
        OpenedKey key = new OpenedKey(e, r);
        do {
            if (key.e().mod(key.r()).equals(BigInteger.ONE)) {
                return key;
            }
            else {
                key = new OpenedKey(key.r(), key.e().mod(key.r()));
            }
        } while (true);
    }

    public static BigInteger eulerFunction(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // (p-1)*(q-1)
    }
}

