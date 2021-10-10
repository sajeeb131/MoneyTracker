package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CurrencyConverter_Controller implements Initializable {
    @FXML
    TextField showBalanceTf, showCurrencyTf, convertBalanceTf, amountPutTf, amountShowTf;
    @FXML
    ChoiceBox<String> choiceBox1, choiceBox2, choiceBox3;
    String[] Currencies = {"USD","Euro","Taka","Rupee"};

    String amountStr2,amountStr3;

    static BufferedReader reader = logInPage_Controller.getReader();
    static String balance = MoneyTracker_Controller.getBalance();
    static String currency = MoneyTracker_Controller.getCurrency();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox1.getItems().addAll(Currencies);
        choiceBox2.getItems().addAll(Currencies);
        choiceBox3.getItems().addAll(Currencies);
    }
    //getting Current Balance & Currency->
    public void getButton(ActionEvent event) throws IOException {
        System.out.println(balance);
        showBalanceTf.setText(balance);
        showCurrencyTf.setText(currency);
    }

    //Only Current Balance Converter->
    public void convert1Button(ActionEvent event) {
        String currency2 = currency;
        String CBCurrency = choiceBox1.getValue();
        Double amount = Double.parseDouble(balance);
        switch (currency2+CBCurrency){
            case "US Dollar"+"Taka" :
                amount= amount*84;
                break;
            case "US Dollar"+"Euro" :
                amount= amount/0.86;
                break;
            case "US Dollar"+"Rupee" :
                amount= amount*75.13;
                break;
            case "Taka"+"USD" :
                amount= amount/0.012;
                break;
            case "Taka"+"Euro" :
                amount= amount/0.010;
                break;
            case "Taka"+"Rupee" :
                amount= amount/0.88;
                break;
            case "Euro"+"Taka" :
                amount= amount*98.95;
                break;
            case "Euro"+"USD" :
                amount= amount*1.16;
                break;
            case "Euro"+"Rupee" :
                amount= amount*86.93;
                break;

        }
        //formatting 2 decimal value & updating the Textfields ->
        DecimalFormat df = new DecimalFormat(".##");
        double dAmount = Double.parseDouble(df.format(amount));
        amountStr3 = dAmount+"";
        convertBalanceTf.setText(amountStr3);
    }
    //any amount Converter of Currencies->
    public void convert2Button(ActionEvent event) throws Exception {
        String amountStr = amountPutTf.getText();
        Double amount = Double.parseDouble(amountStr);
        String currency = choiceBox2.getValue();
        String currency2 = choiceBox3.getValue();

//        if (amountStr==null){
//            amountShowTf.setPromptText("NO Value Inserted!");
//        }
        switch (currency+currency2){
            case "USD"+"Taka" :
                amount= amount*84;
                break;
            case "USD"+"Euro" :
                amount= amount/0.86;
                break;
            case "USD"+"Rupee" :
                amount= amount*75.13;
                break;
            case "Taka"+"Rupee" :
                amount= amount/0.88;
                break;
            case "Taka"+"USD" :
                amount= amount/0.012;
                break;
            case "Taka"+"Euro" :
                amount= amount/0.010;
                break;
            case "Euro"+"Taka" :
                amount= amount*98.95;
                break;
            case "Euro"+"Rupee" :
                amount= amount*86.93;
                break;
            case "Euro"+"USD" :
                amount= amount*1.16;
                break;
            case "Rupee"+"Taka" :
                amount= amount*1.14;
                break;
            case "Rupee"+"Euro" :
                amount= amount/0.011;
                break;
            case "Rupee"+"USD" :
                amount= amount/0.013;
                break;
        }
        //formatting 2 decimal value & updating the Textfields ->
        DecimalFormat df2 = new DecimalFormat(".##");
        double dAmount2 = Double.parseDouble(df2.format(amount));
        amountStr2 = dAmount2+"";
        amountShowTf.setText(amountStr2);
    }

    public void backButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
