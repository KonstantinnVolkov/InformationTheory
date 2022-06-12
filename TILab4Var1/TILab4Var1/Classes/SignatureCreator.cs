using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace TILab4Var1.Classes
{
    internal class SignatureCreator
    {
        public static BigInteger ECPCalculate(BigInteger p, BigInteger q, BigInteger H0, BigInteger d, string src, out BigInteger m)
        {
            BigInteger r = BigInteger.Multiply(p, q);
            m = MathAlgorithms.hashFunc(src, H0, p, q);
            BigInteger S = MathAlgorithms.FastExprModulo(m, d, r);
            return S;
        }

        public static bool ECPCheck(BigInteger p, BigInteger q, BigInteger H0, BigInteger e, BigInteger S, string msg, out BigInteger m, out BigInteger m1)
        {
            BigInteger r = BigInteger.Multiply(p, q);
            m = MathAlgorithms.hashFunc(msg, H0, p, q);
            m1 = MathAlgorithms.FastExprModulo(S, e, r);
            return BigInteger.Equals(m1, m);
        }
    }
}
