/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author fupre1
 */
/**
 * This defines all the methods needed to perform 
 * all the database access of the application 
 * especially CRUD(create, retrieve, update and delete).
 * @author AFAM, Charles;
 * @see AddressBookConnector.java,AddressBookController.java,
 * @version 1.0 
 */
public class AddressBookDatabase {
    private PreparedStatement statement;
    private Statement old_statement;
    private AddressBookDBConnector dbConnector;
    //the constructor
    public AddressBookDatabase()
    {
        dbConnector = new AddressBookDBConnector ();
    }
    /*
     * Inserts the user's phonebook data
     */
    public int AddAddressBook(Person client_data,String userId)
    {
        dbConnector.databaseConnect();
        int result=0;
        try
        {
            String query = "INSERT INTO phonebook" +
            "(firstName,lastName,email,address,city,zip,state,phone,user_id)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
            //get prepared statement for faster execution  speed and avoid sql injection.
            System.out.println("Phone=="+client_data.getContactInfo().getPhone());
             System.out.println("Address=="+client_data.getContactInfo().getAddress());
            statement = dbConnector.dbconn.prepareStatement(query);
            statement.setString(1, client_data.getFirstName());
            statement.setString(2, client_data.getLastName());
            statement.setString(3, client_data.getContactInfo().getEmailAddress());
            statement.setString(4, client_data.getContactInfo().getAddress());
            statement.setString(5, client_data.getContactInfo().getCity());
            statement.setString(6, client_data.getContactInfo().getZip());
            statement.setString(7, client_data.getContactInfo().getState());
            statement.setString(8, client_data.getContactInfo().getPhone());
            statement.setString(9, userId);
            int resul= statement.executeUpdate();
            result=resul;
            statement.close();
        }
        catch ( SQLException sqlex ) 
        {
            System.out.println("Log Error Message Here:::");
             Logger.getLogger(AddressBookDBConnector.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbConnector.dbconn);
        }
        return result;
    }
    /*
     * Inserts the user's phonebook data
     */
    public int AddUserAccount(Person client_data)
    {
        dbConnector.databaseConnect();
        int result=0;
        try
        {
            String query = "INSERT INTO user" +
            "(name,email,password)" +
            "VALUES(?,?,?)";
            //get prepared statement for faster execution  speed and avoid sql injection.
            statement = dbConnector.dbconn.prepareStatement(query);
            statement.setString(1, client_data.getFirstName()+" "+client_data.getLastName());
            statement.setString(2, client_data.getContactInfo().getEmailAddress());
            statement.setString(3, Utilities.getMD5(client_data.getPassword()));
            int resul= statement.executeUpdate();
            result=resul;
            statement.close();
        }
        catch ( SQLException sqlex ) 
        {
            System.out.println("Log Error Message Here");
             Logger.getLogger(AddressBookDBConnector.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbConnector.dbconn);
        }
        return result;
    }
    public Person getPersonPhoneBook(String userId)
    {
        Person getuser = null; 
        dbConnector.databaseConnect();
        try
        {
            String query = "SELECT * FROM phonebook  WHERE where user_id=?" ;
            statement = dbConnector.dbconn.prepareStatement(query);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            getuser=new Person();
            getuser.setFirstName(rs.getString("firstName"));
            getuser.setLastName(rs.getString ("lastName"));
            getuser.getContactInfo().setEmailAddress(rs.getString ("email"));
            getuser.getContactInfo().setAddress(rs.getString ("address"));
            getuser.getContactInfo().setCity(rs.getString ("city"));
            getuser.getContactInfo().setZip(rs.getString ("zip"));
            getuser.getContactInfo().setState(rs.getString ("state"));
            getuser.getContactInfo().setPhone(rs.getString ("phone"));
            
            dbConnector.dbconn.close();
            
        }
        catch (SQLException sqlExc)
        {
             Logger.getLogger(AddressBookDBConnector.class.getName()).log(Level.SEVERE, null, sqlExc);
        }
        //closes all possible connection to database
        finally
        {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly( dbConnector.dbconn);
        }
        return getuser;
    }
    /**
    * This queries a particular table in a particular field of a particular column with a particular item.
    * @param  String table-This represents the table name to be used for the query.
    * @param String field -This represents the field in question.
    * @param  String Col-This represents the column in question.
    * @param String item -This represents the item in question.
    */
    public String RetrieveItem(String table,String field,String item,String Col)
    {
          String Item="";
          dbConnector.databaseConnect();
          try 
          {
              String query = " select * FROM "+table+" where "+field+" = ? " ;
              statement = dbConnector.dbconn.prepareStatement(query);
              statement.setString(1, item);
              ResultSet rs = statement.executeQuery();
              if(rs.next())
              {
                  Item=rs.getString(Col);
              }
              else
              {
                  //IsItemExist=false;
              }
              dbConnector.dbconn.close();
            
          }
          catch (SQLException sqlExc)
          {
               Logger.getLogger(AddressBookDBConnector.class.getName()).log(Level.SEVERE, null, sqlExc);
          } 
          //closes all possible connection to database
          finally
          {
              DbUtils.closeQuietly(statement);
              DbUtils.closeQuietly( dbConnector.dbconn);
          }
          return Item;
          
    }
    /**
    * This signs a user in.
    * @param String sensor -This represents client unique identity key
    * @param Person client_data this is a get & set object for client data
    */
    public String SignIn(String userEmail,String password)
    {
         String userName=RetrieveItem("user", "email", userEmail, "name");
         String passwordId=RetrieveItem("user", "password", Utilities.getMD5(password), "id");
         String name="";
         if(!userName.equals("") && !passwordId.equals(""))
         {
             name=userName;
         }
         return name;
    }
    
    /**
    * This updates database with clients data.
    * @param String sensor -This represents client unique identity key
    * @param Person client_data this is a get & set object for client data
    */
    public int UpdateClientPhoneBookDetails(String userId,Person client_data)
    {
         Person getuser = new Person();//table,field,item,colu;
         String id=RetrieveItem("snos_client", "snos_type", userId, "cid");
         dbConnector.databaseConnect();
         int result=0;
         try 
         {
             if(id.equals(""))
             {
                 throw new SQLException("The id info from DB is null, thus cannot continue.");
             }
             else
             {
                 String query = "UPDATE snos_client SET Surname=?,firstname=?,Client_name=?,fone=?,loco=?,lga=?,state=?,snos_type=?,Client_email=?  WHERE cid = ? " ;
                 statement = dbConnector.dbconn.prepareStatement(query);
                 statement.setString(1, client_data.getFirstName());
                 statement.setString(2, client_data.getLastName());
                 statement.setString(3, client_data.getContactInfo().getEmailAddress());
                 statement.setString(4, client_data.getContactInfo().getPhone());
                 statement.setString(5, client_data.getContactInfo().getCity());
                 statement.setString(6,client_data.getContactInfo().getZip());
                 statement.setString(7, client_data.getContactInfo().getState());
                 statement.setString(8, client_data.getContactInfo().getPhone());
                 statement.setString(10, id);
                 result=statement.executeUpdate();
             }
         }
         catch (SQLException exc)
         {
             JOptionPane.showMessageDialog(null,exc.toString( ));
         }
         //closes all possible connection to database
         finally
         {
             DbUtils.closeQuietly(statement);
             DbUtils.closeQuietly( dbConnector.dbconn);
         }
         return result;
    }
}
