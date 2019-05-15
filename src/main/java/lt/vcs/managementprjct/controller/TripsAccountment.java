package lt.vcs.managementprjct.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lt.vcs.managementprjct.services.ConnectionClass;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TripsAccountment implements Initializable {
    public ArrayList<Double> custPrice;// = new ArrayList<Double>();
    public ArrayList<Double> carrPrice;
    Double results[][];
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    //ConnectionClass connectionClass = new ConnectionClass();
    //conn = connectionClass.connect();

    @FXML
    private AnchorPane borderPane;

    @FXML
    private TextField turnoverField;
    @FXML
    private TextField profitField;

    private Double[][] loadData() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT customerPrice, carrierPrice FROM Trip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                custPrice.add(rs.getDouble("customerPrice"));
                carrPrice.add(rs.getDouble("carrierPrice"));
            }
            conn.close();

        } catch (SQLException ex) {
            ex.getMessage();
        }
        int listSize = custPrice.size();
        results = new Double[2][listSize];

        for (int i = 0; i < listSize; i++) {
            results[i][0] = custPrice.get(i);
        }
        for (int i = 0; i < 2; i++) {
            results[i][1] = carrPrice.get(i);
        }
        return results;
    }

    double turnover;

    @FXML
    private double turnoverTotal() {
        for (double element : custPrice) {
            System.out.println(element);
        }

        turnoverField.setText("hsfghdhxf");

        return turnover;
    }

    Double profit;

    @FXML
    private Double profitTotal() {
        for (Double element : carrPrice) {
            profit = element++;
        }
        System.out.println(profit);
        String stringProfit = profit.toString();
        profitField.setText(stringProfit);
        return profit;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectionClass connectionClass = new ConnectionClass();
        conn = connectionClass.connect();
        loadData();
    }

    public void addNewTrip(ActionEvent event) {
    }

    public void editTrip(ActionEvent event) {
    }

    public void removeTrip(ActionEvent event) {
    }

    public void refresh(ActionEvent event) {
    }

    public void logout(ActionEvent event) {
    }
}
