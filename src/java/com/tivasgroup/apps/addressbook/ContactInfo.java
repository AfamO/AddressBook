/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

/**
 * This encapsulates and get and sets user contacts data.
 * It extends the abstract class AddressBookGetSetInfo
 * @author fupre1
 */
public  class ContactInfo extends AddressBookGetSetInfo {
    private String email;
    private String address;
    private String city;
    private String zip;
    private String state;
    private String phone;
    public ContactInfo(String email,String address,String city,String zip,String state,String phone){
        this.setEmailAddress(email);
        this.setAddress(address);
        this.setCity(city);
        this.setZip(zip);
        this.setState(state);
        this.setPhone(phone);
    }
    public ContactInfo(String email){
        this.setEmailAddress(email);
        
    }
    public void setEmailAddress(String email){
        this.email=email;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setCity(String city){
       this.city=city;
    }
    public void setZip(String zip){
        this.zip=zip;
    }
    public void setState(String state){
        this.state=state;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getEmailAddress(){
        return this.email;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCity(){
       return this.city;
    }
    public String getZip(){
        return this.zip;
    }
    public String getState(){
        return this.state;
    }
    public String getPhone(){
        return this.phone;
    }
    
}
