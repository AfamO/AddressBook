/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tivasgroup.apps.addressbook;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fupre1
 */
public class CaptchaController extends HttpServlet {

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
        HttpSession session=request.getSession();
    int width = 150;
    int height = 50;

    

    BufferedImage bufferedImage = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = bufferedImage.createGraphics();

    Font font = new Font("Georgia", Font.BOLD, 18);
    g2d.setFont(font);

    RenderingHints rh = new RenderingHints(
           RenderingHints.KEY_ANTIALIASING,
           RenderingHints.VALUE_ANTIALIAS_ON);

    rh.put(RenderingHints.KEY_RENDERING,
           RenderingHints.VALUE_RENDER_QUALITY);

   // g2d.setRenderingHints(rh);

    GradientPaint gp = new GradientPaint(0, 0,
    Color.red, 0, height/2, Color.black, true);

    g2d.setPaint(gp);
    g2d.fillRect(0, 0, width, height);

    g2d.setColor(new Color(255, 153, 0));
    String captcha_token=Utilities.gEnerateUserToken();//Use this for a random captcha image
    Random r = new Random();
    char captcha_data[];
    captcha_data = captcha_token.toCharArray();
    //store it for later comparison
    session.setAttribute("captcha", captcha_token );

    int x = 0; 
    int y = 0;

    for (int i=0; i<captcha_data.length; i++) {
        x += 10 + (Math.abs(r.nextInt()) % 15);
        y = 20 + Math.abs(r.nextInt()) % 20;
        //g2d.drawChars(data[index], i, 1, x, y);
        g2d.drawChars(captcha_data, i, 1, x, y);
    }

    g2d.dispose();

    response.setContentType("image/png");
    OutputStream os = response.getOutputStream();
    ImageIO.write(bufferedImage, "png", os);
   
    os.close();
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
}
