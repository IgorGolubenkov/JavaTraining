package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

public class TestCreationGroup extends TestBase {

    @Test
    public void testCreationGroup() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
}
