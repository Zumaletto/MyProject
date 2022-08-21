package ru.stqa.pft.addressbook;

import java.time.Duration;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String login, String password) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(login);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testCreateNewContact() throws Exception {
        initContactCreation();
        fillInfoAboutName(new ContactNameData("1name", "middle name", "last name", "Kitty"));
        fillNameAndAddressOfTheCompany(
                new CompanyData("LCW", "Bolshaya Porokhovskaya str., 38, room 2, St. Petersburg, 195176"));
        fillContactInformation(
                new ContactInformaationData("+78123458675", "+79112222222", "+7812754896", "+78123458675", "1223@mail.ru"));
        selectGroupOnAddNewPage("JavaGroup");
        submitContactCreation();
        goToHomePage();
        selectGroupOnHomePage("JavaGroup");
        logout();
    }

    private void initContactCreation() {
        wd.findElement(By.xpath("//a[contains(@href, 'edit.php')]")).click();
    }

    private void fillInfoAboutName(ContactNameData contactNameData) {
        wd.findElement(By.xpath("//input[@name='firstname']")).click();
        wd.findElement(By.xpath("//input[@name='firstname']")).clear();
        wd.findElement(By.xpath("//input[@name='firstname']")).sendKeys(contactNameData.getFirstName());
        wd.findElement(By.xpath("//input[@name='middlename']")).click();
        wd.findElement(By.xpath("//input[@name='middlename']")).clear();
        wd.findElement(By.xpath("//input[@name='middlename']")).sendKeys(contactNameData.getMiddleName());
        wd.findElement(By.xpath("//input[@name='lastname']")).click();
        wd.findElement(By.xpath("//input[@name='lastname']")).clear();
        wd.findElement(By.xpath("//input[@name='lastname']")).sendKeys(contactNameData.getLastName());
        wd.findElement(By.xpath("//input[@name='nickname']")).click();
        wd.findElement(By.xpath("//input[@name='nickname']")).clear();
        wd.findElement(By.xpath("//input[@name='nickname']")).sendKeys(contactNameData.getNickName());
    }

    private void fillNameAndAddressOfTheCompany(CompanyData companyData) {
        wd.findElement(By.xpath("//input[@name='company']")).click();
        wd.findElement(By.xpath("//input[@name='company']")).clear();
        wd.findElement(By.xpath("//input[@name='company']")).sendKeys(companyData.getNameOfCompany());
        wd.findElement(By.xpath("//textarea[@name='address']")).click();
        wd.findElement(By.xpath("//textarea[@name='address']")).clear();
        wd.findElement(By.xpath("//textarea[@name='address']")).sendKeys(companyData.getAddressOfCompany());
    }

    private void fillContactInformation(ContactInformaationData contactInformaationData) {
        wd.findElement(By.xpath("//input[@name='home']")).click();
        wd.findElement(By.xpath("//input[@name='home']")).clear();
        wd.findElement(By.xpath("//input[@name='home']")).sendKeys(contactInformaationData.getHomeTel());
        wd.findElement(By.xpath("//input[@name='mobile']")).click();
        wd.findElement(By.xpath("//input[@name='mobile']")).clear();
        wd.findElement(By.xpath("//input[@name='mobile']")).sendKeys(contactInformaationData.getMobileTel());
        wd.findElement(By.xpath("//input[@name='work']")).click();
        wd.findElement(By.xpath("//input[@name='work']")).clear();
        wd.findElement(By.xpath("//input[@name='work']")).sendKeys(contactInformaationData.getWorkTel());
        wd.findElement(By.xpath("//input[@name='fax']")).click();
        wd.findElement(By.xpath("//input[@name='fax']")).clear();
        wd.findElement(By.xpath("//input[@name='fax']")).sendKeys(contactInformaationData.getFaxTel());
        wd.findElement(By.xpath("//input[@name='email']")).click();
        wd.findElement(By.xpath("//input[@name='email']")).clear();
        wd.findElement(By.xpath("//input[@name='email']")).sendKeys(contactInformaationData.getEmail());

    }

    private void selectGroupOnAddNewPage(String nameOfGroup) {
        wd.findElement(By.name("new_group")).click();
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(nameOfGroup);
    }

    private void submitContactCreation() {
        wd.findElement(By.xpath("//input[21]")).click();
    }
    private void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }
    private void selectGroupOnHomePage(String nameOfGroup) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(nameOfGroup);
    }
    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}