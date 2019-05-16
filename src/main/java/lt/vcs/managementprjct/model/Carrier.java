package lt.vcs.managementprjct.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Carrier {
    private SimpleIntegerProperty carrierID;
    private SimpleStringProperty password;
    private SimpleStringProperty company;
    private SimpleStringProperty contactPerson;
    private SimpleStringProperty lorryType;
    private SimpleStringProperty phone;
    private SimpleStringProperty language;
    private SimpleIntegerProperty availability;

    public Carrier(SimpleIntegerProperty carrierID, SimpleStringProperty password, SimpleStringProperty company,
                   SimpleStringProperty contactPerson, SimpleStringProperty lorryType, SimpleStringProperty phone,
                   SimpleStringProperty language, SimpleIntegerProperty availability) {
        this.carrierID = carrierID;
        this.password = password;
        this.company = company;
        this.contactPerson = contactPerson;
        this.lorryType = lorryType;
        this.phone = phone;
        this.language = language;
        this.availability = availability;
    }

    public int getCarrierID() {
        return carrierID.get();
    }

    public SimpleIntegerProperty carrierIDProperty() {
        return carrierID;
    }

    public void setCarrierID(int carrierID) {
        this.carrierID.set(carrierID);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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

    public String getContactPerson() {
        return contactPerson.get();
    }

    public SimpleStringProperty contactPersonProperty() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson.set(contactPerson);
    }

    public String getLorryType() {
        return lorryType.get();
    }

    public SimpleStringProperty lorryTypeProperty() {
        return lorryType;
    }

    public void setLorryType(String lorryType) {
        this.lorryType.set(lorryType);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getLanguage() {
        return language.get();
    }

    public SimpleStringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public int getAvailability() {
        return availability.get();
    }

    public SimpleIntegerProperty availabilityProperty() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability.set(availability);
    }
}
