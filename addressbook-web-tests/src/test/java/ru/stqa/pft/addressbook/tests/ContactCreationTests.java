package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/vi1.jpg");
        ContactData contact = new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withMobileTel("+79217777777").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("qw@mail.ru").withEmail3("abv@mail.ru").withPhoto(photo);
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(enabled = true)
    public void testAddNextContact() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/vi1.jpg");
        ContactData contact = new ContactData()
                .withLastName("Pupkin").withFirstName("Alex").withAddress("St.Peterburg")
                .withHomeTel("+7(921)2-85").withMobileTel("+792177-77").withWorkTel("")
                .withEmail("123@mail.ru").withEmail2("").withEmail3("").withPhoto(photo);;
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

    @Test(enabled = true)
    public void testBadContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData()
                .withLastName("Petrov'").withFirstName("Anton").withAddress("Litva")
                .withHomeTel("+7 87285").withMobileTel("+792177-77").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("123@mail.ru").withEmail3("");
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }

}
