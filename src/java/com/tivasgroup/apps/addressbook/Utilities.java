/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fupre1
 */
public class Utilities {
    /**
  * This methods simply generates user token by using a class UUID random method. 
  * It then gets the last 6 digits of the random generated key and returns it as the needed token String.
  * @return String -It is the token generated.
  */
public static String gEnerateUserToken()
{
        //gets a unique by accessing the static class random method
        UUID uniqueKey = UUID.randomUUID();
        // converts the key to string format
        String token=uniqueKey.toString();
        //gets the last position of the character '-' in the key
        int val=token.lastIndexOf("-");
        //gets the substring of the token starting from the last position of '-' till the last character in the key
        String tokensub=token.substring(val, token.length());
        System.out.println("The substring of token ="+tokensub);
        //gets the last 6 character of the substring.
        token=tokensub.substring(tokensub.length()-6, tokensub.length());
        System.out.println("The final Token value is  ="+token);
        return token;
    }

    /** 
    * This method generates MD5 hash for password hashing
    * @param original String object representing the original password text.
    * @throws NoSuchAlgorithmException if the MD5 algorithm access operation failed.
    */
    public static String getMD5(String original)
    {
        MessageDigest md = null;
        try 
        {
            md = MessageDigest.getInstance("MD5");
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(original.getBytes());
	byte[] digest = md.digest();
	StringBuilder sb = new StringBuilder();
	for (byte b : digest) 
        {
            sb.append(String.format("%02x", b & 0xff));
	}
        return  sb.toString();
    }
    
}
