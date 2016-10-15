package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationGroup extends TestBase {

    @Test
    public void testCreationGroup() throws InterruptedException {
        //app.group().openNewTab();
        app.goTo().groupPage();
        Groups before = app.group().all();
        //int before = app.group().count();
        GroupData group = new GroupData().withName("test254");
        app.group().create(group);
        //int after = app.group().count();
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        before.add(group);
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCreationBadGroup() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test254'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        before.add(group);
        assertThat(after, equalTo(before));
    }
}
