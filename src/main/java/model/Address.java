package model;

public class Address {

    private String streetName;
    private String streetNo;
    private int floorNo;
    private int doorNo;
    private String city;
    private String country;

    public Address(String streetName, String streetNo, String city, String country) {   // house constructor
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.city = city;
        this.country = country;
    }

    public Address(String streetName, String streetNo, int floorNo, int doorNo, String city, String country) {
        this.streetName = streetName;
        this.streetNo = streetNo;                                                        // flat constructor
        this.floorNo = floorNo;
        this.doorNo = doorNo;
        this.city = city;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
