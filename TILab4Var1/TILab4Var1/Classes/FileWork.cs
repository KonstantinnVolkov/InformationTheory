using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;
using System.IO;

namespace TILab4Var1.Classes
{
    internal class FileWork
    {
        public static void ECPWriteToFile(string pathToFile, string srcInfo, BigInteger s)
        {
            File.WriteAllText(pathToFile, srcInfo + " " + s.ToString());
        }

        public static BigInteger ECPGetFromFile(string pathToFile)
        {
            string[] src = File.ReadAllText(pathToFile).Split(' ');
            return BigInteger.Parse(src[src.Length - 1]);
        }

        public static string GetFileContent_Signed(string pathToFile)
        {
            string[] src = File.ReadAllText(pathToFile).Split(' ');
            string res = string.Empty;
            for (int i = 0; i < src.Length - 1; i++)
                res += src[i];
            return res;
        }
    }
}
