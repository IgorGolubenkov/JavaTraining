package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

    private final ApplicationManager app;

    public GroupHelper(ApplicationManager app) {
        super(app.wd);
        this.app = app;
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.xpath(".//*[@id='content']/form/span[1]/input"));
    }

    private Groups groupCache = null;

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        //List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='group']"));
        for (WebElement element : elements ) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            //String id = element.findElement(By.xpath("/input[@name='selected[]']")).getAttribute("value");
            GroupData group = new GroupData().withName(name).withId(id);
            groups.add(group);
        }
        return groups;
    }

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='group']"));
        for (WebElement element : elements ) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withName(name).withId(id);
            groupCache.add(group);
        }
        return new Groups(groupCache);
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

    public void selectGroup(int index) {
        wd.findElements(By.xpath("//input[@name='selected[]']")).get(index).click();
    }

    public void selectGroupById(int id) {
        wd.findElement(By.xpath("//input[@value='"+ id +"']")).click();
    }

    public void initGroupModification() {
        clickSearch(By.xpath(".//*[@id='content']/form/input[3]"));
    }

    public void submitGroupModification() {
        clickSearch(By.xpath("//input[@name='update']"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteSelectedGroup();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public int count() {
        return wd.findElements(By.xpath("//input[@name='selected[]']")).size();
    }

    public void openNewTab() throws InterruptedException {
        String NewTabLink = Keys.chord(Keys.CONTROL,"t");
        wd.findElement(By.linkText("urlLink")).sendKeys(NewTabLink );
    }

}
