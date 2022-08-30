package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(
                new ContactData("Alena", " ", "Nikitina",
                        "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                        "+78123458675", "+79112222222", "+7812754896", "+78123458675", "1223@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        app.getNavigationHelper().logout();
    }
    @Test
    public void testAddNext(){
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData( "Olesya", "Ivanovna", "Kirillova",
                "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                "+78123458675", "+79112222222", "+7812754896", "+78123458675", "1223@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoAddNext();
        app.getContactHelper().fillContactForm(new ContactData( "Mary", " ", "Chernikova",
                "Mary", "LC WVC", "St. Petersburg",
                " ", "+79112257222",  "", "+78123458675", " "));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        app.getNavigationHelper().logout();
    }


}

