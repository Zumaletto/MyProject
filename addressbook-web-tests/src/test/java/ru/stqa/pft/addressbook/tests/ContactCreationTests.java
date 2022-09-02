package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact();
        app.getContactHelper().returnToHomePage();
    }

    @Test
    public void testAddNextContact() {
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact();
        app.getNavigationHelper().gotoAddNext();
        app.getContactHelper().fillContactForm(new ContactData("Mary", " ", "Chernikova",
                "Mary", "LC WVC", "St. Petersburg",
                " ", "+79112257222", "", "+78123458675", " ", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}

