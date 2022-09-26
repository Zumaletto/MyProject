package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contact().size() == 0) {
            app.goTo().addNewPage();
            app.contact().createFirstContact();
        }
    }

    @Test(enabled = true)
    public void testEditContact() {

        Contacts before = app.db().contact();
        ContactData deletedContact = before.iterator().next();
        app.contact().edit(deletedContact);
        ContactData contact = new ContactData().withId(deletedContact.getId())
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withMobileTel("+79217777777").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("qw@mail.ru").withEmail3("abv@mail.ru");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contact();
        assertThat(after, equalTo(before.withOut(deletedContact).withAdded(contact)));


    }

    @Test(enabled = true)
    public void testSelectDetailsToEditContact() {

        Contacts before = app.db().contact();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForEdit(deletedContact);
        ContactData contact = new ContactData().withId(deletedContact.getId())
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withWorkTel("8(812)55-33-15").withEmail("123@mail.ru").withEmail2("qw@mail.ru");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contact();
        assertThat(after, equalTo(before.withOut(deletedContact).withAdded(contact)));
    }


    @Test(enabled = true)
    public void testSelectDetailsToDeleteContact() {

        Contacts before = app.db().contact();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForDelete(deletedContact);
        app.goTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size() - 1));
        Contacts after = app.db().contact();

        assertThat(after, equalTo(before.withOut(deletedContact)));
    }


    @Test(enabled = true)
    public void testSelectVcardToDownload() {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().selectContactById(deletedContact.getId());
        app.contact().selectVcard(deletedContact.getId());

        Contacts after = app.contact().all();
        assertEquals(before, after);

    }

}
