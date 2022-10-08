package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value = 'Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span['Изменить пользователя']"));
    }

    public void goToManageUserPage(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), username);
        wd.findElement(By.cssSelector("input[value='submit']")).click();
        type(By.name("password"), password);
        wd.findElement(By.cssSelector("input[value='submit']")).click();
        wd.findElement(By.cssSelector("a[href='manage_overview_page.php']")).click();
        wd.findElement(By.cssSelector("a[href='manage_user_page.php']")).click();
    }

    public void selectUser() {
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[class^=row]"));
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String userName = cells.get(0).getText();
            if (!userName.equals("administrator")) {
                cells.get(0).findElement(By.tagName("a")).click();
                return;
            }
        }
    }


}
