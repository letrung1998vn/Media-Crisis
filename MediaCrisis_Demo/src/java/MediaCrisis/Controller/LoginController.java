/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.Model.UserLogin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String error = "error.html";
    private final String mainPage = "mainPage_JSP.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "http://192.168.1.18:8080/login/?"; 
            String nextPage = "";
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            url += "username=";
            url += username;
            url += "&password=";
            url += password;
            URL urlForGetRequest = new URL(url);
    String readLine = null;        
    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
    conection.setRequestMethod("GET");
    int responseCode = conection.getResponseCode();
    StringBuffer rp = new StringBuffer();
    
    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
        new InputStreamReader(conection.getInputStream()));
        while ((readLine = in.readLine()) != null) {
            rp.append(readLine);
        } in.close();
        System.out.println("JSON String Result " + rp.toString());
    } else {
        System.out.println("Không kết nối được với api");
        nextPage = error;
    }
        UserLogin userDTO = new UserLogin();
            try {
                JSONObject jobj = new JSONObject(rp.toString());
                userDTO.setId(Integer.parseInt(jobj.get("id").toString()));
                userDTO.setUsername(jobj.get("username").toString());
                userDTO.setPassword(jobj.get("password").toString());
                userDTO.setRole(jobj.get("role").toString());
                nextPage = mainPage;
            } catch (Exception e) {
                nextPage = error;
            }
        HttpSession session = request.getSession();
        session.setAttribute("USERLOGIN", userDTO);
    RequestDispatcher rd = request.getRequestDispatcher(nextPage);
    rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
