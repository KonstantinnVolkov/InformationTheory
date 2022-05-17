using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace TheoryOfInformation.LFSR
{
    /// <summary>
    /// Realization of LFSR
    /// </summary>
    internal class Register
    {
        #region Private Members

        // degrees of our polynomial
        private int[] degrees;

        // Current state of LFSR
        private int[] myRegister;

        // Cells that take part in XOR operation
        private int[] xorCells;
        #endregion

        #region Constructor

        public Register(int[] numbers, string initial)
        {
            // Copy degrees from our polynomial
            degrees = new int[numbers.Length];
            Array.Copy(numbers, 0, degrees, 0, numbers.Length);
            // Set myRegister length to number of register cells
            myRegister = new int[degrees.Max()];
            int i = 0;
            for (; i < myRegister.Length && i < initial.Length; i++)
            {
                //write key base state
                myRegister[myRegister.Length - 1 - i] = (int)(initial[i] - '0');
            }
            for (; i < myRegister.Length; i++)
                myRegister[myRegister.Length - 1 - i] = 1;
            // Set amount of XOR cells
            xorCells = new int[degrees.Length];
        }

        #endregion

        #region Public Methods
        // Generate new key bit
        public int GenerateKeyBit()
        {
            // Get keyBit
            int keyBit = myRegister[myRegister.Length - 1];
            // Get cells that take part in XOR operation
            for (int i = 0; i < degrees.Length; i++)
                xorCells[i] = myRegister[degrees[i] - 1];
            // Shift of register
            for (int i = myRegister.Length - 1; i > 0; i--)
                myRegister[i] = myRegister[i - 1];
            // Generete new first bit
            myRegister[0] = xorCells[0];
            for (int i = 1; i < xorCells.Length; i++)
                myRegister[0] ^= xorCells[i];
            return keyBit;
        }
        #endregion
    }
}
