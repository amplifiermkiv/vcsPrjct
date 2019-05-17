package lt.vcs.managementprjct.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.Manager;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class TripsAccountment implements Initializable {
    @FXML
    private TableView<Manager> managersTableView;
    @FXML
    private TableColumn<Trip, Integer> managerIDCol;
    @FXML
    private TableColumn<Trip, String> managerNameCol;
    @FXML
    private TableView<Trip> accountmentTableView;
    @FXML
    private TableColumn<Trip, Integer> tripIdCol;
    @FXML
    private TableColumn<Trip, Double> customerPriceCol;
    @FXML
    private TableColumn<Trip, Double> carrierPriceCol;
    @FXML
    private TableColumn<Trip, Double> differenceCol;
    @FXML
    private TextField managerIDField;
    @FXML
    private TextField managerProfitField;
    @FXML
    private TextField managerTurnoverField;
    @FXML
    private TextField totalProfitField;
    @FXML
    private TextField totalTurnoverField;
    private ObservableList<Trip> accountmentData;
    private ObservableList<Manager> managerList;
    private ArrayList<Double> profitList = new ArrayList<>();
    private ArrayList<Double> customerPriceList = new ArrayList<>();
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TripManagementDBConnection connection = new TripManagementDBConnection();
        conn = connection.connect();
        accountmentData = FXCollections.observableArrayList();
        managerList = FXCollections.observableArrayList();
        setCellAccountment();
        loadData();
        setCellManager();
        loadManagerList();
    }

    @FXML
    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent newParent = FXMLLoader.load(getClass().getResource("/tripAccountment.fxml"));
        Scene newScene = new Scene(newParent);
        window.setScene(newScene);
        window.show();
    }

    private void setCellAccountment() {
        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        customerPriceCol.setCellValueFactory(new PropertyValueFactory<>("customerPrice"));
        carrierPriceCol.setCellValueFactory(new PropertyValueFactory<>("carrierPrice"));
        differenceCol.setCellValueFactory(new PropertyValueFactory<>("difference"));
    }

    private void setCellManager() {
        managerIDCol.setCellValueFactory(new PropertyValueFactory<>("managerID"));
        managerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadData() {
        conn = connection.connect();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT tripID, customerPrice, carrierPrice FROM Trip");
            ResultSet rs = pst.executeQuery();
            double profit;
            double customerPr;
            double carrierPr;
            while (rs.next()) {
                customerPr = rs.getDouble("customerPrice");
                carrierPr = rs.getDouble("carrierPrice");
                profit = customerPr - carrierPr;
                accountmentData.add(new Trip(rs.getInt("tripID"),
                        rs.getDouble("customerPrice"),
                        rs.getDouble("carrierPrice"), profit));
                customerPriceList.add(customerPr);
                profitList.add(profit);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        accountmentTableView.setItems(accountmentData);
    }

    private void loadManagerList() {
        conn = connection.connect();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT managerID, name FROM Manager");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                managerList.add(new Manager(rs.getInt("managerID"),
                        rs.getString("name")));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        managersTableView.setItems(managerList);
    }

    @FXML
    private void setProfitField() {
        int managerID = parseInt(managerIDField.getText());
        double profit = selectedManagerProfit(managerID);
        managerProfitField.setText(String.format("%,.2f", profit));
        double turnover = selectedManagerTurnover(managerID);
        managerTurnoverField.setText(String.format("%,.2f", turnover));
        double totalProfit = totalProfit(profitList);
        totalProfitField.setText(String.format("%,.2f", totalProfit));
        double totalTurnover = totalTurnover(customerPriceList);
        totalTurnoverField.setText(String.format("%,.2f", totalTurnover));

    }

    private double selectedManagerProfit(int managerID) {
        double managerProfit = 0.0;
        double customerPr;
        double carrierPr;
        try {
            conn = connection.connect();
            PreparedStatement pst = conn.prepareStatement("SELECT customerPrice, carrierPrice FROM Trip WHERE managerID=" + managerID);
            ResultSet rs = pst.executeQuery();
            double partialProfit;
            while (rs.next()) {
                customerPr = rs.getDouble("customerPrice");
                carrierPr = rs.getDouble("carrierPrice");
                partialProfit = customerPr - carrierPr;
                managerProfit += partialProfit;
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return managerProfit;
    }

    private double selectedManagerTurnover(int managerID) {
        double managerTurnover = 0.0;
        try {
            conn = connection.connect();
            PreparedStatement pst = conn.prepareStatement("SELECT customerPrice FROM Trip WHERE managerID=" + managerID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                managerTurnover += rs.getDouble("customerPrice");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return managerTurnover;
    }

    ArrayList<Double> list1 = new ArrayList<>();

    public double totalProfit(ArrayList profitList) {
        list1 = profitList;
        double profit = 0;
        for (Double part : list1) {
            profit = profit + part;
        }
        return profit;
    }

    ArrayList<Double> list2 = new ArrayList<>();

    public double totalTurnover(ArrayList turnoverList) {
        list2 = customerPriceList;
        double turnover = 0.0;
        for (Double part : list2) {
            turnover += part;
        }
        return turnover;
    }
}
