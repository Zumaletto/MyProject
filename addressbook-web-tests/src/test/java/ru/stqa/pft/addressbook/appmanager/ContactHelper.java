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

    public void createContact(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getNameOfCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobileTel());
        type(By.name("email"), contactData.getEmail());
//проверка условия пр  выборе группы
     /*   if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }*/
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("[class='center'] input")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closePopUp() {
        wd.switchTo().alert().accept();
    }

    public void initEditContact(int index) {

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

    public void selectModifiy() {
        click(By.name("modifiy"));
    }

    public void submitDeleteContact() {
        click(By.xpath("//form[2]/input[2]"));
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public int getContactCount() {
        return wd.findElements(By.cssSelector("[class='center'] input")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> data = element.findElements(By.tagName("td"));
            String firstName = data.get(1).getText();
            String lastName = data.get(2).getText();
            String address = data.get(3).getText();
            String phones = data.get(4).getText();
            String email = data.get(5).getText();
            String id = element.findElement(By.tagName("input")).getAttribute("value");
            ContactData contact = new ContactData(id, lastName, firstName, address, email, phones);
            contacts.add(contact);
        }
        return contacts;
    }
}
