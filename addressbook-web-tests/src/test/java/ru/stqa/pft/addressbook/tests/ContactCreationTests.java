package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContact() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/vi1.jpg");
        list.add(new Object[]{new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withMobileTel("+79217777777").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("qw@mail.ru").withEmail3("abv@mail.ru").withPhoto(photo)});
        list.add(new Object[]{new ContactData()
                .withLastName("Pupkin").withFirstName("Alex").withAddress("St.Peterburg")
                .withHomeTel("+7(921)2-85").withMobileTel("+792177-77").withWorkTel("")
                .withEmail("123@mail.ru").withEmail2("").withEmail3("").withPhoto(photo)});
        return list.iterator();
    } //итератор массивов объектов

    @DataProvider//провайдер тестовых данных
    public Iterator<Object[]> invalidContact() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData()
                .withLastName("Petrov'").withFirstName("Anton").withAddress("Litva")
                .withHomeTel("+7 87285").withMobileTel("+792177-77").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("123@mail.ru").withEmail3("")});
        return list.iterator();
    }

    @Test(dataProvider = "validContact")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test(dataProvider = "validContact")
    public void testAddNextContact(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
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

    @Test(dataProvider = "invalidContact")
    public void testBadContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }

}
