package lt.vcs.managementprjct.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.ConnectionClass;

public class ManagerController extends UserController implements Initializable {

    private ObservableList<Trip> data;

    @FXML
    private TableView<Trip> tripsTableView;
    @FXML
    private TableColumn<Trip, Integer> tripIdCol;
    @FXML
    private TableColumn<Trip, Integer> customerIdCol;
    @FXML
    private TableColumn<Trip, Integer> managerIdCol;
    @FXML
    private TableColumn<Trip, String> companyCol;
    @FXML
    private TableColumn<Trip, String> loadingPlaceCol;
    @FXML
    private TableColumn<Trip, String> offloadingPlaceCol;
    @FXML
    private TableColumn<Trip, String> loadingDateCol;
    @FXML
    private TableColumn<Trip, String> offloadingDateCol;
    @FXML
    private TableColumn<Trip, Double> customerPriceCol;
    @FXML
    private TableColumn<Trip, Double> carrierPriceCol;
    @FXML
    private TableColumn<Trip, String> driverContactsCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connectionClass = new ConnectionClass();
        conn = connectionClass.connect();
        data = FXCollections.observableArrayList();
        setCell();
        loadDataFromDB();
    }

    @FXML
    public void newTrip() throws IOException {
        NewTrip newTrip = new NewTrip();
        newTrip.display();
    }

    @FXML
    public void removeTrip() throws IOException {
        RemoveTrip removeTrip = new RemoveTrip();
        removeTrip.display();
    }

    @FXML
    public void editTrip() throws IOException {
        EditTrip editTrip = new EditTrip();
        editTrip.display();
    }

    @FXML
    public void refresh() {
        ConnectionClass connectionClass = new ConnectionClass();
        conn = connectionClass.connect();
        data = FXCollections.observableArrayList();
        setCell();
        loadDataFromDB();
    }

    private void setCell() {
        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        managerIdCol.setCellValueFactory(new PropertyValueFactory<>("managerID"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        loadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("loadingPlace"));
        offloadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("offloadingPlace"));
        loadingDateCol.setCellValueFactory(new PropertyValueFactory<>("loadingDate"));
        offloadingDateCol.setCellValueFactory(new PropertyValueFactory<>("offloadingDate"));
        customerPriceCol.setCellValueFactory(new PropertyValueFactory<>("customerPrice"));
        carrierPriceCol.setCellValueFactory(new PropertyValueFactory<>("carrierPrice"));
        driverContactsCol.setCellValueFactory(new PropertyValueFactory<>("driverContacts"));
    }

    private void loadDataFromDB() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Trip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Trip(rs.getInt("tripID"), rs.getInt("customerID"),
                        rs.getInt("managerID"), rs.getString("company"),
                        rs.getString("loadingPlace"), rs.getString("offloadingPlace"),
                        rs.getString("loadingDate"), rs.getString("offloadingDate"),
                        rs.getDouble("customerPrice"), rs.getDouble("carrierPrice"),
                        rs.getString("driverContacts")));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        tripsTableView.setItems(data);
    }
}

