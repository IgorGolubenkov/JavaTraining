package ru.stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCreationGroup extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        //List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader redear = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = redear.readLine();
        while (line != null) {
            xml += line;
            line = redear.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
            //while (line != null) {
        //    String[] split = line.split(";");
        //    list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        //    line = redear.readLine();
        //}
        //list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        //list.add(new Object[] {new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        //list.add(new Object[] {new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        //return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testCreationGroup(GroupData group) throws InterruptedException {
        //app.group().openNewTab();
        app.goTo().groupPage();
        Groups before = app.group().all();
        //int before = app.group().count();
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
