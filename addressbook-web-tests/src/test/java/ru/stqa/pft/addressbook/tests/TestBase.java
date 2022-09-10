package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {
    //Запуск через Firefox
    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
//Запуск через Chrome
  //  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
//Запуск через IE
    //  protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }

}
