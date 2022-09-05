package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact();
            app.getContactHelper().returnToHomePage();
        } else {
            app.getContactHelper().selectContact();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initEditContact();
        app.getContactHelper().fillContactForm(
                new ContactData("Mary", " ", "Chernikova","Mary",
                        "LC WVC", "St. Petersburg","+79112257222", "123@mail.ru"));
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void testSelectDetailsToEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact();
            app.getContactHelper().returnToHomePage();
            app.getContactHelper().selectContact();
        } else {
            app.getContactHelper().selectContact();
        }
        app.getContactHelper().selectDetails();
        app.getContactHelper().selectModifiy();
        app.getContactHelper().fillContactForm(
                new ContactData("Mary", " ", "Chernikova","Mary",
                        "LC WVC", "St. Petersburg","+79112257222", "123@mail.ru"));
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void testSelectDetailsToDeleteContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact();
            app.getContactHelper().returnToHomePage();
            app.getContactHelper().selectContact();
        } else {
            app.getContactHelper().selectContact();
        }
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
            app.getContactHelper().createContact();
            app.getContactHelper().returnToHomePage();
            app.getContactHelper().selectContact();
        } else {
            app.getContactHelper().selectContact();
        }
        app.getContactHelper().selectVcard();
    }

}
