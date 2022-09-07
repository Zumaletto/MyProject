package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String id;
    private  String firstName;
    private  String middleName;
    private  String lastName;
    private  String nickName;
    private  String nameOfCompany;
    private  String address;
    private  String mobileTel;
    private  String email;

    public ContactData( String firstName, String lastName, String address, String mobileTel, String email) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileTel = mobileTel;
        this.email = email;
    }


    public ContactData(String id, String firstName, String lastName, String address, String mobileTel, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobileTel = mobileTel;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
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
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobileTel='" + mobileTel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (mobileTel != null ? !mobileTel.equals(that.mobileTel) : that.mobileTel != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobileTel != null ? mobileTel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
