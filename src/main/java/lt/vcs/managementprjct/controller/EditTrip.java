package lt.vcs.managementprjct.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.model.Trip;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.sql.*;

public class EditTrip {
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    TripManagementDBConnection connection = new TripManagementDBConnection();
    private ObservableList<Trip> data;

    @FXML
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
    private TextArea driverContactsField;

    @FXML
    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent newParent = FXMLLoader.load(getClass().getResource("/editTrip.fxml"));
        Scene newScene = new Scene(newParent);
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void loadCurrentInfo() throws SQLException {
        conn = connection.connect();
        st = conn.createStatement();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM Trip WHERE tripID = " + tripIDField.getText());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tripIDField.setText(rs.getInt("tripID") + "");
                customerIDField.setText(rs.getInt("customerID") + "");
                managerIDField.setText(rs.getInt("managerID") + "");
                companyField.setText(rs.getString("company"));
                loadingPlaceField.setText(rs.getString("loadingPlace"));
                offloadingPlaceField.setText(rs.getString("loadingPlace"));
                loadingDateField.setText(rs.getString("loadingDate"));
                offloadingDateField.setText(rs.getString("offloadingDate"));
                customerPriceField.setText(rs.getDouble("customerPrice") + "");
                carrierPriceField.setText(rs.getDouble("carrierPrice") + "");
                driverContactsField.setText(rs.getString("driverContacts"));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void upload() throws SQLException {
        conn = connection.connect();
        st = conn.createStatement();
        String sql = "UPDATE Trip SET customerID = ?, managerID = ?, company = ?, " +
                "loadingPlace = ?, offloadingPlace = ?, loadingDate = ?," +
                "offloadingDate = ?, customerPrice = ?, carrierPrice = ?," +
                "driverContacts = ? WHERE tripID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Integer tripID = Integer.parseInt(tripIDField.getText());
            Integer customerID = Integer.parseInt(customerIDField.getText());
            Integer managerID = Integer.parseInt(managerIDField.getText());
            String company = companyField.getText();
            String loadingPlace = loadingPlaceField.getText();
            String offloadingPlace = offloadingPlaceField.getText();
            String loadingDate = loadingDateField.getText();
            String offloadingDate = offloadingDateField.getText();
            Double customerPrice = Double.parseDouble(customerPriceField.getText());
            Double carrierPrice = Double.parseDouble(carrierPriceField.getText());
            String driverContacts = driverContactsField.getText();

            pstmt.setInt(1, customerID);
            pstmt.setInt(2, managerID);
            pstmt.setString(3, company);
            pstmt.setString(4, loadingPlace);
            pstmt.setString(5, offloadingPlace);
            pstmt.setString(6, loadingDate);
            pstmt.setString(7, offloadingDate);
            pstmt.setDouble(8, customerPrice);
            pstmt.setDouble(9, carrierPrice);
            pstmt.setString(10, driverContacts);
            pstmt.setInt(11, tripID);
            pstmt.executeUpdate();
            AlertBox alertBox = new AlertBox();
            alertBox.display("SQL message", "Data base updated successfully");
            conn.close();
        } catch (NumberFormatException en) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("Wrong number format", "Repeat input. " + en.getMessage());
            System.out.println(en.getMessage());
        } catch (SQLException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("SQL connection", "No SQL connection or repeated data base line");
            System.out.println(e.getMessage());
        }
    }
}

