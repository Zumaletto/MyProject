package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
            app.getContactHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initEditContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                "OLga", "Elovich", "St. Petersburg", "+79112257222", "123@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

    @Test
    public void testSelectDetailsToEditContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
            app.getContactHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().selectDetails();
        app.getContactHelper().selectModifiy();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                "Mary", "Chernikova", "St. Petersburg", "+79112257222", "123@mail.ru");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitEditContact();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testSelectDetailsToDeleteContact() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
            app.getContactHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().selectDetails();
        app.getContactHelper().selectModifiy();
        app.getContactHelper().submitDeleteContact();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

    }

    @Test
    public void testSelectVcardToDownload() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
            app.getContactHelper().returnToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().selectVcard();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }

}
