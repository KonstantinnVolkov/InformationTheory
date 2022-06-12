using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Numerics;
using TILab4Var1.Classes;
using System.IO;

namespace TILab4Var1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void SignFile(object sender, RoutedEventArgs e)
        {
            try
            {
                
                string msg = File.ReadAllText(FileToSignInput.Text);
                BigInteger p = BigInteger.Parse(PInput.Text), q = BigInteger.Parse(QInput.Text), Kc = BigInteger.Parse(KcInput.Text);
                BigInteger eulerFunc = BigInteger.Multiply(BigInteger.Subtract(p, 1), BigInteger.Subtract(q, 1));
                if (!MathAlgorithms.CheckInput(p, q, Kc, eulerFunc))
                    return;
                BigInteger hashFunc;
                BigInteger S = SignatureCreator.ECPCalculate(p, q, 100, Kc, msg, out hashFunc);
                FileWork.ECPWriteToFile(SignedFileInput.Text, msg, S);
                HashSignOutput.Text = hashFunc.ToString();
                SOutput.Text = S.ToString();
            }
            catch (FormatException)
            {
                MessageBox.Show("Incorrect input!!!");
            }
        }

        private void CheckFileSign(object sender, RoutedEventArgs e)
        {
            try
            {
                string msg = FileWork.GetFileContent_Signed(FileToCheckSignInput.Text);
                BigInteger S = FileWork.ECPGetFromFile(FileToCheckSignInput.Text);
                BigInteger p = BigInteger.Parse(PInput.Text), q = BigInteger.Parse(QInput.Text), Kc = BigInteger.Parse(KcInput.Text);
                BigInteger hashFunc;
                BigInteger eulerFunc = BigInteger.Multiply(BigInteger.Subtract(p, 1), BigInteger.Subtract(q, 1));
                if (!MathAlgorithms.CheckInput(p, q, Kc, eulerFunc))
                    return;
                BigInteger m1;
                BigInteger Ko = MathAlgorithms.EuclidEx(eulerFunc, Kc);
                if (SignatureCreator.ECPCheck(p, q, 100, Ko, S, msg, out hashFunc, out m1))
                {
                    MessageBox.Show("Sign is valid");
                }
                else
                {
                    MessageBox.Show("Sign is invalid");
                }
                HashCheckSignedOutput.Text = hashFunc.ToString();
                KoOutput.Text = Ko.ToString();
                HashViaFormulaOutput.Text = m1.ToString();
            }
            catch (FormatException)
            {
                MessageBox.Show("Incorrect input");
            }
        }
    }
}
