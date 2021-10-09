package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class events_Controller {
    @FXML
    DatePicker datePick;

    @FXML
    void setButton(ActionEvent event){
        LocalDate localdate= datePick.getValue();
        String date=localdate+"";
        System.out.println(date);
    }
    @FXML
    void closeButton(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

}
