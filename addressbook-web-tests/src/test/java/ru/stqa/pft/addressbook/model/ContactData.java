package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String lastName;
    private String firstName;
    private String address;
    private String email;
    private String email1;
    private String homeTel;
    private String mobileTel;
    private String allPhones;
    private String allEmails;

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
        return this;
    }

    public ContactData withHomeTel(String homeTel) {
        this.homeTel = homeTel;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeTel() {
        return homeTel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public String getEmail() {
        return email;
    }
    public String getEmail1() {
        return email1;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAllEmails() {
        return allEmails;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
        if (homeTel != null ? !homeTel.equals(that.homeTel) : that.homeTel != null) return false;
        if (mobileTel != null ? !mobileTel.equals(that.mobileTel) : that.mobileTel != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        return allEmails != null ? allEmails.equals(that.allEmails) : that.allEmails == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (homeTel != null ? homeTel.hashCode() : 0);
        result = 31 * result + (mobileTel != null ? mobileTel.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        return result;
    }
}
