package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class MoneyTracker_Controller{
    private static String ccBalance,ccCurrency;
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
        ccBalance = balance;
        ccCurrency = currency;
        bill=reader.readLine();
        grocery=reader.readLine();
        restaurant=reader.readLine();
        shopping=reader.readLine();
        transport=reader.readLine();
        loan=reader.readLine();
    }
    public static String getBalance(){
        return ccBalance;
    }
    public static String getCurrency(){
        return ccCurrency;
    }

    @FXML
    public void initialize() {
        usernameLabel.setText(username);
        nameL.setText(name);
        balanceL.setText(balance);
        loanL.setText(loan);
        currencyL.setText(currency);
        billL.setText(bill);
        groceryL.setText(grocery);
        restaurantL.setText(restaurant);
        shoppingL.setText(shopping);
        transportL.setText(transport);
        loan2L.setText(loan);


        Thread listener = new Thread(){
            @Override
            public void run(){
                while (true){
                    try{
                        String category = reader.readLine();
                        if (category.equals("Bill")){
                            bill = reader.readLine();
                            Platform.runLater( () -> {
                            billL.setText(bill);
                            });
                            System.out.println(bill);
                        }
                        else if (category.equals("Grocery")){
                            grocery = reader.readLine();
                            Platform.runLater( () -> {
                                groceryL.setText(grocery);
                            });
                        }
                        else if (category.equals("Restaurant")){
                            restaurant = reader.readLine();
                            Platform.runLater( () -> {
                                restaurantL.setText(restaurant);
                            });
                        }
                        else if (category.equals("Shopping")){
                            shopping = reader.readLine();
                            Platform.runLater( () -> {
                                shoppingL.setText(shopping);
                            });
                        }
                        else if (category.equals("Transport")){
                            transport = reader.readLine();
                            Platform.runLater( () -> {
                                transportL.setText(transport);
                            });
                        }
                    }
                    catch (SocketException e){
                        e.printStackTrace();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    catch (ClassCastException e){
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
    @FXML
    private void eventButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/event.fxml"));
            Scene scene=new Scene(root);
            Stage window = new Stage();
            window.setTitle("Events");
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
