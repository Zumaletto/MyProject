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
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+78123457285").withMobileTel("+79217777777").withEmail("123@mail.ru").withEmail("qw@mail.ru");
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
                .withLastName("Petrov").withFirstName("Anton").withAddress("Litva")
                .withHomeTel("+7 87285").withMobileTel("+792177-77").withEmail("123@mail.ru").withEmail1("123@mail.ru");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertEquals(app.contact().count(),before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }

    @Test(enabled = true)
    public void testAddNextContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Pupkin").withFirstName("Alex").withAddress("St.Peterburg")
                .withHomeTel("+7(921)2-85").withMobileTel("+792177-77").withEmail("123@mail.ru");
        app.contact().create(contact);
        app.goTo().addNextContact();
        ContactData contactNext = new ContactData()
                .withLastName("Ivanova").withFirstName("Olga").withAddress("Riga")
                .withHomeTel("+7(921)275-85-87").withMobileTel("+792177-77").withEmail("123@mail.ru");
        app.contact().create(contactNext);
        app.contact().returnToHomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 2);

    }

}
