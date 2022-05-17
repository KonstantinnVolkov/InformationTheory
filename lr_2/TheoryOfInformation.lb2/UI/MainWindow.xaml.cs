using System;
using System.IO;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using TheoryOfInformation.lb2.Encoding;

namespace TheoryOfInformation.lb2
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

        private void TextBox_GotFocus(object sender, RoutedEventArgs e)
        {
            TextBox tb = (TextBox)sender;
            tb.Text = string.Empty;
            tb.GotFocus -= TextBox_GotFocus;
        }


        private void KeyValidationTextBox(object Sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^0-1]+");
            e.Handled = regex.IsMatch(e.Text);
        }

        private bool zeroCheck(string regState)
        {
            for(int i = 0; i < regState.Length; i++)
            {
                if (regState[i] != '0') return true;
            }
            return false;
        }

        private void TB_PerformFileNameChange(object sender, TextChangedEventArgs e)
        {
            //working with polinome length
            BT_Perform.IsEnabled = (!TB_PerformFileName.Text.Equals(String.Empty) && !TB_ResultFileName.Text.Equals("") && TB_initialKey.Text.Length == 28 && zeroCheck(TB_initialKey.Text));
        }

        private void BT_Perform_Click(object sender, RoutedEventArgs e)
        {
            // working with degrees, which will used for generating new register value
            Encoder.Encode(new int[] { 28, 3}, TB_initialKey.Text, TB_PerformFileName.Text, TB_ResultFileName.Text, 
                TB_Source, TB_GeneratedKey, TB_Result);
        }
    }
}
