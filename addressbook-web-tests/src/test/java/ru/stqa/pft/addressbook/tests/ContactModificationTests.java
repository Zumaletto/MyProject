package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

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

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().edit(deletedContact);
        ContactData contact = new ContactData()
                .withId(deletedContact.getId()).withLastName("Loto").withFirstName("Igor").withAddress("St.Peterburg")
                .withEmail("12783@mail.ru").withMobileTel("+79217771437");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(deletedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

    @Test(enabled = true)
    public void testSelectDetailsToEditContact() {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForEdit(deletedContact);
        ContactData contact = new ContactData()
                .withId(deletedContact.getId()).withLastName("Vip").withFirstName("Andrey").withAddress("Minsk")
                .withEmail("123@mail.ru").withMobileTel("+79217771477");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(deletedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }


    @Test(enabled = true)
    public void testSelectDetailsToDeleteContact() {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().seeDetailsForDelete(deletedContact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


    @Test(enabled = true)
    public void testSelectVcardToDownload() {

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().selectContactById(deletedContact.getId());
        app.contact().selectVcard(deletedContact.getId());

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(before, after);

       /* List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().select(index);
        app.contact().selectVcard(index);

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());*/
    }

}
