package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createFirstContact() {
        create(new ContactData(
                "Semenovich", "Anna", null, null, null));
        returnToHomePage();
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public void createEdit(ContactData contact) {
        fillContactForm(contact);
        submitEditContact();
    }

    public void delete(int index) {
        select(index);
        deleteContact();
        closePopUp();
    }

    public void edit(int index) {
        select(index);
        clickEdit(index);
    }

    public void seeDetailsForEdit(int index) {
        select(index);
        selectDetails(index);
        selectModify();
    }

    public void seeDetailsForDelete(int index) {
        select(index);
        selectDetails(index);
        selectModify();
        submitDeleteContact();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobileTel());
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void select(int index) {
        wd.findElements(By.cssSelector("[class='center'] input")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closePopUp() {
        wd.switchTo().alert().accept();
    }

    public void clickEdit(int index) {

        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public void selectDetails(int index) {
        wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
    }

    public void selectVcard(int index) {
        wd.findElements(By.xpath("//img[@alt='vCard']")).get(index).click();
    }

    public void selectModify() {
        click(By.name("modifiy"));
    }

    public void submitDeleteContact() {
        click(By.xpath("//form[2]/input[2]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> data = element.findElements(By.tagName("td"));
            String lastName = data.get(1).getText();
            String firstName = data.get(2).getText();
            String address = data.get(3).getText();
            String email = data.get(4).getText();
            String phones = data.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, lastName, firstName, address, email, phones);
            contacts.add(contact);
        }
        return contacts;
    }
}
