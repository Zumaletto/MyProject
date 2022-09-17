package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        save(contacts, new File (file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getLastName(), contact.getFirstName(), contact.getAddress(),
                    contact.getHomeTel(), contact.getMobileTel(), contact.getWorkTel(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();

    }

    private List<ContactData> generateContact(int count) {
        List<ContactData> contact = new ArrayList<ContactData>();

        for (int i = 0; i < count; i++) {
            contact.add(new ContactData()
                    .withLastName(String.format("lastname %s", i)).withFirstName(String.format("firstname %s", i))
                    .withAddress(String.format("address %s", i)).withHomeTel(String.format("home %s", i))
                    .withMobileTel(String.format("mobile %s", i)).withWorkTel(String.format("work %s", i))
                    .withEmail(String.format("email1 %s", i)).withEmail2(String.format(" email2 %s", i))
                    .withEmail3(String.format("email %s", i)));
        }
        return contact;
    }
}
