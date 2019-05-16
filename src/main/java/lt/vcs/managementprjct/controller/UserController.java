package lt.vcs.managementprjct.controller;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class UserController {
    protected Connection conn = null;

    @FXML
    protected void logout(ActionEvent event) throws IOException, SQLException {
        conn.close();
        Parent logoutParent = FXMLLoader.load(getClass().getResource("/loginWindow.fxml"));
        Scene logoutScene = new Scene(logoutParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logoutScene);
        window.show();
    }
}


