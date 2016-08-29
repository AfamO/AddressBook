/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

/**
 * This defines/encapsulates the Person's class. It extends the abstract class
 * ContactInfo since every person must have contacts.
 * @author fupre1
 */
public class Person extends AddressBookGetSetInfo {
    private String firstName;
    private String lastName;
    private String password;
    private ContactInfo contactInfo;
    public Person(){
        
    }
    public Person(String firstName,String lastName,String password, ContactInfo contactInfo){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.SetContactInfo(contactInfo);
    }
    public Person(String firstName,String lastName, ContactInfo contactInfo){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.SetContactInfo(contactInfo);
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getPassword(){
        return this.password;
    }
    public void SetContactInfo(ContactInfo contactInfo){
        this.contactInfo=contactInfo;
    }
    public ContactInfo getContactInfo(){
       return this.contactInfo;
    }
    
    
}
