package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MoneyTracker_Controller{
    @FXML
    TextArea area;

    @FXML
    Label usernameLabel, nameL,balanceL,loanL,currencyL,billL,groceryL,
            restaurantL,shoppingL,loan2L,transportL;
    String username,name,balance,loan,currency,bill,grocery,restaurant,shopping,transport;

    static BufferedWriter writer=logInPage_Controller.getWriter();
    static BufferedReader reader=logInPage_Controller.getReader();

    public MoneyTracker_Controller() throws IOException{
        username=reader.readLine();
        name=reader.readLine();
        balance=reader.readLine();
        loan=reader.readLine();
        currency=reader.readLine();

    }

    @FXML
    public void initialize() {
        usernameLabel.setText(username);
        nameL.setText(name);
        balanceL.setText(balance);
        loanL.setText(loan);
        currencyL.setText(currency);

        Thread listener = new Thread(){
            @Override
            public void run(){
                while (true){
                    try{
                        System.out.println("running");
                        String category = reader.readLine();
                        if (category.equals("Bills")){
                            bill = reader.readLine();
                            billL.setText(bill);
                            System.out.println(bill);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        listener.start();
    }
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

}