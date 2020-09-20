package model;

import enums.Group;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Contact implements Comparable<Contact>{

    private String firstName;
    private String lastName;
    private Group group;
    private PhoneNumber phoneNumber;
    private String email;
    private Address address;
    private Company company;
    private Date birthday;
    private boolean isFavorite;

    public Contact(String firstName, String lastName, PhoneNumber phoneNumber, String email, Address address, Company company, Date birthday, boolean isFavorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.company = company;
        this.birthday = birthday;
        this.isFavorite = isFavorite;
    }

    // constructor used for data source (Faker)
    public Contact(String firstName, String lastName, PhoneNumber phoneNumber, Address address, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group=" + group +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", company=" + company +
                ", birthday=" + birthday +
                ", isFavorite=" + isFavorite +
                '}';
    }

    @Override
    public int compareTo(Contact otherContact) {
        return this.firstName.compareTo(otherContact.firstName);
    }
}
