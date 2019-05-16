package lt.vcs.managementprjct.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.sql.*;

public class EditTripCarrier {
    @FXML
    private TextField tripIDField;
    @FXML
    private TextArea loadingPlaceField;
    @FXML
    private TextArea offloadingPlaceField;
    @FXML
    private TextField loadingDateField;
    @FXML
    private TextField offloadingDateField;
    @FXML
    private TextField carrierPriceField;
    @FXML
    private TextArea driverContactsField;
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @FXML
    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent parent = FXMLLoader.load(getClass().getResource("/editTripCarrier.fxml"));
        Scene scene = new Scene(parent);
        window.setScene(scene);
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
                loadingPlaceField.setText(rs.getString("loadingPlace"));
                offloadingPlaceField.setText(rs.getString("loadingPlace"));
                loadingDateField.setText(rs.getString("loadingDate"));
                offloadingDateField.setText(rs.getString("offloadingDate"));
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
        String sql = "UPDATE Trip SET loadingPlace = ?, offloadingPlace = ?, loadingDate = ?," +
                "offloadingDate = ?, carrierPrice = ?, driverContacts = ? WHERE tripID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int tripID = Integer.parseInt(tripIDField.getText());
            String loadingPlace = loadingPlaceField.getText();
            String offloadingPlace = offloadingPlaceField.getText();
            String loadingDate = loadingDateField.getText();
            String offloadingDate = offloadingDateField.getText();
            double carrierPrice = Double.parseDouble(carrierPriceField.getText());
            String driverContacts = driverContactsField.getText();

            pstmt.setString(1, loadingPlace);
            pstmt.setString(2, offloadingPlace);
            pstmt.setString(3, loadingDate);
            pstmt.setString(4, offloadingDate);
            pstmt.setDouble(5, carrierPrice);
            pstmt.setString(6, driverContacts);
            pstmt.setInt(7, tripID);
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



