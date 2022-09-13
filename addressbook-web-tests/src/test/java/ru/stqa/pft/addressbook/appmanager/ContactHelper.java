package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createFirstContact() {
        create(new ContactData().withLastName("Semenovich").withFirstName("Anna").withAddress("")
                .withHomeTel("111").withMobileTel("2 33-3").withEmail("1_2@mail.ru").withEmail1("1-1@mail.ru"));
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

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email1 = wd.findElement(By.name("email2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withAddress(address).withHomeTel(home).withMobileTel(mobile).withEmail(email).withEmail1(email1);
    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomeTel());
        type(By.name("mobile"), contactData.getMobileTel());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail());
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
        // wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
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
            int id = Integer.parseInt(data.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = data.get(1).getText();
            String firstName = data.get(2).getText();
            String address = data.get(3).getText();
            String allEmail = data.get(4).getText();
            String allPhones = data.get(5).getText();
            contactCache.add(new ContactData()
                    .withId(id).withLastName(lastName).withFirstName(firstName).withAddress(address)
                    .withAllEmails(allEmail).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }


}
