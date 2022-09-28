package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeleteFromGroup extends TestBase{

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

        GroupData selectGroup = app.db().groups().stream().iterator().next();
        ContactData selectContact = app.contact().selectContactToAdd(selectGroup, app.db().contact());

        app.contact().clickGroup(selectGroup);
        app.contact().selectContactById(selectContact.getId());
        app.contact().submitRemoveContactFromGroup();

        Assert.assertFalse(app.db().getContactById(selectContact.getId()).getGroups().isPresented(selectGroup));
    }
}
