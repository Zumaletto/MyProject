package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(
                new ContactData("Alena", " ", "Nikitina",
                        "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                        "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                        "1223@mail.ru","test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        app.getNavigationHelper().logout();
    }
    @Test
    public void testAddNext(){
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData( "Olesya", "Ivanovna", "Kirillova",
                "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
                "+78123458675", "+79112222222", "+7812754896", "+78123458675",
                "1223@mail.ru","test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoAddNext();
        app.getContactHelper().fillContactForm(new ContactData( "Mary", " ", "Chernikova",
                "Mary", "LC WVC", "St. Petersburg",
                " ", "+79112257222",  "", "+78123458675", " ", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
        app.getNavigationHelper().logout();
    }


}

