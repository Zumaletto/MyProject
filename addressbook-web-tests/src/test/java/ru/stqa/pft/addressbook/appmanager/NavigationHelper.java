package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if (!isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoAddNewPage() {
        if (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")){
    return;
        }
            click(By.linkText("add new"));
    }

    public void gotoHomePage() {
       if (!isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void gotoAddNext() {

        click(By.linkText("add next"));
    }

   /*public void logout() {
        click(By.linkText("Logout"));
    }*/
}
