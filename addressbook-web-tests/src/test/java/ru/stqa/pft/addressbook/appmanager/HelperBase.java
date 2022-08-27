package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver click;

    public HelperBase(WebDriver wd) {
        this.click = wd;
    }

    protected void click(By locator) {
        click.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            click.findElement(locator).clear();
            click.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
        try {
            click.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
