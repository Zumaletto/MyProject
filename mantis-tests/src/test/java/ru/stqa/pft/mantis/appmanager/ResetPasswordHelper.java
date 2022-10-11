package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAdmin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        wd.findElement(By.cssSelector("input[value='Вход']")).click();
        type(By.name("password"), password);
        wd.findElement(By.cssSelector("input[value='Вход']")).click();
    }

    public void start(UserData selectedUser) {
        click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
        click(By.xpath("//a[contains(text(),'Управление пользователями')]"));
        click(By.xpath("//a[text()='" + selectedUser.getUsername() + "']"));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finish(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span['Изменить пользователя']"));
    }
}
