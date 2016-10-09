package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class TestCreationGroup extends TestBase {

    @Test
    public void testCreationGroup() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        //int before = app.group().getGroupCount();
        GroupData group = new GroupData().withName("test254");
        app.group().create(group);
        //int after = app.group().getGroupCount();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        //int max = 0;
        //for (GroupData g : after) {
        //    if (g.getId() > max) {
        //        max = g.getId();
        //    }
        //}
        group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
