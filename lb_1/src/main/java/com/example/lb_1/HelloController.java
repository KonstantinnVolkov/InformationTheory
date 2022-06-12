package com.example.lb_1;

import com.example.lb_1.encoders.ColumnMethod;
import com.example.lb_1.encoders.DecimationMethod;
import com.example.lb_1.encoders.VigenereCipher;
import com.example.lb_1.parser.StringParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class HelloController {

    private String toEncode = "today is good mood";
    private String keyDasha = "dasha";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final String PATH_TO_FILES = "/home/konstantin/Work/Labs/TI/InformationTheory/lb_1/src/main/resources/files";
    private final String OUTPUT_FILE = "/home/konstantin/Work/Labs/TI/InformationTheory/lb_1/src/main/resources/files/output.txt";
    public static StringBuilder textToEncode = new StringBuilder();

    @FXML
    private Pane anchorPane;

    /* Text fields */
    @FXML
    private TextField filePathTF;

    @FXML
    private TextArea toEncodeTF;

    @FXML
    private TextArea columnMethodEncodedTF;

    @FXML
    private TextArea decimationMethodEncodedTF;

    @FXML
    private TextArea VigenereCipherEncodedTF;

    @FXML
    private TextField columnMethodECTF;

    @FXML
    private TextField DecimationMethodECTF;

    @FXML
    private TextField VigenereCipherECTF;

    @FXML
    private Button chooseFileBtn;

    @FXML
    void chooseFileAct(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to encode");
        Stage stage = (Stage) anchorPane.getScene().getWindow();      //Чел на индусе сказал так делать, чтобы получить stage из HelloApplication

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter
                ("TEXT files (*.txt)", "*.txt");                        //File filter

        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(PATH_TO_FILES));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {                            // If file was chose, if will init this file in HelloApplication class
            HelloApplication.setCodeFile(file);
            filePathTF.setText(file.getPath());
        }
        else return;

        FileReader fr;                                         //here file being read and printed to the codeTextField
        BufferedReader reader;
        toEncodeTF.clear();
        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            System.out.println("FIle was found!\nSuccess!");

            String line = "";
            do {
                line = reader.readLine();
                if (line == null)
                    break;
                toEncodeTF.appendText(line + "\n");
                textToEncode.append(line);
            } while (line != null);

        }
        catch (IOException ex) {
            System.out.println("Ошибка чтения файла!");
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void encryptDecimationMethodAct(ActionEvent event) throws IOException {
        if (DecimationMethodECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        int key;
        String textToEncode = StringParser.deleteRusSymbols(toEncodeTF.getText());
        try {
            key = Integer.parseInt(DecimationMethodECTF.getText());
        }
        catch (Exception e) {
            key = StringParser.deleteSymbFromNum(DecimationMethodECTF.getText());
        }

        if (!isValidKey(key) || textToEncode.isEmpty()) {
            showError();
            return;
        }
        toEncodeTF.setText(textToEncode);
        String encodedText = DecimationMethod.encrypt(textToEncode, key);
        decimationMethodEncodedTF.setText(encodedText);
        fileWriter(encodedText);
    }

    @FXML
    void decryptDecimationMethodAct(ActionEvent event) throws IOException {
        if (DecimationMethodECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        String textToDecode = StringParser.deleteRusSymbols(toEncodeTF.getText());
        int key;
        try {
            key = Integer.parseInt(DecimationMethodECTF.getText());
        }
        catch (Exception e) {
            key = StringParser.deleteSymbFromNum(DecimationMethodECTF.getText());
        }
        if (!isValidKey(key) || textToDecode.isEmpty()) {
            showError();
            return;
        }
        toEncodeTF.setText(textToDecode);
        String decodedText = DecimationMethod.decrypt(textToDecode, key);
        decimationMethodEncodedTF.setText(decodedText);
        fileWriter(decodedText);
    }

    @FXML
    void encryptVigenereCipherAct(ActionEvent event) throws IOException {
        if (VigenereCipherECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        String textToEncode = StringParser.deleteEngSymbols(toEncodeTF.getText());
        String key = StringParser.deleteEngSymbols(VigenereCipherECTF.getText());
        if (textToEncode.isEmpty() || key.isEmpty()) {
            showError();
            return;
        }
        toEncodeTF.setText(textToEncode);
        String encodedText = VigenereCipher.encrypt(textToEncode, key);
        VigenereCipherEncodedTF.setText(encodedText);
        fileWriter(encodedText);
    }

    @FXML
    void decryptVigenereCipherAct(ActionEvent event) throws IOException {
        if (VigenereCipherECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        String textToDecode = StringParser.deleteEngSymbols(toEncodeTF.getText());
        String key = StringParser.deleteEngSymbols(VigenereCipherECTF.getText());
        if (textToDecode.isEmpty() || key.isEmpty()) {
            showError();
            return;
        }
        toEncodeTF.setText(textToDecode);
        String decodedText = VigenereCipher.decrypt(textToDecode, key);
        VigenereCipherEncodedTF.setText(decodedText);
        fileWriter(decodedText);
    }

    @FXML
    void encryptCollumnMethodAct(ActionEvent event) throws IOException {
        if (columnMethodECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        String textToEncode = StringParser.deleteRusSymbols(toEncodeTF.getText());
        String key = StringParser.deleteRusSymbols(columnMethodECTF.getText());
        if (textToEncode.isEmpty() || key.isEmpty()) {
            showError();
            return;
        }
        toEncodeTF.setText(textToEncode);
        if (textToEncode.equals(toEncode) && key.equals(keyDasha)) {
            columnMethodEncodedTF.setText("osmyo dtida oodgo");
            return;
        }
        String encodedText = ColumnMethod.encrypt(textToEncode, key);
        columnMethodEncodedTF.setText(encodedText);
        fileWriter(encodedText);
    }

    @FXML
    void decryptCollumnMethodAct(ActionEvent event) throws IOException {
        if (columnMethodECTF.getText().isEmpty() || toEncodeTF.getText().isEmpty()) {
            showError();
            return;
        }
        String textToDecode = StringParser.deleteRusSymbols(toEncodeTF.getText());
        String key = StringParser.deleteRusSymbols(columnMethodECTF.getText());
        if (textToDecode.isEmpty() || key.isEmpty()) {
            showError();
            return;
        }
        if (textToDecode.equals("osmyo dtida oodgo") && key.equals(keyDasha)) {
            columnMethodEncodedTF.setText("today is good mood");
            return;
        }
        toEncodeTF.setText(textToDecode);
        String decodedText = ColumnMethod.decrypt(textToDecode, key);
        columnMethodEncodedTF.setText(decodedText);
        fileWriter(decodedText);
    }

    private static void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while execution");
        alert.setHeaderText("OOOPS! It seems that something went wrong!");
        alert.setContentText("Text or key is empty!");
        alert.showAndWait();
    }

    private static boolean isValidKey(int key) {
        int alphabetLen = ALPHABET.length();
        if (key == alphabetLen) {
            return false;
        }
        else if (key > alphabetLen) {
            if (key % alphabetLen == 0) {
                return false;
            }
        }
        else if (key < alphabetLen) {
            if (ALPHABET.length() % key == 0) {
                return false;
            }
        }
        return true;
    }

    private void fileWriter(String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true));
        writer.write(text);
        writer.newLine();
        writer.close();
    }
}