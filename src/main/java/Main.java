import model.Contact;
import model.PhoneBook;
import service.IOService;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    private final static File contactsFile=new File("contacts.csv");

    public static void main(String[] args) {
        //Simulate the functionality of a contact list application

        try {
            IOService ioService = new IOService();
            Set<Contact> contacts = new TreeSet<>(ioService.readFile(contactsFile));
            PhoneBook phoneBook = new PhoneBook(contacts, contactsFile);
            phoneBook.initiateMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

