package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoAddNewPage();
        ContactData contact = new ContactData(
                "Oleg", "Klichko", "Litva", "+1234587", "123@mail.ru");
        app.getContactHelper().createContact(contact);
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

    @Test
    public void testAddNextContact() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().gotoAddNewPage();
        ContactData contact = new ContactData(
                "Alex", "Lecenko", "Litva", "+1234587", "123@mail.ru");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().gotoAddNext();
        ContactData contactNext = new ContactData(
                "Liza", "Semenovich","St. Petersburg", "+79112257222", "123@mail.ru");
        app.getContactHelper().fillContactForm(contactNext);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 2);
    }

}
