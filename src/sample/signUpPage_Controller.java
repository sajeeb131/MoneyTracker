package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class signUpPage_Controller implements Initializable{
    @FXML
    TextField username,firstname,lastname,password,totalBalance;
    @FXML
    Label warning,currencyT;
    @FXML
    private ChoiceBox<String> currency_choiceBox;
    private String[] currency={"Taka","US Dollar","Euro"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currency_choiceBox.getItems().addAll(currency);
        currency_choiceBox.setOnAction(this::setCurrency_choiceBox);
    }
    private void setCurrency_choiceBox(ActionEvent event){
        currencyT.setVisible(false);
    }
    BufferedWriter writer;
    BufferedReader reader;
    @FXML
    void button_SignUp(ActionEvent event) throws IOException {
        if(username.getText().length()==0||lastname.getLength()==0||firstname.getLength()==0||password.getLength()==0||totalBalance.getLength()==0||currency_choiceBox.getValue().length()==0){
            warning.setVisible(true);
            return;
        }
        Socket sc=new Socket("localhost",2021);
        InputStreamReader i=new InputStreamReader(sc.getInputStream());
        reader=new BufferedReader(i);
        OutputStreamWriter o=new OutputStreamWriter(sc.getOutputStream());
        writer=new BufferedWriter(o);

        writer.write("new"+"\n");
        writer.write(username.getText()+"\n");
        writer.write(password.getText()+"\n");
        writer.write(firstname.getText()+" "+lastname.getText()+"\n");
        writer.write(totalBalance.getText()+"\n");
        writer.write(currency_choiceBox.getValue()+"\n");
        writer.flush();

        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXML/logIn.fxml"));
            Scene scene=new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Create a new Account");
            window.setScene(scene);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
