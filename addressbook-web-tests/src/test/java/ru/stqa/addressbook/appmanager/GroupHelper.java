package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(ChromeDriver wd) {
        super(wd);
    }
    public void returnToGroupPage() {
        click(By.xpath("//a[contains(.,'group page')]"));
    }
    public void submitGroupCreation() {
        click(By.name("submit"));
    }
    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }
    public void initGroupCreation() {
        click(By.name("new"));
    }
    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }
    public void selectGroup() {
        click(By.xpath(".//*[@id='content']/form/span[1]/input"));
    }

    public void initGroupModification() {
        click(By.xpath(".//*[@id='content']/form/input[3]"));
    }
    public void submitGroupModification() {
        click(By.xpath("//input[@name='update']"));
    }
}
