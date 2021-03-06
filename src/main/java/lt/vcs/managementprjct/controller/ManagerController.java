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
import lt.vcs.managementprjct.services.TripManagementDBConnection;

public class ManagerController extends UserController implements Initializable {
    @FXML
    private TableView<Trip> tripsTableView;
    @FXML
    private TableColumn<Trip, Integer> tripIdCol;
    @FXML
    private TableColumn<Trip, Integer> customerIdCol;
    @FXML
    private TableColumn<Trip, Integer> carrierIdCol;
    @FXML
    private TableColumn<Trip, String> companyCol;
    @FXML
    private TableColumn<Trip, String> contactPersonCol;
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
    private ObservableList<Trip> data;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TripManagementDBConnection connection = new TripManagementDBConnection();
        conn = connection.connect();
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
    public void callAccountment() throws IOException {
        TripsAccountment accountment = new TripsAccountment();
        accountment.display();
    }

    @FXML
    public void refresh() {
        conn = connection.connect();
        data = FXCollections.observableArrayList();
        setCell();
        loadDataFromDB();
    }

    protected void setCell() {
        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        contactPersonCol.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        carrierIdCol.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        loadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("loadingPlace"));
        offloadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("offloadingPlace"));
        loadingDateCol.setCellValueFactory(new PropertyValueFactory<>("loadingDate"));
        offloadingDateCol.setCellValueFactory(new PropertyValueFactory<>("offloadingDate"));
        customerPriceCol.setCellValueFactory(new PropertyValueFactory<>("customerPrice"));
        carrierPriceCol.setCellValueFactory(new PropertyValueFactory<>("carrierPrice"));
        driverContactsCol.setCellValueFactory(new PropertyValueFactory<>("driverContacts"));
    }

    protected void loadDataFromDB() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Trip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Trip(rs.getInt("tripID"), rs.getInt("customerID"),
                        rs.getString("company"), rs.getString("contactPerson"),
                        rs.getInt("carrierID"), rs.getString("loadingPlace"),
                        rs.getString("offloadingPlace"), rs.getString("loadingDate"),
                        rs.getString("offloadingDate"), rs.getDouble("customerPrice"),
                        rs.getDouble("carrierPrice"), rs.getString("driverContacts")));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        tripsTableView.setItems(data);
    }
}

