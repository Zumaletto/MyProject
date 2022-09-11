package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Litva").withEmail("123@mail.ru").withMobileTel("+79217777777");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

        before.add(contact);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = true)
    public void testAddNextContact() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Pupkin").withFirstName("Alex").withAddress("St.Peterburg").withEmail("123@mail.ru").withMobileTel("+79217771477");
        app.contact().create(contact);
        app.goTo().addNextContact();
        ContactData contactNext = new ContactData()
                .withLastName("Ivanova").withFirstName("Olga").withAddress("Riga").withEmail("123@mail.ru").withMobileTel("+79217788777");
        app.contact().create(contactNext);
        app.contact().returnToHomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 2);

    }

}
