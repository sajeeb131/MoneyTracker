package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTransaction implements Initializable{

    @FXML
    ChoiceBox<String> categoryAdd;
    String[] categories = {"Bills", "Grocery", "Restaurant", "Transport", "Shopping"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryAdd.getItems().addAll(categories);
    }
    @FXML
    TextField amountTF;
    @FXML
    DatePicker dateEX;
    @FXML
    TextArea descriptionAdd;
    Button saveButton;

    ///Calling writer from loginPage_Controller file
    BufferedWriter writer = logInPage_Controller.getWriter();


    @FXML
    public void saveButton(ActionEvent event){
        try {
            ////Sending the values to the server
            LocalDate myDate = dateEX.getValue();
            String category = categoryAdd.getValue();
            String amount = amountTF.getText();
            String date = myDate.toString(); //Turning myDate object into String
            String description = descriptionAdd.getText();

            writer.write(category+"\n");
            writer.write(amount+"\n");
            writer.write(date+"\n");
            writer.write(description+"\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void closeButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
