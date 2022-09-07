package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

    @Test
    public void testAddNextContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("Alena", null, null, null, null));
        app.getNavigationHelper().gotoAddNext();
        app.getContactHelper().fillContactForm(
                new ContactData("Liza", "Semenovich",
                        "St. Petersburg", "+79112257222", "123@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 2);
    }

}
