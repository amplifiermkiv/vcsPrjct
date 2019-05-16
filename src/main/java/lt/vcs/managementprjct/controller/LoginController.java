package lt.vcs.managementprjct.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;
import lt.vcs.managementprjct.services.TripManagementDBConnection;

import java.sql.*;

public class LoginController {
    @FXML
    private TextField loginIssue;
    @FXML
    private PasswordField passIssue;
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    TripManagementDBConnection connection = new TripManagementDBConnection();

    @FXML
    private void login(ActionEvent event) {
        try {
            AlertBox alertBox = new AlertBox();
            String alertTitle = "Alert window";
            String alertMessage = "Wrong username or password";
            conn = connection.connect();
            st = conn.createStatement();
            if (loginIssue.getLength() == 5) {
                String sql = "SELECT carrierID, password FROM Carriers WHERE carrierID='" + loginIssue.getText() + "'AND password='" + passIssue.getText() + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    Parent logoutParent = FXMLLoader.load(getClass().getResource("/carrierWindow.fxml"));
                    Scene logoutScene = new Scene(logoutParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(logoutScene);
                    window.show();
                } else {
                    alertBox.display(alertTitle, alertMessage);
                }
            } else if (loginIssue.getLength() == 4) {
                String sql = "SELECT managerID, password FROM Manager WHERE managerID='" + loginIssue.getText() + "'AND password='" + passIssue.getText() + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    Parent logoutParent = FXMLLoader.load(getClass().getResource("/managerWindow.fxml"));
                    Scene logoutScene = new Scene(logoutParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(logoutScene);
                    window.show();
                } else {
                    alertBox.display(alertTitle, alertMessage);
                }
            } else {
                alertBox.display(alertTitle, alertMessage);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {
        loginIssue.setText("");
        passIssue.setText("");
    }
}