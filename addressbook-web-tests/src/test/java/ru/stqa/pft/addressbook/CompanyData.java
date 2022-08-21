package ru.stqa.pft.addressbook;

public class CompanyData {
    private final String nameOfCompany;
    private final String addressOfCompany;

    public CompanyData(String nameOfCompany, String addressOfCompany) {
        this.nameOfCompany = nameOfCompany;
        this.addressOfCompany = addressOfCompany;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public String getAddressOfCompany() {
        return addressOfCompany;
    }
}
