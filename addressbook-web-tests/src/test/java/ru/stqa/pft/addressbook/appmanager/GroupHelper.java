package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{
    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        click.findElement(By.name("group_header")).clear();
        click.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        click.findElement(By.name("group_footer")).clear();
        click.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.xpath("//input[@name='delete']"));
    }

    public void selectGroup() {
        click(By.xpath("//div[@id='content']/form/span[2]/input"));
    }


}
