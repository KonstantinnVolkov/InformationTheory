package com.example.lr_4;

import com.example.lr_4.utils.Hash;
import com.example.lr_4.utils.RSA_EDS;
import com.example.lr_4.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigInteger;

public class HelloController {

//    private final String PATH_TO_FILES = "D:\\My_projects\\Лабы\\4 сем\\ТИ\\lr_4\\src\\main\\resources\\files\\";
    private final String PATH_TO_FILES = "/home/konstantin/Work/Labs/TI/InformationTheory/lr_4/src/main/resources/files";
    private final String INVALID_INPUT_ERR = "Invalid input!";

    BigInteger p;
    BigInteger q;
    BigInteger r;
    StringBuilder fileData = new StringBuilder();

    @FXML
    private Pane anchorPane;

    @FXML
    private TextArea fileDataTF;

    @FXML
    private TextField EDS_TF;

    @FXML
    private TextField PQ_TF;

    @FXML
    private TextField P_TF;

    @FXML
    private TextField Q_TF;

    @FXML
    private TextField eulerFuncTF;

    @FXML
    private Button chooseFileBTN;

    @FXML
    private TextField filePathTF;

    @FXML
    private TextField hashTF;

    @FXML
    private TextField privateKeyTF;

    @FXML
    private Button signFileBtn;

    @FXML
    private Button checkSignBtn;

    @FXML
    private Label EDSStatusLbl;

    @FXML
    void SignFileAct(ActionEvent event) {
        p = new BigInteger(P_TF.getText().trim());
        q = new BigInteger(Q_TF.getText().trim());
        BigInteger closedKey = new BigInteger(privateKeyTF.getText().trim());
        r = p.multiply(q);
        BigInteger eulerFuncResult = RSA_EDS.eulerFunction(p, q);
        PQ_TF.setText(r.toString());
        eulerFuncTF.setText(eulerFuncResult.toString());
        if (Validator.validate(p, q, closedKey, eulerFuncResult) != "OK") {
            showError(Validator.validate(p, q, closedKey, eulerFuncResult));
            return;
        }
        if (!fileData.isEmpty()) {
            BigInteger hashImage = Hash.hashFunc(r, fileData.toString()); //step 1: find hash image m of M message
            BigInteger EDS = RSA_EDS.EDS(hashImage, closedKey, r);  //step 4: find EDS
            hashTF.setText(hashImage.toString());
            EDS_TF.setText(EDS.toString());

            String signedFileData = fileData.toString() + " " + EDS.toString();
            //write to file
            try (FileWriter fw = new FileWriter(PATH_TO_FILES + "/signedFile.txt")) {
                fw.write("");
                fw.write(signedFileData);
                fw.flush();
            }
            catch (IOException e) {
                showError(e.getMessage());
            }
        }
    }

    @FXML
    void CheckSignAct(ActionEvent event) {
        String signedFileData = new String(fileData.toString());
        String message = signedFileData.split(" ")[0];
        BigInteger sign = new BigInteger(signedFileData.split(" ")[1]);
        p = new BigInteger(P_TF.getText().trim());
        q = new BigInteger(Q_TF.getText().trim());
        BigInteger eulerFuncResult = RSA_EDS.eulerFunction(p, q);
        BigInteger closedKey = new BigInteger(privateKeyTF.getText().trim());
        if (!fileData.isEmpty()) {
            BigInteger hashImage = Hash.hashFunc(p, fileData.toString()); //step 1: find hash image m of M message
            BigInteger openedKey = RSA_EDS.getOpenedKey(eulerFuncResult, closedKey);
            if (RSA_EDS.checkEDS(r, hashImage, openedKey, sign, message) == true) {
                EDSStatusLbl.setText("Sign is valid");
            }
            else {
                EDSStatusLbl.setText("Sign is invalid");
            }
        }
    }

    @FXML
    void chooseFileAct(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to encode");
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter
                ("TEXT files (*.txt)", "*.txt");                        //File filter

        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(PATH_TO_FILES));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {                            // If file was chose, if will init this file in HelloApplication class
            HelloApplication.setFileToCheck(file);
            filePathTF.setText(file.getPath());

            FileReader fileReader;
            BufferedReader bufferedReader;
            fileDataTF.clear();;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String line = "";
                do {
                    line = bufferedReader.readLine();
                    if (line == null)
                        break;
                    else {
                        fileDataTF.appendText(line + "\n");
                        fileData.append(line);
                    }
                } while (line != null);
            }
            catch (IOException e) {
                System.out.println("Error while reading file.");
                e.printStackTrace();
            }
        }
        else
            return;
    }

    private static void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while execution");
        alert.setHeaderText(err);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
    }

}
