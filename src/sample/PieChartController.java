package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {

    @FXML
    private PieChart pieChart;
    ///Getting values from labels
    double b =  Double.parseDouble(MoneyTracker_Controller.getPieB());
    double g = Double.parseDouble(MoneyTracker_Controller.getPieG());
    double r = Double.parseDouble(MoneyTracker_Controller.getPieR());
    double t = Double.parseDouble(MoneyTracker_Controller.getPieT());
    double s = Double.parseDouble(MoneyTracker_Controller.getPieS());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bill", b),
                new PieChart.Data("Grocery", g),
                new PieChart.Data("Restaurannt", r),
                new PieChart.Data("Transport", t),
                new PieChart.Data("Shopping", s)
        );
        pieChart.setData(pieChartData);
    }
}