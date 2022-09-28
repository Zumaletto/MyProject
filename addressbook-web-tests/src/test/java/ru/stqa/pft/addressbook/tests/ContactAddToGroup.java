package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactAddToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contact().size() == 0) {
            app.goTo().addNewPage();
            app.contact().createFirstContact();
        }
    }

    @Test(enabled = true)
    public void testContactAddToGroup() throws Exception {
        app.goTo().homePage();
        app.contact().clickAllGroup("[all]");
        ContactData contact = app.db().contact().stream().iterator().next();

        if (contact.getGroups().equals(app.db().groups())){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New group"));
            app.goTo().homePage();
        }

        app.contact().selectContactById(contact.getId());
        GroupData selectGroup = app.group().selectGroupToAdd(contact, app.db().groups());
        app.contact().clickGroupToAdd(selectGroup);
        app.contact().submitAddContactToGroup();
        Assert.assertTrue(app.db().getContactById(contact.getId()).getGroups().isPresented(selectGroup));
    }

}


