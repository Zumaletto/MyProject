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
        click.findElement(By.xpath("//input[21]"));
    }
    public void selectContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td/input"));
    }
    public void submitContactModification() {
        click(By.name("update"));
    }
    public void editContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td[8]/a/img"));
    }
    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closePopUp(){
        click.switchTo().alert().accept();
    }

    public void selectDetails() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td[7]/a/img"));
    }

    public void selectVcard() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td[9]/a/img"));
    }
}
