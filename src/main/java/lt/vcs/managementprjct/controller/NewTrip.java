package lt.vcs.managementprjct.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.sql.*;

public class NewTrip {
    @FXML
    private Button saveBtn;
    @FXML
    private TextField tripIDField;
    @FXML
    private TextField customerIDField;
    @FXML
    private TextField managerIDField;
    @FXML
    private TextField carrierIDField;
    @FXML
    private TextArea companyField;
    @FXML
    private TextArea contactPersonField;
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
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @FXML
    void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent parent = FXMLLoader.load(getClass().getResource("/newTrip.fxml"));
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void upload() throws SQLException {
        AlertBox alertBox = new AlertBox();
        int forCheckID = Integer.parseInt(managerIDField.getText());
        if (managerCheck(forCheckID)) {
            conn = connection.connect();
            st = conn.createStatement();
            String sql = "INSERT INTO Trip(tripID, customerID, carrierID, managerID, company, " +
                    "loadingPlace, offloadingPlace, loadingDate, offloadingDate, customerPrice, " +
                    "carrierPrice, contactPerson) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                int tripID = Integer.parseInt(tripIDField.getText());
                int customerID = Integer.parseInt(customerIDField.getText());
                int carrierID = Integer.parseInt(carrierIDField.getText());
                int managerID = Integer.parseInt(managerIDField.getText());
                String company = companyField.getText();
                String loadingPlace = loadingPlaceField.getText();
                String offloadingPlace = offloadingPlaceField.getText();
                String loadingDate = loadingDateField.getText();
                String offloadingDate = offloadingDateField.getText();
                double customerPrice = Double.parseDouble(customerPriceField.getText());
                double carrierPrice = Double.parseDouble(carrierPriceField.getText());
                String contactPerson = contactPersonField.getText();

                pstmt.setInt(1, tripID);
                pstmt.setInt(2, customerID);
                pstmt.setInt(3, carrierID);
                pstmt.setInt(4, managerID);
                pstmt.setString(5, company);
                pstmt.setString(6, loadingPlace);
                pstmt.setString(7, offloadingPlace);
                pstmt.setString(8, loadingDate);
                pstmt.setString(9, offloadingDate);
                pstmt.setDouble(10, customerPrice);
                pstmt.setDouble(11, carrierPrice);
                pstmt.setString(12, contactPerson);

                pstmt.executeUpdate();
                alertBox.display("SQL message", "Data base updated successfully");
                conn.close();
            } catch (NumberFormatException en) {
                alertBox.display("Wrong number format", "Repeat input. " + en.getMessage());
                System.out.println(en.getMessage());
            } catch (SQLException e) {
                alertBox.display("SQL connection", "No SQL connection or repeated data base line");
                System.out.println(e.getMessage());
            }
        } else {
            alertBox.display("Warning ", "There is no such manager!. ");
        }
    }

    private boolean managerCheck(int managerID) throws SQLException {
        boolean checkedID = false;
        conn = connection.connect();
        st = conn.createStatement();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT managerID FROM Manager WHERE managerID = " + managerID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                checkedID = true;
            }
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return checkedID;
    }
}
