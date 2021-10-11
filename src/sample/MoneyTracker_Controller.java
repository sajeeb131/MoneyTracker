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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoneyTracker_Controller{
    private static String cBalance,cCurrency;
    private static String pieB, pieG, pieR, pieT, pieS;
    String date;
    @FXML
    TextArea area;

    //user data instances
    @FXML
    Label usernameLabel, nameL,balanceL,loanL,currencyL,billL,groceryL,
            restaurantL,shoppingL,loan2L,transportL;
    String username,name,balance,loan,currency,bill,grocery,restaurant,shopping,transport;
    @FXML
    TextField eventL;

    static BufferedWriter writer=logInPage_Controller.getWriter();
    static BufferedReader reader=logInPage_Controller.getReader();

    //event reminder variables
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date currentdate = new Date();



    public MoneyTracker_Controller() throws IOException{
        //get default user data
        username=reader.readLine();
        name=reader.readLine();
        balance=reader.readLine();
        loan=reader.readLine();
        currency=reader.readLine();

        //get default user data
        bill=reader.readLine();
        grocery=reader.readLine();
        restaurant=reader.readLine();
        shopping=reader.readLine();
        transport=reader.readLine();
        loan=reader.readLine();

        //get event date
        date=reader.readLine();

        cBalance = balance;
        cCurrency = currency;
        pieB = bill;
        pieG = grocery;
        pieR = restaurant;
        pieT = transport;
        pieS = shopping;

        System.out.println("Event  date: "+date);
        System.out.println("System date: "+formatter.format(currentdate));

    }
    public static String getBalance(){
        return cBalance;
    }
    public static String getCurrency(){
        return cCurrency;
    }

    ///PieChartController value methods
    public static String getPieB(){
        return pieB;
    }
    public static String getPieG(){
        return pieG;
    }
    public static String getPieR(){
        return pieR;
    }
    public static String getPieT(){
        return pieT;
    }
    public static String getPieS(){
        return pieS;
    }

    @FXML
    public void initialize() {
        //set all user data from server to UI
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

        if(date.equals(formatter.format(currentdate))){
            try {
                writer.write("EventInfo"+"\n");
                writer.flush();
                String line=reader.readLine();
                eventL.setText(line);
                System.out.println("inside equals");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            eventL.setText("No event Today!");
        }

        //thread to catch any new changes and set it in UI
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
                        else if(category.equals("Balance")){
                            balance=reader.readLine();
                            Platform.runLater( () -> {
                                balanceL.setText(balance);
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
                        System.out.println("cast exception occurred.");
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
    @FXML
    private void getPieChart(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/PieChart.fxml"));
            Scene scene=new Scene(root);
            Stage window = new Stage();
            window.setTitle("Graph");
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
