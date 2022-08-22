package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Olesya", "Ivanovna", "Kirillova",
                "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                "+78123458675", "+79112222222", "+7812754896", "+78123458675", "1223@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().closePopUp();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testSeeDetailsContact() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectDetails();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testDownloadVcard() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectVcard();
    }
}
