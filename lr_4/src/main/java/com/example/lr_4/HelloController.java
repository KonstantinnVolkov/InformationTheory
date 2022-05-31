package com.example.lr_4;

import com.example.lr_4.utils.Hash;
import com.example.lr_4.utils.keys.ClosedKey;
import com.example.lr_4.utils.keys.OpenedKey;
import com.example.lr_4.utils.RSA_EDS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class HelloController {

    private final String PATH_TO_FILES = "D:\\My_projects\\Лабы\\4 сем\\ТИ\\lr_4\\src\\main\\resources\\files\\";
    private final String CLOSED_KEY_ERROR = "Wrong closed key!";

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
    private Button chooseFileBTN;

    @FXML
    private TextField filePathTF;

    @FXML
    private TextField hashTF;

    @FXML
    private TextField privateKeyTF;

    @FXML
    private Button encodeBtn;

    @FXML
    void encodeAct(ActionEvent event) {
        p = new BigInteger(P_TF.getText());
        q = new BigInteger(Q_TF.getText());
        r = p.multiply(q);
        if (!fileData.isEmpty()) {
            BigInteger hashImage = Hash.hashFunc(p, q , r, fileData.toString()); //step 1: find hash image m of M message
            BigInteger eulerFuncResult = RSA_EDS.eulerFunction(p, q); //step 2: find euler function of p and q
            BigInteger closedKey = new BigInteger(privateKeyTF.getText()); //step 3: find closed key
        }
        PQ_TF.setText(r.toString());
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
