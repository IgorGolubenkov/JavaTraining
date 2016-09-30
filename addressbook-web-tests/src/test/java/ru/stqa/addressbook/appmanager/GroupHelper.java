package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }
    public void returnToGroupPage() {
        clickSearch(By.xpath("//a[contains(.,'group page')]"));
    }
    public void submitGroupCreation() {
        clickSearch(By.name("submit"));
    }
    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }
    public void initGroupCreation() {
        clickSearch(By.name("new"));
    }
    public void deleteSelectedGroup() {
        clickSearch(By.name("delete"));
    }
    public void selectGroup() {
        clickSearch(By.xpath(".//*[@id='content']/form/span[1]/input"));
    }
    public void initGroupModification() {
        clickSearch(By.xpath(".//*[@id='content']/form/input[3]"));
    }
    public void submitGroupModification() {
        clickSearch(By.xpath("//input[@name='update']"));
    }
    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }
    public boolean isThereAGroup() {
        return isElementPresent(By.xpath(".//*[@id='content']/form/span[1]/input"));
    }
}
