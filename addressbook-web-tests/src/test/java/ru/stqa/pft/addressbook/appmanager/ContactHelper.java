package ru.stqa.pft.addressbook.appmanager;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createFirstContact() {
        create(new ContactData().withLastName("Semenovich").withFirstName("Anna"));
        returnToHomePage();
    }

    public void create(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
    }

    public void createEdit(ContactData contact) {
        fillContactForm(contact);
        submitEditContact();
        contactCache = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        closePopUp();
        contactCache = null;
    }

    public void edit(ContactData contact) {
        selectContactById(contact.getId());
        clickEditById(contact.getId());
        contactCache = null;
    }

    public void seeDetailsForEdit(ContactData contact) {
        selectContactById(contact.getId());
        selectDetailsById(contact.getId());
        selectModify();
        contactCache = null;
    }

    public void seeDetailsForDelete(ContactData contact) {
        selectContactById(contact.getId());
        selectDetailsById(contact.getId());
        selectModify();
        submitDeleteContact();
        contactCache = null;
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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closePopUp() {
        wd.switchTo().alert().accept();
    }

    public void clickEditById(int id) {

        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public void selectDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
    }

    public void selectVcard(int id) {
        wd.findElement(By.cssSelector("a[href='vcard.php?id=" + id + "']")).click();
    }

    public void selectModify() {
        wd.findElement(By.cssSelector("input[value='Modify']")).click();
        //click(By.name("Modify"));
    }

    public void submitDeleteContact() {
        click(By.xpath("//form[2]/input[2]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> data = element.findElements(By.tagName("td"));
            String lastName = data.get(1).getText();
            String firstName = data.get(2).getText();
            String address = data.get(3).getText();
            String email = data.get(4).getText();
            String phones = data.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData()
                    .withId(id).withLastName(lastName).withFirstName(firstName).withAddress(address).withEmail(email).withMobileTel(phones));
        }
        return new Contacts(contactCache);
    }

}
