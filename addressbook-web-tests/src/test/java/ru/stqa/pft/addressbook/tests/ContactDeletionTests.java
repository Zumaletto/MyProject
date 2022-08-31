package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletionO() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Alena", " ", "Nikitina",
                    "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                    "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                    "1223@mail.ru", "test1"), true);
        }
            app.getContactHelper().selectContact();
            app.getContactHelper().deleteContact();
            app.getContactHelper().closePopUp();
            app.getNavigationHelper().gotoHomePage();

        }


    }
