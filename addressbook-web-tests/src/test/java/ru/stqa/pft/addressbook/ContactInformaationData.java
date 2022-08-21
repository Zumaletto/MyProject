package ru.stqa.pft.addressbook;

public class ContactInformaationData {
    private final String homeTel;
    private final String mobileTel;
    private final String workTel;
    private final String faxTel;
    private final String email;

    public ContactInformaationData(String homeTel, String mobileTel, String workTel, String faxTel, String email) {
        this.homeTel = homeTel;
        this.mobileTel = mobileTel;
        this.workTel = workTel;
        this.faxTel = faxTel;
        this.email = email;
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
