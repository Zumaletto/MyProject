package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ResetUserPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws Exception {
        long now = System.currentTimeMillis();
        UserData admin = app.db().getUserByUsername(app.getProperty("web.adminLogin"));
        Users allUsers = app.db().users().without(admin);
        UserData selectedUser = null;
        List<MailMessage> mailMessages;
        String confirmationLink = "";
        if (allUsers.size() < 1) {
            selectedUser = new UserData().withEmail(String.format("user%s@localhost.localdomain", now)).withUsername(String.format("user%s", now));
            app.registration().start(selectedUser.getUsername(), selectedUser.getEmail());
            mailMessages = app.mail().waitForMail(2, 10000);
            confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
            app.registration().finish(confirmationLink, "password");
        } else {
            selectedUser = allUsers.stream().iterator().next();
        }

        app.resetPassword().loginAdmin(admin.getUsername(), app.getProperty("web.adminPassword"));
        app.resetPassword().start(selectedUser);
        mailMessages = app.mail().waitForMail(1, 10000);
        confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
        final String newPassword = "newPassword";
        app.resetPassword().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
