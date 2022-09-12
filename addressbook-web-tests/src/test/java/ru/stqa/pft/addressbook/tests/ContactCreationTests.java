package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Litva").withEmail("123@mail.ru").withMobileTel("+79217777777");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test(enabled = true)
    public void testBadContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Petrov'").withFirstName("Anton").withAddress("Litva").withEmail("123@mail.ru").withMobileTel("+79217777777");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }

    @Test(enabled = true)
    public void testAddNextContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Pupkin").withFirstName("Alex").withAddress("St.Peterburg").withEmail("123@mail.ru").withMobileTel("+79217771477");
        app.contact().create(contact);
        app.goTo().addNextContact();
        ContactData contactNext = new ContactData()
                .withLastName("Ivanova").withFirstName("Olga").withAddress("Riga").withEmail("123@mail.ru").withMobileTel("+79217788777");
        app.contact().create(contactNext);
        app.contact().returnToHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 2);

    }

}
