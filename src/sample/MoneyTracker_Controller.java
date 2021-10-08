package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoneyTracker_Controller{
    @FXML
    TextArea area;
    @FXML
    private void addButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/AddTransactionScene.fxml"));
            Scene scene=new Scene(root);
            Stage window = new Stage();
            window.setTitle("Add Transaction");
            window.setScene(scene);
            window.setAlwaysOnTop(true);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void currencyConverterButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/CurrencyConverter.fxml"));
            Scene scene=new Scene(root);
            Stage window = new Stage();
            window.setTitle("Currency Converter");
            window.setScene(scene);
            window.setAlwaysOnTop(true);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}