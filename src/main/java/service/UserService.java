package service;

import model.Contact;
import java.util.Set;

public interface UserService {

    void getContacts(Set<Contact> contactSet);

    void getFavouriteContact(Set<Contact> contactSet);

    void addContact(Set<Contact> contactSet);

    void removeContact(Contact contact, Set<Contact> contactSet);

    void editContact(Contact contact);

    Contact searchContactByFirstName (Set<Contact> tempContacts);
}
