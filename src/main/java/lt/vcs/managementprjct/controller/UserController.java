package lt.vcs.managementprjct.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lt.vcs.managementprjct.services.ConnectionClass;

public abstract class UserController implements Initializable {

    protected Connection conn = null;
    protected PreparedStatement pst = null;
    protected ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connectionClass = new ConnectionClass();
        conn = connectionClass.connect1();
    }

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


