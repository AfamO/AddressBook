/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author fupre1
 */
public class AddressBookController extends HttpServlet {
    private ArrayList<Person> person;
    private Person personInfo;
    private AddressBookDatabase addressBookDatabase;
    private Boolean isPersonInfoEdited=false;
    private String editedInfoType="";
    private int numberOfContacts=0;
    private int personIndex=0;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        try {
            
            String clickedAction=request.getParameter("action");
            //did the user click a button/link for an action
            if(clickedAction!=null &&!clickedAction.equals("")){
                if(clickedAction.equalsIgnoreCase("addBook")){
                    String firstName=request.getParameter("firstName").trim();
                    String lastName=request.getParameter("lastName").trim();
                    String email=request.getParameter("email").trim();
                    String address=request.getParameter("address").trim();
                    String city=request.getParameter("city").trim();
                    String zip=request.getParameter("zip").trim();
                    String state=request.getParameter("state").trim();
                    String phone=request.getParameter("phone").trim();
                    ContactInfo contactInfo=new ContactInfo(email,address,city,zip,state,phone);
                    personInfo=new Person(firstName,lastName,contactInfo);
                    String currentUserEmail=(String)session.getAttribute("email");
                    addressBookDatabase=new AddressBookDatabase();
                    String userId=addressBookDatabase.RetrieveItem("user", "email",currentUserEmail, "id");
                    if(!userId.equals("")){
                        addressBookDatabase.AddAddressBook(personInfo, userId);
                        response.sendRedirect(request.getContextPath()+"/index.jsp#vieweditAddressBook");
                    }
                    else{
                        session.setAttribute("errmsg","We don't know this user.<br>Thank you");
                        response.sendRedirect(request.getContextPath()+"/index.jsp#addAddressBook");
                    }
                    //Temporally save the data to an array list.
                    //this.addPersonInfo(request, response, personInfo); NO TIME TO IMPLEMENT THIS
                    
                    
                }
                else if(clickedAction.equalsIgnoreCase("login"))
                {
                    String email=request.getParameter("email").trim();
                    String password= request.getParameter("password").trim();
                    addressBookDatabase=new AddressBookDatabase();
                    String name= addressBookDatabase.SignIn(email, password);
                    if(!name.equals("")){
                        //store in session for later references
                        
                        String userId=addressBookDatabase.RetrieveItem("user", "email",email, "id");
                        personInfo=addressBookDatabase.getPersonPhoneBook(userId);
                        String data[]={personInfo.getFirstName(),personInfo.getLastName(),personInfo.getContactInfo().getEmailAddress(),
                            personInfo.getContactInfo().getZip(),personInfo.getContactInfo().getCity(),personInfo.getContactInfo().getAddress(),
                           personInfo.getContactInfo().getPhone(), personInfo.getContactInfo().getState()};
                        session.setAttribute("user",name);
                        session.setAttribute("email",email);
                        session.setAttribute("data",data);
                        response.sendRedirect(request.getContextPath()+"/index.jsp#vieweditAddressBook");
                    }
                    else
                    {
                        session.setAttribute("errmsg","Ensure you entered correct values.<br>Thank you");
                        response.sendRedirect(request.getContextPath()+"/index.jsp#login");
                    }
                    
                }
                else if(clickedAction.equalsIgnoreCase("signup"))
                {
                    //JOptionPane.showMessageDialog(null, "You clicked "+clickedAction);
                    String firstName=request.getParameter("name").trim();
                    String email=request.getParameter("email").trim();
                    String password= request.getParameter("password").trim();
                    ContactInfo contactInfo=new ContactInfo(email);
                    Person personInfo=new Person(firstName,"",password,contactInfo);
                    JOptionPane.showMessageDialog(null, "You clicked "+clickedAction);
                    if((firstName!=null && !firstName.equals(""))&& (password!=null && !password.equals(""))&& (email!=null && !email.equals(""))&&personInfo!=null &&contactInfo!=null)
                    {
                        addressBookDatabase=new AddressBookDatabase();
                        addressBookDatabase.AddUserAccount(personInfo);
                        //Store in session for later references
                        session.setAttribute("user",firstName);
                        session.setAttribute("email",email);
                        response.sendRedirect(request.getContextPath()+"/index.jsp#addAddressBook");
                    }
                    else
                    {
                        session.setAttribute("errmsg","Ensure you entered correct values.<br>Thank you");
                        response.sendRedirect(request.getContextPath()+"/index.jsp#signup");
                    }
                    
                    
                    
                    
                }
                else if(clickedAction.equalsIgnoreCase("edit"))
                {
                    
                }
                else if(clickedAction.equalsIgnoreCase("delete"))
                {
                    
                }
                else if(clickedAction.equalsIgnoreCase("search"))
                {
                    
                }
                else if(clickedAction.equalsIgnoreCase("logout"))
                {
                    
                }
            }
            else{
                
            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    /**
     * Adds the person data temporally to array List.
     *
     * @return void
     */
    private void addPersonInfo(HttpServletRequest request, HttpServletResponse response,Person personInfo){
        
        person =new ArrayList();
        person.add(personIndex++,personInfo);
        isPersonInfoEdited=false;
    }
    /*
     * Saves the Edited person contact book to Arraylist temporaraly.
     */
     private void editPersonInfo(HttpServletRequest request, HttpServletResponse response,Person personInfo){
        Person old_person=person.get(personIndex); //get the older person object using his/her index
        person.remove(old_person);//removes it from the ArrayList
        person.add(personIndex,personInfo);//Inserts the new person object to the same postition in the List.
        isPersonInfoEdited=true;
    }
     /*
     * Deletes the person info/contact book from the ContactBook Arraylist temporaraly.
     */
     private void deletePersonInfo(HttpServletRequest request, HttpServletResponse response,Person personInfo){
        
        person.remove(personInfo);//removes it from the ArrayList
        isPersonInfoEdited=true;//yes removed completely
    }
     /*
     * Saves the Edited person contact book to Arraylist temporaraly.
     */
     private Person searchPersonInfo(HttpServletRequest request, HttpServletResponse response,Person personInfo,String type,String searchItem){
        String item="";
        Person foundPerson=null;
        if(type.equalsIgnoreCase("firstName")){
            item="firstName";
        }
        else{
            item="zip";
        }
        for(int i=0;i<person.size();i++){
            if(person.get(i).getFirstName().matches(searchItem)){
                foundPerson= person.get(i);
                break;
            }
        }
       return foundPerson;
    }
     
}
