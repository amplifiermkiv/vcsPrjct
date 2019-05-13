package lt.vcs.managementprjct.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lt.vcs.managementprjct.model.AlertBox;

import java.io.IOException;
import java.sql.*;

public class AddNew {

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

    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

/*    public Connection connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db", "root", "");
            st = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }*/

    public void display() throws IOException {

        Stage window = new Stage();
        Parent newParent = FXMLLoader.load(getClass().getResource("/newTrip.fxml"));
        Scene newScene = new Scene(newParent);
        window.setScene(newScene);
        window.show();

/*        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(270);
        window.setHeight(130);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();*/

    }

    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C://SQL/CargoDB.db", "root", "");
            st = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void upload() {
        connect();
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

        String sql = "INSERT INTO Trip(tripID, customerID, managerID, company, " +
                "loadingPlace, offloadingPlace, loadingDate," +
                "offloadingDate, customerPrice, carrierPrice," +
                "driverContacts) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tripID);
            pstmt.setInt(2, customerID);
            pstmt.setInt(3, managerID);
            pstmt.setString(4, company);
            pstmt.setString(5, loadingPlace);
            pstmt.setString(6, offloadingPlace);
            pstmt.setString(7, loadingDate);
            pstmt.setString(8, offloadingDate);
            pstmt.setDouble(9, customerPrice);
            pstmt.setDouble(10, carrierPrice);
            pstmt.setString(11, driverContacts);
            pstmt.executeUpdate();
            lt.vcs.managementprjct.model.AlertBox alertBox = new lt.vcs.managementprjct.model.AlertBox();
            alertBox.display("SQL message", "Data base updated successfully");

        } catch (SQLException e) {
            lt.vcs.managementprjct.model.AlertBox alertBox = new AlertBox();
            alertBox.display("SQL connection", "No SQL connection or repeated data base line");
            System.out.println(e.getMessage());
        }
    }

}
