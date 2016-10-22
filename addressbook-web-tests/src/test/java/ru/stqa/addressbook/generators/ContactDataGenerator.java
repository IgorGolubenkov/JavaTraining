package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")  // argument -c 10 -f src/test/resources/contacts.json -d json
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println(String.format("Unrecognized format %s", format));
        }
    }

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException exc) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()));
            }
        }
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String firstName = String.format("firstName: %s", i);
            String lastName = String.format("lastName: %s", i);
            String address = String.format("country Russia city Moscow, Lenina street apartment %s apartment %s", i, i);
            String email = String.format("test%s@test%s.test%s", i, i, i);
            String email2 = String.format("2test%s@2test%s.2test%s", i, i, i);
            String email3 = String.format("3test%s@3test%s.3test%s", i, i, i);
            String homePhone = String.format("+749911%s4%s5%s", i, i, i);
            String mobilePhone = String.format("+790%s11%s2%s3%s", i, i, i, i);
            String workPhone = String.format("+749511%s2%s3%s", i, i, i);
            contacts.add(new ContactData().withFirstname(firstName).withLastname(lastName)
                    .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withEmail(email)
                    .withEmail2(email2).withEmail3(email3).withAddress(address));
        }
        return contacts;
    }
}
