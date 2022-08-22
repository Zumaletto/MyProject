package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void fillContactForm(ContactData contactData) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
        type(By.xpath("//input[@name='middlename']"), contactData.getMiddleName());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
        type(By.xpath("//input[@name='nickname']"), contactData.getNickName());
        type(By.name("company"), contactData.getNameOfCompany());
        type(By.name("address"), contactData.getAddressOfCompany());
        type(By.name("home"), contactData.getHomeTel());
        type(By.name("mobile"), contactData.getMobileTel());
        type(By.name("work"), contactData.getWorkTel());
        type(By.name("fax"), contactData.getFaxTel());
        type(By.name("email"), contactData.getEmail());
    }
    public void submitContactCreation() {
        click.findElement(By.xpath("//input[21]")).click();
    }


}
