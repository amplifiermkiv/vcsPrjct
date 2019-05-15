package lt.vcs.managementprjct.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TripsAccountment implements Initializable{
    private ObservableList<Trip> accountmentData;// = new ArrayList<Double>();
    //public ArrayList<Double> carrPrice;
    //Double results[][];
    TripManagementDBConnection connection = new TripManagementDBConnection();
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @FXML
    private TableView<Trip> accountmentTableView;
    @FXML
    private TableColumn<Trip, Integer> tripIdCol;
    @FXML
    private TableColumn<Trip, Double> customerPriceCol;
    @FXML
    private TableColumn<Trip, Double> carrierPriceCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TripManagementDBConnection connection = new TripManagementDBConnection();
        conn = connection.connect();
        accountmentData = FXCollections.observableArrayList();
        setCell();
        loadData();
    }

    private void setCell() {
        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        customerPriceCol.setCellValueFactory(new PropertyValueFactory<>("customerPrice"));
        carrierPriceCol.setCellValueFactory(new PropertyValueFactory<>("carrierPrice"));
    }

    public void loadData() {
        conn = connection.connect();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT tripID, customerPrice, carrierPrice FROM Trip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                accountmentData.add(new Trip(rs.getInt("tripID"),
                        rs.getDouble("customerPrice"),
                        rs.getDouble("carrierPrice")));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


/*    public void printSarasas() {
        System.out.println();
        System.out.format("%16s%9s%13s%10s\n", "", "Chemija", "Matematika", "Fizika");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < mokiniai.size(); i++) {
            System.out.format("%-16s%6d%12d%12d\n", mokiniai.get(i), mokiniuChemija.get(i), mokiniuMatematika.get(i), mokiniuFizika.get(i));
        }
        System.out.println("-----------------------------------------------------");
    }*/
}
