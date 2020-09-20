package model;

public class PhoneNumber {

    private String phoneNumber; // 723456789
    private String countryCode; // +40 - RO

    public PhoneNumber(String phoneNumber) { // constructor with default RO country code
        this.phoneNumber=phoneNumber;
        this.countryCode="+40";
    }

    public PhoneNumber(String phoneNumber, String countryCode) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
