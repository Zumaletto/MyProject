package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContact() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            xstream.allowTypes(new Class[]{ContactData.class});
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contact.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
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
        Contacts before = app.db().contact();
        app.goTo().addNewPage();
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contact();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testContactCreationWithPhoto() throws Exception {
        app.goTo().homePage();
        Contacts before = app.db().contact();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/vi1.jpg");
        ContactData contact = new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withMobileTel("+79217777777").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("qw@mail.ru").withEmail3("abv@mail.ru").withPhoto(photo);
        app.goTo().addNewPage();
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contact();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testAddNextContact() {
        app.goTo().homePage();
        Contacts before = app.db().contact();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/vi1.jpg");
        ContactData contact = new ContactData()
                .withLastName("Petrov").withFirstName("Anton").withAddress("Riga, Tytyh2ki street,17, 78549")
                .withHomeTel("+7 812 345 72 85").withMobileTel("+79217777777").withWorkTel("8(812)55-33-15")
                .withEmail("123@mail.ru").withEmail2("qw@mail.ru").withEmail3("abv@mail.ru").withPhoto(photo);
        app.contact().create(contact);
        app.goTo().addNextContact();
        ContactData contactNext = new ContactData()
                .withLastName("Ivanova").withFirstName("Olga").withAddress("Riga")
                .withHomeTel("+7(921)275-85-87").withMobileTel("+792177-77").withEmail("123@mail.ru");
        app.contact().create(contactNext);
        app.contact().returnToHomePage();
        Contacts after = app.db().contact();
        assertEquals(after.size(), before.size() + 2);
    }

    @Test(dataProvider = "invalidContact")
    public void testBadContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.db().contact();
        app.goTo().addNewPage();
        app.contact().create(contact);
        app.contact().returnToHomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contact();
        assertThat(after, equalTo(before));

    }

}
