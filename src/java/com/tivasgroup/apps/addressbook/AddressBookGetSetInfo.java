/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

/** 
 * 
 * It implements the interface AddressBookGetterSetter
 *
 * @author fupre1
 */
public abstract class AddressBookGetSetInfo implements AddressBookGetterSetter {
    private String info;
    public void setInfo(String info){
        this.info=info;
        
    }
    public String getInfo(){
        return info;
    }
    
}
