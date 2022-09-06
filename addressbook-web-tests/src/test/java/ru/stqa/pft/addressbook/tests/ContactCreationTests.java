package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoAddNewPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void testAddNextContact() {
        app.getNavigationHelper().gotoAddNewPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact();
        app.getNavigationHelper().gotoAddNext();
        app.getContactHelper().fillContactForm(
                new ContactData("Elena", "Ivanovna", "Chernikova", "Mary",
                        "LC WVC", "St. Petersburg", "+79112257222", "123@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();

        Assert.assertEquals(after, before + 2);
    }

}
