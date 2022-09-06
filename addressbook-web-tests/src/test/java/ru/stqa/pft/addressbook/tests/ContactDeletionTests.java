package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionO() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact();
            app.getContactHelper().returnToHomePage();
            app.getContactHelper().selectContact();
        } else {
            app.getContactHelper().selectContact();
        }
        app.getContactHelper().deleteContact();
        app.getContactHelper().closePopUp();
        app.getNavigationHelper().gotoHomePage();

    }


}