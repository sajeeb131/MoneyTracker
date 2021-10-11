package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

public class events_Controller {
    @FXML
    DatePicker datePick;
    BufferedWriter writer=logInPage_Controller.getWriter();
    @FXML
    TextField amountL,nameL;

    @FXML
    void setButton(ActionEvent event){
        try {
            LocalDate localdate= datePick.getValue();
            String date=localdate+"";
            System.out.println(date);

            writer.write("Event"+"\n");
            writer.write(date+"\n");
            writer.write(nameL.getText() +"\n");
            System.out.println(nameL.getText());
            writer.write(amountL.getText()+"\n");
            System.out.println(amountL.getText());

            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        closeButton(event);
    }
    @FXML
    void closeButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
