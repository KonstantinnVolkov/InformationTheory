package com.example.lr_4.utils;

import java.math.BigInteger;

public class RSA_EDS {

    public static BigInteger EDS (BigInteger m, BigInteger d, BigInteger r) {
        BigInteger s;
        s = m.pow(d.intValue()).mod(r);
        return s;
    }

    public static boolean checkEDS (BigInteger r, BigInteger hash, BigInteger openedKey, BigInteger signature, String message) {
        BigInteger mStroke = fastExprModulo(signature, openedKey, r);
        if (mStroke.compareTo(hash) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static BigInteger getOpenedKey(BigInteger eulerFuncRes, BigInteger closedKey) {
        BigInteger d0 = eulerFuncRes;
        BigInteger d1 = closedKey;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;
        //while d1 more than 1
        while (d1.compareTo(BigInteger.ONE) > 1) {
            BigInteger q = d0.divide(d1);
            BigInteger d2 = d0.mod(d1);
            BigInteger x2 = x0.subtract(q.multiply(x1));
            BigInteger y2 = y0.subtract(q.multiply(y1));
            d0 = d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }
        //if y1 < 0
        if (y1.compareTo(BigInteger.ZERO) == -1) {
            return y1.add(eulerFuncRes);
        }
        else {
            return y1;
        }
    }

    public static BigInteger eulerFunction(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // (p-1)*(q-1)
    }

    private static BigInteger fastExprModulo(BigInteger sign, BigInteger openedKey, BigInteger r) {
        BigInteger x = BigInteger.ONE;
        //while openedKey != 0
        while (openedKey.compareTo(BigInteger.ZERO) != 0) {
            //while openedKey mod 2 == 0
            while (openedKey.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
                openedKey = openedKey.divide(BigInteger.TWO);
                sign = sign.multiply(sign);
                sign = sign.mod(r);
            }
            openedKey = openedKey.subtract(BigInteger.ONE);
            x = x.multiply(sign);
            x = x.mod(r);
        }
        return x;
    }

}

