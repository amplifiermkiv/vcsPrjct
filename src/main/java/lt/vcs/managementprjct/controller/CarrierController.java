package lt.vcs.managementprjct.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CarrierController extends ManagerController {
    @FXML
    private TableView<Trip> tripsTableView;
    @FXML
    private TableColumn<Trip, Integer> tripIdCol;
    @FXML
    private TableColumn<Trip, Integer> customerIdCol;
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

    @Override
    public void newTrip() {
        AlertBox alertBox = new AlertBox();
        alertBox.display("Warning", "Carrier can't create new trips!");
    }

    @Override
    public void removeTrip() {
        AlertBox alertBox = new AlertBox();
        alertBox.display("Warning", "Carrier can't remove trips!");
    }

    @Override
    public void editTrip() throws IOException {
        EditTripCarrier editTrip = new EditTripCarrier();
        editTrip.display();
    }

    @FXML
    @Override
    public void refresh() {
        conn = connection.connect();
        data = FXCollections.observableArrayList();
        setCell();
        loadDataFromDB();
    }

    @Override
    public void callAccountment() {
        AlertBox alertBox = new AlertBox();
        alertBox.display("Warning", "Carrier can't call accountment!");
    }

    @Override
    protected void setCell() {
        tripIdCol.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        loadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("loadingPlace"));
        offloadingPlaceCol.setCellValueFactory(new PropertyValueFactory<>("offloadingPlace"));
        loadingDateCol.setCellValueFactory(new PropertyValueFactory<>("loadingDate"));
        offloadingDateCol.setCellValueFactory(new PropertyValueFactory<>("offloadingDate"));
        carrierPriceCol.setCellValueFactory(new PropertyValueFactory<>("carrierPrice"));
        driverContactsCol.setCellValueFactory(new PropertyValueFactory<>("driverContacts"));
    }

    @Override
    protected void loadDataFromDB() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Trip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Trip(rs.getInt("tripID"), rs.getInt("customerID"),
                        rs.getString("company"), rs.getString("loadingPlace"),
                        rs.getString("offloadingPlace"), rs.getString("loadingDate"),
                        rs.getString("offloadingDate"), rs.getDouble("carrierPrice"),
                        rs.getString("driverContacts")));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        tripsTableView.setItems(data);
    }
}
