package lt.vcs.managementprjct.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.ConnectionClass;


public class ManagerController extends UserControllerTest implements Initializable {

    private ObservableList<Trip> data;

    @FXML
    private TableView<Trip> tableView;
    @FXML
    private TableColumn<Object, String> tripIdCol;
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

/*    @FXML
    private void handle (ActionEvent event){
        String sql="INSERT INTO Trip (tripID, customerID, managerID, company) VALUES (?,?,?,?)";

    }*/

    @FXML
    private BorderPane rootPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connectionClass = new ConnectionClass();
        conn = connectionClass.connect();
        data = FXCollections.observableArrayList();
        setCell();
        loadDataFromDB();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException, SQLException {
        conn.close();
        Parent logoutParent = FXMLLoader.load(getClass().getResource("/loginWindow.fxml"));
        Scene logoutScene = new Scene(logoutParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logoutScene);
        window.show();
    }


/*    @FXML
    private TextField tripIDField;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField managerIDField;
    @FXML
    private TextField companyField;
    @FXML
    private TextArea loadingPlaceField;
    @FXML
    private TextArea offloadingPlaceField;
    @FXML
    private TextField loadingDateField;
    @FXML
    private TextField offloadingDateField;
    @FXML
    private TextField customerPriceField;
    @FXML
    private TextField carrierPriceField;
    @FXML
    private TextArea driverContactsField;*/


    @FXML
    public void addNewTrip(ActionEvent event1) throws IOException {
/*        Parent newParent = FXMLLoader.load(getClass().getResource("/newTrip.fxml"));
        Scene newScene = new Scene(newParent);
        Stage window = (Stage) ((Node) event1.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();*/
        NewTrip newTrip = new NewTrip();
        newTrip.display();

/*        Integer tripID = Integer.parseInt(tripIDField.getText());
        Integer customerID = Integer.parseInt(customerIDField.getText());
        Integer managerID = Integer.parseInt(managerIDField.getText());
        String company = companyField.getText();
        String loadingPlace = loadingPlaceField.getText();
        String offloadingPlace = offloadingPlaceField.getText();
        String loadingDate = loadingDateField.getText();
        String offloadingDate = offloadingDateField.getText();
        Double customerPrice = Double.parseDouble(customerPriceField.getText());
        Double carrierPrice = Double.parseDouble(carrierPriceField.getText());
        String driverContacts = driverContactsField.getText();*/
        //uploadDBNewTrip(tripID, customerID,managerID,company,loadingPlace,offloadingPlace,loadingDate,offloadingDate,customerPrice,carrierPrice,driverContacts);

    }


    @FXML
    public void uploadDBNewTrip(int tripID, int customerID, int managerID, String company,
                                String loadingPlace, String offloadingPlace, String loadingDate,
                                String offloadingDate, double customerPrice, double carrierPrice,
                                String driverContacts) {

        String sql = "INSERT INTO Trip(tripID, customerID, managerID, company, " +
                "loadingPlace, offloadingPlace, loadingDate," +
                "offloadingDate, customerPrice, carrierPrice," +
                "driverContacts) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.conn;
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, tripID);
            pst.setInt(2, customerID);
            pst.setInt(3, managerID);
            pst.setString(4, company);
            pst.setString(5, loadingPlace);
            pst.setString(6, offloadingPlace);
            pst.setString(7, loadingDate);
            pst.setString(8, offloadingDate);
            pst.setDouble(9, customerPrice);
            pst.setDouble(10, carrierPrice);
            pst.setString(11, driverContacts);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tableView.setItems(data);
    }

    public void refresh(ActionEvent event) {
    }

    public void remove(ActionEvent event) {
    }

    public void removeTrip(ActionEvent event) {
    }

    public void editTrip(ActionEvent event) {
    }

    public void newTrip(ActionEvent event) {
    }

/*    @FXML
    private void update(int ID, ActionEvent event) {


    }


    @FXML
    private void removeTrip(ActionEvent event) {

    }*/
}

