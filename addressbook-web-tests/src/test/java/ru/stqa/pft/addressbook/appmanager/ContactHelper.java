package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact() {
        fillContactForm(new ContactData("Mary", " ", "Chernikova", "Mary",
                "LC WVC", "St. Petersburg", "+79112257222", "123@mail.ru"));
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

    public void selectContact() {
        //click(By.xpath("//input[@name='selected[]']"));
        //click(By.cssSelector("[class=center] input"));
        click(By.xpath("//td/input"));

    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void closePopUp() {
        wd.switchTo().alert().accept();
    }

    public void initEditContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitEditContact() {
        click(By.name("update"));
    }

    public void selectDetails() {
        click(By.xpath("//img[@alt='Details']"));
    }

    public void selectVcard() {
        //click(By.xpath("//table[@id='maintable']/tbody/tr[8]/td[9]/a/img"));
        click(By.xpath("//img[@alt='vCard']"));
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


  /*  public void createNextContact(ContactData contact, boolean status) {
        fillContactForm(contact, status);
        submitContactCreation();
    }*/
}
