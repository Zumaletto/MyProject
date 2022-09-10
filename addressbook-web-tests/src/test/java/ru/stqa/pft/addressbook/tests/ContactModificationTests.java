package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().addNewPage();
            app.contact().createFirstContact();
        }
    }

    @Test(enabled = true)
    public void testEditContact() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().edit(index);
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withLastName("Loto").withFirstName("Igor").withAddress("St.Peterburg")
                .withEmail("12783@mail.ru").withMobileTel("+79217771437");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

    @Test(enabled = true)
    public void testSelectDetailsToEditContact() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().seeDetailsForEdit(index);
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withLastName("Vip").withFirstName("Andrey").withAddress("Minsk")
                .withEmail("123@mail.ru").withMobileTel("+79217771477");
        app.contact().createEdit(contact);
        app.contact().returnToHomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


    @Test(enabled = true)
    public void testSelectDetailsToDeleteContact() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().seeDetailsForDelete(index);
        app.goTo().homePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


    @Test(enabled = true)
    public void testSelectVcardToDownload() {

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().select(index);
        app.contact().selectVcard(index);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
    }

}
