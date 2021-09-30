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
    TextField username,fullName,password,totalBalance;
    @FXML
    Label warning;
    @FXML
    private ChoiceBox<String> currency_choiceBox;
    private String[] currency={"Taka","US Dollar","Euro"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currency_choiceBox.getItems().addAll(currency);
    }
    BufferedWriter bw;
    BufferedReader br;
    @FXML
    void button_SignUp(ActionEvent event) throws IOException {
        if(username.getText().length()==0||fullName.getLength()==0||password.getLength()==0||totalBalance.getLength()==0||currency_choiceBox.getValue().length()==0){
            warning.setVisible(true);
            return;
        }
        Socket sc=new Socket("localhost",2021);
        InputStreamReader i=new InputStreamReader(sc.getInputStream());
        br=new BufferedReader(i);
        OutputStreamWriter o=new OutputStreamWriter(sc.getOutputStream());
        bw=new BufferedWriter(o);

        bw.write("new"+"\n");
        bw.write(username.getText()+"\n");
        bw.write(password.getText()+"\n");
        bw.write(fullName.getText()+"\n");
        bw.write(totalBalance.getText()+"\n");
        bw.write(currency_choiceBox.getValue()+"\n");
        bw.flush();

        try{
            Parent root = FXMLLoader.load(getClass().getResource("FXML/MoneyTrackerPage.fxml"));
            Scene scene=new Scene(root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Create a new Account");
            window.setScene(scene);
            window.show();
        }
        catch (IOException e){
            System.out.println("sign Up Page not found!");
        }


    }
}
