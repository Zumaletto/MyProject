package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String nameOfCompany;
    private final String addressOfCompany;
    private final String homeTel;
    private final String mobileTel;
    private final String workTel;
    private final String faxTel;
    private final String email;

    public ContactData(String firstName, String middleName, String lastName, String nickName,
                       String nameOfCompany, String addressOfCompany, String homeTel,
                       String mobileTel, String workTel, String faxTel, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.nameOfCompany = nameOfCompany;
        this.addressOfCompany = addressOfCompany;
        this.homeTel = homeTel;
        this.mobileTel = mobileTel;
        this.workTel = workTel;
        this.faxTel = faxTel;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() { return nickName; }
    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public String getAddressOfCompany() {
        return addressOfCompany;
    }
    public String getHomeTel() {
        return homeTel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public String getWorkTel() {
        return workTel;
    }

    public String getFaxTel() {
        return faxTel;
    }

    public String getEmail() {
        return email;
    }
}
