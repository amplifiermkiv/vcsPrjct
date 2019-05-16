package lt.vcs.managementprjct.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.io.IOException;
import java.sql.*;

public class RemoveTrip {
    @FXML
    private TextField removeTripField;
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @FXML
    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent parent = FXMLLoader.load(getClass().getResource("/removeTrip.fxml"));
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void removeConfirmation() {
        conn = connection.connect();
        int tripID = Integer.parseInt(removeTripField.getText());
        String sql = "DELETE FROM Trip WHERE tripID = " + tripID;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            AlertBox alertBox = new AlertBox();
            alertBox.display("Remove info", "Record was removed successfully");
            conn.close();
        } catch (SQLException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("Remove info", "There is no such record " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
