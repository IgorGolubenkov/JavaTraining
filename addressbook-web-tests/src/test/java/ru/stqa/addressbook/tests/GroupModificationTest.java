package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase{

    @DataProvider
    public Iterator<Object[]> modifyGroupsFromJSON() throws IOException {
        try (BufferedReader readear = new BufferedReader(new FileReader
                (new File("src/test/resources/TestData/groups/groups_modify.json")))) {
            String json = "";
            String line = readear.readLine();
            while (line != null) {
                json += line;
                line = readear.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
//        UI
//        app.goTo().groupPage();
//        if (app.group().all().size() == 0) {
//            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
//        }
    }

    @Test(dataProvider = "modifyGroupsFromJSON")
    public void testGroupModification(GroupData group) {

        Groups before = app.db().groups();
//        Groups before = app.group().all();  // UI
        GroupData modifyGroup = before.iterator().next();
        GroupData groupData = group.withId(modifyGroup.getId());

        app.goTo().groupPage();
        app.group().modify(groupData);

        assertEquals(app.group().count(), before.size()); // UI hash

        Groups after = app.db().groups();

//        Groups after = app.group().all();   //UI

        assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));

        verifyGroupListInUI();
    }
}
