package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (!isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void addNewPage() {
        if (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")){
    return;
        }
            click(By.linkText("add new"));
    }

    public void homePage() {
         click(By.linkText("home"));
    }

    public void addNextContact() {

        click(By.linkText("add next"));
    }

   /*public void logout() {
        click(By.linkText("Logout"));
    }*/
}
