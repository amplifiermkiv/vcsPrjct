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

    public SimpleIntegerProperty tripIDProperty() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID.set(tripID);
    }

    public int getCustomerID() {
        return customerID.get();
    }

    public SimpleIntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public int getManagerID() {
        return managerID.get();
    }

    public SimpleIntegerProperty managerIDProperty() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID.set(managerID);
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getLoadingPlace() {
        return loadingPlace.get();
    }

    public SimpleStringProperty loadingPlaceProperty() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace.set(loadingPlace);
    }

    public String getOffloadingPlace() {
        return offloadingPlace.get();
    }

    public SimpleStringProperty offloadingPlaceProperty() {
        return offloadingPlace;
    }

    public void setOffloadingPlace(String offloadingPlace) {
        this.offloadingPlace.set(offloadingPlace);
    }

    public String getLoadingDate() {
        return loadingDate.get();
    }

    public SimpleStringProperty loadingDateProperty() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate.set(loadingDate);
    }

    public String getOffloadingDate() {
        return offloadingDate.get();
    }

    public SimpleStringProperty offloadingDateProperty() {
        return offloadingDate;
    }

    public void setOffloadingDate(String offloadingDate) {
        this.offloadingDate.set(offloadingDate);
    }

    public double getCustomerPrice() {
        return customerPrice.get();
    }

    public SimpleDoubleProperty customerPriceProperty() {
        return customerPrice;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice.set(customerPrice);
    }

    public double getCarrierPrice() {
        return carrierPrice.get();
    }

    public SimpleDoubleProperty carrierPriceProperty() {
        return carrierPrice;
    }

    public void setCarrierPrice(double carrierPrice) {
        this.carrierPrice.set(carrierPrice);
    }

    public String getDriverContacts() {
        return driverContacts.get();
    }

    public SimpleStringProperty driverContactsProperty() {
        return driverContacts;
    }

    public void setDriverContacts(String driverContacts) {
        this.driverContacts.set(driverContacts);
    }
}
