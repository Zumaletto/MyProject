package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String nameOfCompany;
    private final String address;
    private final String mobileTel;
    private final String email;
    public ContactData(String firstName, String middleName, String lastName, String nickName, String nameOfCompany,
                       String address, String mobileTel, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.mobileTel = mobileTel;
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

    public String getNickName() {
        return nickName;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public String getEmail() {
        return email;
    }


}
