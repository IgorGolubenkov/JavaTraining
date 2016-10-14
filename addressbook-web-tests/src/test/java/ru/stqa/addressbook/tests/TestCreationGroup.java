package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationGroup extends TestBase {

    @Test
    public void testCreationGroup() throws InterruptedException {
        app.group().openNewTab();
        app.goTo().groupPage();
        Groups before = app.group().all();
        //int before = app.group().getGroupCount();
        GroupData group = new GroupData().withName("test254");
        app.group().create(group);
        //int after = app.group().getGroupCount();
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        before.add(group);
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCreationBadGroup() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test254'");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
        before.add(group);
        assertThat(after, equalTo(before));
    }
}
