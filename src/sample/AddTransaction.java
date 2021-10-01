package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddTransaction implements Initializable {

    @FXML
    ChoiceBox<String> categoryAdd;
    String[] categories = {"Bills", "Grocery", "Restaurant", "Transport", "Shopping"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryAdd.getItems().addAll(categories);
    }

    @FXML
    TextField amountTF;
    DatePicker dateEX;
    TextArea descriptionADD;
    String date;
    Button saveButton;

    ///Calling writer from loginPage_Controller file
    BufferedWriter writer = logInPage_Controller.getWriter();

    ///Date picker method turning it into a String
    @FXML
    public void getDate(ActionEvent event){
        LocalDate myDate = dateEX.getValue();
        date = myDate.toString();
    }

    @FXML
    public void saveButton(ActionEvent event) {
        try {
            ////Sending the values to the server
            String category = categoryAdd.getValue();
            double amount = Double.parseDouble(amountTF.getText());
            String dateEX = date;
            String description = descriptionADD.getText();

            writer.write(category+"\n");
            writer.write(amount+"\n");
            writer.write(dateEX+"\n");
            writer.write(description+"\n");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
