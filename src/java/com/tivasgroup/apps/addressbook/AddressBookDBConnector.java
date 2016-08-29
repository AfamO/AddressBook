/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author fupre1
 */
/**
* This class encapsulates the whole database connection logic needed throughout the application.
* @param dbconn Connection object used to obtain a database connection.
* @param msgout String object that holds connection status message.
* @param IsConnected Boolean object used to check whether a connection was established or not at each point in time.
* @author Afam;
* @see AdressBookDatabase.java,AdressBookController.java,AdressBookAppListener.java
* @version 1.0 
*/
public class AddressBookDBConnector {
    //Declare these objects and these variables-explained above- as global.
    public Connection dbconn;
    private String  msgout;
    private static int counter=Integer.parseInt(System.getProperty("connections_counter"));//number of db connections
    private static Boolean IsConnected=false;
    /** Creates a new instance of this class */
    public AddressBookDBConnector()
    {
      
    }
    /**
    * This method directly makes a connection to database.
    * @throws ClassNotFoundException if the jdbc driver fails to load.
    * @throws SQLException if database access operation failed.
    * **/
    public void databaseConnect() 
    {
        try
        {
            //get the needed database connection parameters from web.xml as initialized by AppListener at the start-up of this web app.
            String dbname= System.getProperty("dbname");
            String dbhost = System.getProperty("dbhost");
            String dbport = System.getProperty("dbport");
            String dbuser = System.getProperty("dbuser");
            String dbpass = System.getProperty("dbpass");
            //validate for empty values
            if(dbhost.equals("") || dbport.equals("") || dbname.equals("") || dbuser.equals(""))
            {
                System.out.println("DATABASE CONNECTION ERROR:Make Sure that your database configuration file has not been tampered with");
                System.out.println("Dbuser="+dbuser+",dbpassword\n="+dbpass+" while  DBname="+dbname+",dbhost="+dbhost+" , dbport="+dbport+"");
                dbconn=null;
            }
            else
            {
                ///*
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                DataSource ds = (DataSource)envCtx.lookup("jdbc/phoneBookDB");

                dbconn = (Connection) ds.getConnection();
                //get the number of db connections
                counter=Integer.parseInt(System.getProperty("connections_counter"));
                counter+=1;
                //For Debugging purposes.
                System.out.println("NUMBER OF MADE CONNECTIONS=="+counter);
                System.setProperty("connections_counter", Integer.toString(counter));
                System.out.println("MAX CONNECTIONS=="+dbconn.getMetaData().getMaxConnections());
                System.out.println("MAX getMaxUserNameLength()=="+dbconn.getMetaData().getMaxUserNameLength());
                System.out.println("getUserName()=="+dbconn.getMetaData().getUserName());
                dbconn.getMetaData().getUserName();
                //*/
                //loads the driver class and makes jdbc connection
                //Class.forName("com.mysql.jdbc.Driver");
                //dbconn = DriverManager.getConnection("jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname, dbuser,dbpass);
            }
            //Is connection made successfully?
            if(dbconn!=null)
            {
                IsConnected=true;
            }
            else
            {
                IsConnected=false;
            }
        }
       
        catch ( SQLException sqlex ) {
            // process SQLExceptions here
            sqlex.printStackTrace();
            msgout =  "Connection unsuccessful\n" +
            sqlex.toString() ;
            System.out.println(msgout);
        }
        ///*
        catch (NamingException ex) {
            Logger.getLogger(AddressBookDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*/
    }
    /** 
    * This method statically gets the boolean object=that tells whether connection was successfully established or not 
    * @return Boolean  -It the boolean variable in question.
    */
    public static Boolean getIsConnected()
    {
        return IsConnected;
    }
    
}
