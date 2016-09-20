package ru.stqa.addressbook;

import org.testng.annotations.Test;

public class TestCreationGroup extends TestBase{

    @Test
    public void testCreationGroup() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }
}
