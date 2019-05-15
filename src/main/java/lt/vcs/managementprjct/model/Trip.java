package lt.vcs.managementprjct.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Trip {
    private SimpleIntegerProperty tripID;
    private SimpleIntegerProperty customerID;
    private SimpleIntegerProperty managerID;
    private SimpleStringProperty company;
    private SimpleStringProperty loadingPlace;
    private SimpleStringProperty offloadingPlace;
    private SimpleStringProperty loadingDate;
    private SimpleStringProperty offloadingDate;
    private SimpleDoubleProperty customerPrice;
    private SimpleDoubleProperty carrierPrice;
    private SimpleStringProperty driverContacts;

    public Trip() {

    }

    public Trip(Integer tripID, Integer customerID, Integer managerID, String company, String loadingPlace, String offloadingPlace,
                String loadingDate, String offloadingDate, Double customerPrice, Double carrierPrice, String driverContacts) {
        this.tripID = new SimpleIntegerProperty(tripID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.managerID = new SimpleIntegerProperty(managerID);
        this.company = new SimpleStringProperty(company);
        this.loadingPlace = new SimpleStringProperty(loadingPlace);
        this.offloadingPlace = new SimpleStringProperty(offloadingPlace);
        this.loadingDate = new SimpleStringProperty(loadingDate);
        this.offloadingDate = new SimpleStringProperty(offloadingDate);
        this.customerPrice = new SimpleDoubleProperty(customerPrice);
        this.carrierPrice = new SimpleDoubleProperty(carrierPrice);
        this.driverContacts = new SimpleStringProperty(driverContacts);
    }

    public int getTripID() {
        return tripID.get();
    }

    public SimpleIntegerProperty tripIDProperty(int tripID) {
        return this.tripID;
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public int getManagerID() {
        return managerID.get();
    }

    public SimpleIntegerProperty managerIDProperty() {
        return managerID;
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public String getLoadingPlace() {
        return loadingPlace.get();
    }

    public SimpleStringProperty loadingPlaceProperty() {
        return loadingPlace;
    }

    public String getOffloadingPlace() {
        return offloadingPlace.get();
    }

    public SimpleStringProperty offloadingPlaceProperty() {
        return offloadingPlace;
    }

    public String getLoadingDate() {
        return loadingDate.get();
    }

    public SimpleStringProperty loadingDateProperty() {
        return loadingDate;
    }

    public String getOffloadingDate() {
        return offloadingDate.get();
    }

    public SimpleStringProperty offloadingDateProperty() {
        return offloadingDate;
    }

    public double getCustomerPrice() {
        return customerPrice.get();
    }

    public SimpleDoubleProperty customerPriceProperty() {
        return customerPrice;
    }

    public double getCarrierPrice() {
        return carrierPrice.get();
    }

    public SimpleDoubleProperty carrierPriceProperty() {
        return carrierPrice;
    }

    public String getDriverContacts() {
        return driverContacts.get();
    }

    public SimpleStringProperty driverContactsProperty() {
        return driverContacts;
    }

}
