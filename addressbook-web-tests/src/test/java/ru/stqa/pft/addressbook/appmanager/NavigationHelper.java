package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(WebDriver wd) {

        super(wd);
    }
    public void goToGroupPage() {
        click(By.linkText("groups"));
    }
    public void goToAddNext() {
        click(By.linkText("add next"));
    }
    public void goToAddNewPage() {
        click(By.xpath("//a[contains(@href, 'edit.php')]"));
    }
    public void goToHomePage() {
        click(By.linkText("home"));
    }

}
