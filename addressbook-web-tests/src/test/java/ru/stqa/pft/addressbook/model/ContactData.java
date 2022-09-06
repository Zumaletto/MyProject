package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private  String firstName;
    private  String middleName;
    private  String lastName;
    private  String nickName;
    private  String nameOfCompany;
    private  String address;
    private  String mobileTel;
    private  String email;


    public ContactData(String firstName, String middleName, String lastName, String nickName, String nameOfCompany,
                       String address, String mobileTel, String email) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.nameOfCompany = nameOfCompany;
        this.address = address;
        this.mobileTel = mobileTel;
        this.email = email;
    }

    public ContactData(String firstName, String lastName, String address, String mobileTel, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileTel = mobileTel;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
