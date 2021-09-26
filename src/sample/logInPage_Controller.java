package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;

public class logInPage_Controller {
    @FXML
    Label warning;
    @FXML
    TextField username,password;
    BufferedWriter writer;
    BufferedReader reader;
    @FXML
    void button_LogIn(ActionEvent event){
        try{
            Socket sc=new Socket("localhost",2021);
            InputStreamReader i=new InputStreamReader(sc.getInputStream());
            reader=new BufferedReader(i);
            OutputStreamWriter o=new OutputStreamWriter(sc.getOutputStream());
            writer=new BufferedWriter(o);

            writer.write("old"+"\n");
            writer.write(username.getText()+"\n");
            writer.write(password.getText()+"\n");
            writer.flush();
            String successful= reader.readLine();
            System.out.println(successful);

            if(successful.equals("True")){
                Parent root = FXMLLoader.load(getClass().getResource("MoneyTrackerPage.fxml"));
                Scene scene=new Scene(root);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Create a new Account");
                window.setScene(scene);
                window.show();
            }
            else{
                warning.setVisible(true);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    void buttonCreateAccount(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
            Scene scene=new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Create a new Account");
            window.setScene(scene);
            window.show();
        }
        catch (IOException e){
            System.out.println("Failed to Change Scene!LoginPage: 40");
        }
    }
}