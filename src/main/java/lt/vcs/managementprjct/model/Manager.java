package lt.vcs.managementprjct.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Manager {
    private SimpleIntegerProperty managerID;
    private SimpleStringProperty password;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amountOfCargos;
    private SimpleDoubleProperty turnover;
    private SimpleDoubleProperty profit;
    private SimpleDoubleProperty basicSalary;

    public Manager(int managerID, String name) {
        this.managerID = new SimpleIntegerProperty(managerID);
        this.name = new SimpleStringProperty(name);
    }

    public Manager(SimpleIntegerProperty managerID, SimpleStringProperty password, SimpleStringProperty name,
                   SimpleIntegerProperty amountOfCargos, SimpleDoubleProperty turnover, SimpleDoubleProperty profit,
                   SimpleDoubleProperty basicSalary) {
        this.managerID = managerID;
        this.password = password;
        this.name = name;
        this.amountOfCargos = amountOfCargos;
        this.turnover = turnover;
        this.profit = profit;
        this.basicSalary = basicSalary;
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

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAmountOfCargos() {
        return amountOfCargos.get();
    }

    public SimpleIntegerProperty amountOfCargosProperty() {
        return amountOfCargos;
    }

    public void setAmountOfCargos(int amountOfCargos) {
        this.amountOfCargos.set(amountOfCargos);
    }

    public double getTurnover() {
        return turnover.get();
    }

    public SimpleDoubleProperty turnoverProperty() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover.set(turnover);
    }

    public double getProfit() {
        return profit.get();
    }

    public SimpleDoubleProperty profitProperty() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit.set(profit);
    }

    public double getBasicSalary() {
        return basicSalary.get();
    }

    public SimpleDoubleProperty basicSalaryProperty() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary.set(basicSalary);
    }
}
