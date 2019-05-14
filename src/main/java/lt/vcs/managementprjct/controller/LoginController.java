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

import java.sql.*;

public class LoginController {

    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @FXML
    private TextField loginIssue;
    @FXML
    private PasswordField passIssue;

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db", "root", "");
            st = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            connect();
            String sql = "SELECT managerID, password FROM Manager WHERE managerID='" + loginIssue.getText() + "'AND password='" + passIssue.getText() + "'";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                Parent logoutParent = FXMLLoader.load(getClass().getResource("/managerWindow.fxml"));
                Scene logoutScene = new Scene(logoutParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(logoutScene);
                window.show();

            } else {
                AlertBox alertBox = new AlertBox();
                alertBox.display("Alert window", "Wrong username or password");
                System.out.println("Access denied!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        loginIssue.setText("");
        passIssue.setText("");
    }

    private void userChoser() {
        if (loginIssue.equals("admin")) {
            //TODO
            // login.admin()
        } else {
            //TODO
            // login.manager()
        }
    }

    //TODO įdomu, kodėl nepavyksta autorizuotis per klasę ConnectionClass
/*     @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            con = connectionClass.connect();
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
