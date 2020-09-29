package model;

import data.DataSource;
import org.apache.commons.lang3.StringUtils;
import service.IOService;
import service.UserServiceImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhoneBook {

    Scanner sc = new Scanner(System.in);
    Set<Contact> contactSet = DataSource.getContactSet();
    Set<Contact> filteredContacts = new TreeSet<>();
    private final Set<Contact> blackList = new TreeSet<>();
    UserServiceImpl userService = new UserServiceImpl();
    Contact extractedContact;
    private final IOService ioService = new IOService();
    private final File contactsFile;
    private final File blackListFile = new File("blackList.csv");
    private final File backupDirectory = new File("Backup");


    public PhoneBook(Set<Contact> contactSet, File contactsFile) {
        this.contactSet=contactSet;
        this.contactsFile=contactsFile;
    }

    public void initiateMenu() {
        do {
            showMainMenu();
            try {
                String choice = sc.next();

                switch (choice) {
                    case "1":
                        //LIST ALL CONTACTS + EDIT CONTACT SUB-MENU

                        showHeader();
                        userService.getContacts(contactSet);
                        System.out.println();
                        System.out.println("Press 'E' if you want to edit a contact.");
                        System.out.println("Press 'B' to go back to the main menu.");
                        String userChoice = sc.next();
                        if (userChoice.equalsIgnoreCase("e")) {
                            System.out.println("Which contact do you want to edit?");
                            System.out.println("Please type the contact's first name.");
                            extractedContact = userService.searchContactByFirstName(contactSet);
                            showEditSubMenu();
                            userService.editContact(extractedContact);
                        } else if (userChoice.equalsIgnoreCase("b")) {
                        } else {
                            System.out.println("Wrong input! Please try again.");
                        }
                        System.out.println();
                        break;

                    case "2":
                        //LIST FAVORITE
                        userService.getFavouriteContact(contactSet);
                        break;

                    case "3":
                        //SEARCH CONTACT -> SUBMENU
                       // userService.getContacts(contactSet);
                       // System.out.println("Search :");
                       // showSearchSubMenu();
                       // filteredContacts = userService.search(sc.next(), contactSet);
                       // filteredContacts.forEach(contact -> System.out.println(contact));
                        break;

                    case "4":
                        //ADD CONTACT
                        userService.addContact(contactSet);
                        break;

                    case "5":
                        //REMOVE CONTACT
                        System.out.println("Which contact do you want to remove?");
                        System.out.println("Please type the contact's first name.");
                        extractedContact = userService.searchContactByFirstName(contactSet);
                        userService.removeContact(extractedContact, contactSet);
                        break;

                    case "6":
                        System.exit(0);
                        break;

                    default: // For invalid inputs
                        System.out.println("Invalid input. Please, choose between [1-5] only!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option!" + sc + " Please enter your option again: ");
                String choice = sc.next();
                initiateMenu();
            }
        } while (true);
    }

    public static void showMainMenu() {

        System.out.println("============================");
        System.out.println("|       CONTACT LIST       |");
        System.out.println("============================");
        System.out.println("|    1. List contacts      |"); // -> sub-menu for managing contacts
        System.out.println("|    2. List favorites     |");
        System.out.println("|    3. Search contact     |");
        System.out.println("|    4. Add contact        |");
        System.out.println("|    5. Remove contact     |");
        System.out.println("|    6. Exit               |");
        System.out.println();
    }

    private static void showSearchSubMenu() {
        System.out.println("================================");
        System.out.println("|             SEARCH           |");
        System.out.println("================================");
        System.out.println("|  1. Search by first name     |");
        System.out.println("|  2. Search by last name      |");
        System.out.println("|  3. Search by phone number   |");
        System.out.println("|  4. Return to previous menu  |");

    }

    private static void showEditSubMenu() {
        System.out.println("===================================");
        System.out.println("|            EDIT CONTACT         |");
        System.out.println("===================================");
        System.out.println("|  1. Edit contact's first name   |");
        System.out.println("|  2. Edit contact's last name    |");
        System.out.println("|  3. Edit contact's phone number |");
        System.out.println("|  4. Return to previous menu     |");

    }

    private static void showBackupSubMenu() {
        System.out.println("===================================");
        System.out.println("|              BACKUP             |");
        System.out.println("===================================");
        System.out.println("|  1. Backup                      |");
        System.out.println("|  2. View previous backups       |");
        System.out.println("|  3. Restore previous backup     |");
        System.out.println("|  4. Return to previous menu     |");

    }

    private void showHeader() {
        System.out.println(StringUtils.center("  FIRST NAME", 15, " ") +
                StringUtils.center("  LAST NAME", 16, " ") +
                StringUtils.center(" PHONE NUMBER", 16, " ") +
         //     StringUtils.center("  COUNTRY CODE", 16, " ") +
         //     StringUtils.center("  CITY", 16, " ") +
                StringUtils.center("  COUNTRY", 16, " "));
    }


    // ------------------ File backup ------------------

    private void backupMenu() {
        try {
            showBackupSubMenu();
            System.out.println("Enter an option:");
            byte option = sc.nextByte();
            switch (option) {
                case 1:
                    //do backup now
                    doBackup();
                    break;
                case 2:
                    //show all current backups
                    viewBackups();
                    break;
                case 3:
                    //restore a specific backup
                    restoreBackup();
                    break;
                case 4:
                    initiateMenu();
                default:
                    System.out.println("Invalid input. Please, choose between [1-4] only!");
                    backupMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, choose between [1-5] only!");
            sc = new Scanner(System.in);
            backupMenu();
        }
    }

    private void viewBackups() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        File[] directories = backupDirectory.listFiles();
        if (directories != null) {
            for (int i = 0; i < directories.length; i++) {
                System.out.println((i + 1) + "-Backup \"" + directories[i].getName() + "\" created on "
                        + simpleDateFormat.format(directories[i].lastModified()));
            }
        }
    }
    private void restoreBackup() {
        viewBackups();
        System.out.println();
        System.out.println("Please enter the number of the backup you want to restore:");
        int backupNumber = sc.nextInt() - 1;
        try {
            File[] directories = backupDirectory.listFiles();
            if (directories != null) {
                if (Objects.requireNonNull(directories[backupNumber].listFiles()).length > 1) {
                    File[] files = directories[backupNumber].listFiles();
                    for (File file : Objects.requireNonNull(files)) {
                        if (file.getName().startsWith("backup")) {
                            Files.copy(file.toPath(), contactsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Contacts file backup restored successfully!");
                            contactSet.addAll(ioService.readFile(contactsFile));
                        } else {
                            Files.copy(file.toPath(), blackListFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            blackList.addAll(ioService.readFile(blackListFile));
                            System.out.println("Blacklist file backup restored successfully!");
                        }
                    }
                } else {
                    File[] files = directories[backupNumber].listFiles();
                    for (File file : Objects.requireNonNull(files)) {
                        Files.copy(file.toPath(), contactsFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Contacts file backup restores successfully!");
                        contactSet.addAll(ioService.readFile(contactsFile));
                    }
                }
            }
           // deleteBackup(backupNumber);
        } catch (IOException e) {
            System.out.println("Backup not applied!\nSomething went wrong!");
        }
    }

    private void doBackup() {
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }
        String directoryName = "Backup/" + "backup_" + System.currentTimeMillis();
        String fileName = "backupFile_" + System.currentTimeMillis() + ".csv";
        File newBackupDirectory = new File(directoryName);
        newBackupDirectory.mkdir();
        try {
            if (blackListFile.exists()) {
                String backupBlackListFileName = "BLFileBackup_" + System.currentTimeMillis() + ".csv";
                File backupBlacklistFile = new File(directoryName + "/" + backupBlackListFileName);
                Files.copy(blackListFile.toPath(), backupBlacklistFile.toPath());
            }
            File backupFile = new File(directoryName + "/" + fileName);
            Files.copy(contactsFile.toPath(), backupFile.toPath());
            System.out.println("Backup created successfully!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

    // ------------------------------------------------------


