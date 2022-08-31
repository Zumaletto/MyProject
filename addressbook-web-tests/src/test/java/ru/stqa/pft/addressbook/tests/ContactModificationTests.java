package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", " ", "Nikitina",
                    "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                    "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                    "1223@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initEditContact();
        app.getContactHelper().fillContactForm(
                new ContactData("Elena", "Petrovna", "Dyeva",
                        " ", " ", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                        " ", "+79112222222", " ", " ", "1223@mail.ru", null), false);
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void testSelectDetailsToEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", " ", "Nikitina",
                    "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                    "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                    "1223@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().selectDetails();
        app.getContactHelper().selectModifiy();
        app.getContactHelper().fillContactForm(
                new ContactData("First", " ", "Last",
                        " ", " ", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                        " ", "+79112222222", " ", " ", "1223@mail.ru", "test1"), false);
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void testSelectDetailsToDeleteContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", " ", "Nikitina",
                    "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                    "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                    "1223@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().selectDetails();
        app.getContactHelper().selectModifiy();
        app.getContactHelper().submitDeleteContact();
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testSelectVcardToDownload() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", " ", "Nikitina",
                    "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                    "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                    "1223@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().selectVcard();
    }

}