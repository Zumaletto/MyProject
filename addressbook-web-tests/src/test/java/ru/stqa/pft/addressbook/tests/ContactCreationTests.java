package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testCreateNewContact() throws Exception {

       app.getNavigationHelper().goToAddNewPage();
       app.getContactHelper().fillContactForm(new ContactData( "Olesya", "Ivanovna", "Kirillova",
               "niki", "LCA", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 19517",
               "+78123458675", "+79112222222", "+7812754896", "+78123458675", "1223@mail.ru"));
       app.getContactHelper().submitContactCreation();
       app.getNavigationHelper().goToHomePage();
       app.logOut();
    }




}