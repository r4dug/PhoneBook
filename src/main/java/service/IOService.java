package service;

import model.Address;
import model.Contact;
import model.PhoneNumber;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class IOService {
    public Set<Contact> readFile(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Set<Contact> contactSet = new TreeSet<>();

        String line;
        String header = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] strings = line.split(",");
            String[] company = strings[4].split("/");
            String[] phoneNo = strings[5].split("/");
            String[] address = strings[7].split("/");

            contactSet.add(new Contact((strings[0]), strings[1],
                    new PhoneNumber(phoneNo[0]),
                    new Address(address[0])));
        }
        reader.close();
        return contactSet;
    }
    public void writeFile(Set<Contact> contactSet, File file) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(fileHeader());
                    String string = "";
                    writer.newLine();
                    for (Contact contact : contactSet) {
                        string +=  contact.getFirstName() + "," + contact.getLastName() +
                                "," + contact.getPhoneNumber().getPhoneNumber() +
                                "/" + contact.getAddress().getCountry() + "\n";
                    }
                    try {
                        Thread.sleep(20000);
                        System.out.println("Synchronization finished: ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    writer.write(string);
                    writer.flush();
                } catch (IOException ioException) {
                    System.out.println("Some error!");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private String fileHeader() {
        return "FIRST_NAME,LAST_NAME,PHONE_NUMBER,COUNTRY";
    }

}
