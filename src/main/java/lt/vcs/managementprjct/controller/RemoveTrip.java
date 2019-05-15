package lt.vcs.managementprjct.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;

import java.io.IOException;
import java.sql.*;

public class RemoveTrip {

    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    //private ResultSet rs = null;

    @FXML
    private TextField removeTripField;

    @FXML
    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent newParent = FXMLLoader.load(getClass().getResource("/removeTrip.fxml"));
        Scene newScene = new Scene(newParent);
        window.setScene(newScene);
        window.show();
    }

    private void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db", "root", "");
        st = conn.createStatement();
    }

    @FXML
    public void removeConfirmation() throws SQLException {
        connect();
        Integer tripID = Integer.parseInt(removeTripField.getText());
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
