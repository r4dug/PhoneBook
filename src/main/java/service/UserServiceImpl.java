package service;

import enums.Group;
import model.Contact;
import model.PhoneBook;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class UserServiceImpl implements UserService{

    private Contact searchContact;
    Scanner sc = new Scanner(System.in);

    @Override
    public void getContacts(Set<Contact> contactSet) {
        contactSet.forEach(System.out::println);
    }

    @Override
    public void getFavouriteContact(Set<Contact> contactSet) {
        contactSet.stream().filter(e -> e.isFavorite()).forEach(System.out::println);
    }

    @Override
    public void addContact(Contact contact, Set<Contact> contactSet) {

    }

    @Override
    public void removeContact(Contact contact, Set<Contact> contactSet) {

    }

    @Override
    public void editContact(Contact contact) {
        try {
            String userInput = sc.next();

            switch (userInput) {

                case "1":
                    // first name
                    System.out.println("Please enter the new contact's first name");
                    String firstName = sc.next();
                    contact.setFirstName(firstName);
                    System.out.println("Contact updated to: " + contact.getFirstName() + " " + contact.getLastName());
                    break;
                case "2":
                    //last name
                    System.out.println("Please enter the new contact's last name");
                    String lastName = sc.next();
                    contact.setLastName(lastName);
                    System.out.println("Contact updated to: " + contact.getFirstName() + " " + contact.getLastName());
                    break;

                case "3":
                    // phone number
                    System.out.println("Please enter the new contact's phone number");
                    String phoneNumber = sc.next();
                    contact.getPhoneNumber().setPhoneNumber(phoneNumber);
                    System.out.println("Contact updated to: " + contact.getFirstName() + " " + contact.getPhoneNumber());
                    break;

                case "4":
                    // group
                    System.out.println("Please enter the new contact's group number");
                    String groupName = sc.next();
                    if (groupName.equalsIgnoreCase(Group.FAVORITE.getGroupName())) {
                        contact.setGroup(Group.FAVORITE);
                    } else if (groupName.equalsIgnoreCase(Group.FAMILY.getGroupName())) {
                        contact.setGroup(Group.FAMILY);
                    } else if (groupName.equalsIgnoreCase(Group.FRIENDS.getGroupName())) {
                        contact.setGroup(Group.FRIENDS);
                    } else if (groupName.equalsIgnoreCase(Group.WORK.getGroupName())) {
                        contact.setGroup(Group.WORK);
                    } else {
                        System.out.println("This group doesn't exist!");
                    }
                    System.out.println("Contact's group successfully edited");
                    break;

                default:
                    System.out.println("Invalid input");
                    PhoneBook.showMainMenu();
                    editContact(contact);
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Invalid input. Please, choose between [1-9] only!");
            sc = new Scanner(System.in);
            PhoneBook.showMainMenu();
            editContact(contact);
        }
    }

    @Override
    public Contact searchContactByFirstName(Set<Contact> tempContacts) {
        System.out.println();
        String inputFirstName = sc.next().toLowerCase();
        Optional<Contact> contactOptional = tempContacts.stream()
                .filter(contact -> contact.getFirstName().equalsIgnoreCase(inputFirstName)).findAny();
        if (contactOptional.isPresent()) {
            searchContact = contactOptional.get();
            displayContactInfo();
        } else {
            System.out.println("\nContact not found!");
        }

        return searchContact;
    }

    private void displayContactInfo() {
        System.out.println("Contact selected:");
        System.out.println("\nContact: " + searchContact.getFirstName() + " " + searchContact.getLastName() + searchContact.getPhoneNumber());
    }
}
