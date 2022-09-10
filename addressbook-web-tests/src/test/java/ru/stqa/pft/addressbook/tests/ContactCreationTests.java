package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();

        app.goTo().addNewPage();
        ContactData contact = new ContactData(
                "Petrov", "Anton", "Litva", "123@mail.ru", "+79217777777");
        app.contact().create(contact);
        app.contact().returnToHomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = true)
    public void testAddNextContact() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();

        app.goTo().addNewPage();
        ContactData contact = new ContactData(
                "Pupkin", "Alex", "St.Peterburg", "123@mail.ru", "+79217771477");
        app.contact().create(contact);
        app.goTo().addNextContact();
        ContactData contactNext = new ContactData(
                "Ivanova", "Olga", "Riga", "123@mail.ru", "+79217788777");
        app.contact().create(contactNext);
        app.contact().returnToHomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 2);
    }

}
