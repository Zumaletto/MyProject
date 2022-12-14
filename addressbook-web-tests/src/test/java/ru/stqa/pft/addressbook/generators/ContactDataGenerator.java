package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));//сохранение данных в файл
        } else if (format.equals("json")) {
            saveSaveJson(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveSaveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);}
    }

    private void saveSaveJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveSaveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getLastName(), contact.getFirstName(), contact.getAddress(),
                        contact.getHomeTel(), contact.getMobileTel(), contact.getWorkTel(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
            }
        }
    }

    private List<ContactData> generateContact(int count) {
        List<ContactData> contact = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contact.add(new ContactData()
                    .withLastName(String.format("lastname %s", i)).withFirstName(String.format("firstname %s", i))
                    .withAddress(String.format("address %s", i)).withHomeTel(String.format("home %s", i))
                    .withMobileTel(String.format("mobile %s", i)).withWorkTel(String.format("work %s", i))
                    .withEmail(String.format("email1 %s", i)).withEmail2(String.format(" email2 %s", i))
                    .withEmail3(String.format("email3 %s", i)));
        }
        return contact;
    }
}
