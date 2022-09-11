package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewPage();
            app.contact().createFirstContact();
        }
    }

    @Test(enabled = true)
    public void testEditContact() {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().edit(deletedContact);
        ContactData contact = new ContactData()
                .withId(deletedContact.getId()).withLastName("Loto").withFirstName("Igor").withAddress("St.Peterburg")
                .withEmail("12783@mail.ru").withMobileTel("+79217771437");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(deletedContact).withAdded(contact)));


    }

    @Test(enabled = true)
    public void testSelectDetailsToEditContact() {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForEdit(deletedContact);
        ContactData contact = new ContactData()
                .withId(deletedContact.getId()).withLastName("Vip").withFirstName("Andrey").withAddress("Minsk")
                .withEmail("123@mail.ru").withMobileTel("+79217771477");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();

        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(deletedContact).withAdded(contact)));
    }


    @Test(enabled = true)
    public void testSelectDetailsToDeleteContact() {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForDelete(deletedContact);
        app.goTo().homePage();

        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
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
