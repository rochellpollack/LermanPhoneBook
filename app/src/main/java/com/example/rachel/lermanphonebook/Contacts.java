package com.example.rachel.lermanphonebook;

/**
 * Created by Rachel on 6/22/2015.
 */
public class Contacts {

    //set up data variables
    //private variables
    private  int _id;
    private String _first_name;
    private  String _last_name;
    private String _home_phone_number;
    private String _cell_phone_number;
    private String _email_address;
    private String _street;
    private String _city;
    private String _zip;
    private String _birthday;
    private  String _family_branch;

    // Empty constructor
    public Contacts(){

    }
     // constructor
     public Contacts(int _id, String _first_name, String _last_name, String _home_phone_number, String _cell_phone_number, String _email_address,
                     String _street, String _city, String _zip, String _birthday, String _family_branch) {
         this._id = _id;
         this._first_name = _first_name;
         this._last_name = _last_name;
         this._home_phone_number = _home_phone_number;
         this._cell_phone_number = _cell_phone_number;
         this._email_address = _email_address;
         this._street = _street;
         this._city = _city;
         this._zip = _zip;
         this._birthday = _birthday;
         this._family_branch =_family_branch;
     }

    public Contacts(String _first_name, String _last_name, String _home_phone_number, String _cell_phone_number, String _email_address,
                    String _street, String _city, String _zip, String _family_branch) {
        this._first_name = _first_name;
        this._last_name = _last_name;
        this._home_phone_number = _home_phone_number;
        this._cell_phone_number = _cell_phone_number;
        this._email_address = _email_address;
        this._street = _street;
        this._city = _city;
        this._zip = _zip;
        this._family_branch = _family_branch;

    }

    public Contacts(String _first_name, String _last_name) {
        this._first_name = _first_name;
        this._last_name = _last_name;

    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    // getting name
    public String getFirstName(){
        return this._first_name;
    }

    // setting name
    public void setFirstName(String name){
        this._first_name = name;
    }

    public String getLastName() {
        return _last_name;
    }

    public void setLastName(String _last_name) {
        this._last_name = _last_name;
    }

    // getting phone number
    public String getHomePhoneNumber(){
        return this._home_phone_number;
    }

    // setting phone number
    public void setHomePhoneNumber(String phone_number){
        this._home_phone_number = phone_number;
    }

    public String getCellPhoneNumber() {
        return _cell_phone_number;
    }

    public void setCellPhoneNumber(String _cell_phone_number) {
        this._cell_phone_number = _cell_phone_number;
    }

    public String getEmailAddress() {
        return _email_address;
    }

    public void setEmailAddress(String _email_address) {
        this._email_address = _email_address;
    }

    public String getStreet() {
        return _street;
    }

    public void setStreet(String _street) {
        this._street = _street;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String _city) {
        this._city = _city;
    }

    public String getZip() {
        return _zip;
    }

    public void setZip(String _zip) {
        this._zip = _zip;
    }

    public String getBirthday() {
        return _birthday;
    }

    public void setBirthday(String _birthday) {
        this._birthday = _birthday;
    }

    public String getFamilyBranch() {
        return _family_branch;
    }

    public void setFamilyBranch(String _family_branch) {
        this._family_branch = _family_branch;
    }
}
