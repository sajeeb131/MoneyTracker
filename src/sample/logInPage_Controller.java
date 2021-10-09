package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.*;
import java.net.Socket;

public class logInPage_Controller {

    @FXML
    Label warning;
    @FXML
    TextField username=null,password=null;
    static BufferedWriter writer;
    static BufferedReader reader;
    @FXML
    void button_LogIn(ActionEvent event){
        try{
            if(username==null||password==null){
                warning.setVisible(true);
                return;
            }
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
                Parent root = FXMLLoader.load(getClass().getResource("FXML/MoneyTrackerPage.fxml"));
                Scene scene=new Scene(root);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setTitle("Money Tracker");
                window.setScene(scene);
                window.show();
            }
            else{
                warning.setVisible(true);
                return;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BufferedWriter getWriter(){
        return writer;
    }
    public static BufferedReader getReader(){
        return reader;
    }
    @FXML
    void buttonCreateAccount(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXML/signUpPage.fxml"));
            Scene scene=new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Sign Up");
            window.setScene(scene);
            Image icon=new Image("sample/icons/singUpIcon.png");
            window.getIcons().add(icon);
            window.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
