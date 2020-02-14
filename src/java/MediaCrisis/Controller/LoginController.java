/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            throws ServletException, IOException, JSONException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "https://media-crisis-api.herokuapp.com/user/login/?";
            String nextPage = "";
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            url += "username=";
            url += username;
            url += "&password=";

            //Hash password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordInByte = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : passwordInByte) {
                sb.append(String.format("%02x", b));
            }
            url += sb.toString();
            System.out.println(url);

            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            StringBuffer rp = new StringBuffer();
            User userDTO = new User();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    rp.append(readLine);
                }
                in.close();
                System.out.println("JSON String Result " + rp.toString());
                try {
                    JSONObject jobj = new JSONObject(rp.toString());
                    System.out.println("JSONObj after parse" + jobj.toString());
                    userDTO.setName(jobj.get("name").toString());
                    userDTO.setUsername(jobj.get("userId").toString());
                    JSONObject jobj1 = new JSONObject(jobj.get("user").toString());
                    System.out.println("JSONObj inside" + jobj1.toString());
                    userDTO.setPassword(jobj1.get("password").toString());
                    userDTO.setRole(jobj1.get("role").toString());
                    userDTO.setEmail(jobj.get("email").toString());
                    System.out.println(userDTO.toString());
                    nextPage = mainPage;
                } catch (Exception e) {
                    System.out.println("ko parse duoc ve json object");
                    nextPage = error;
                }
            } else {
                System.out.println("Loi api roi");
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
        } catch (NoSuchAlgorithmException ex) {
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
        } catch (NoSuchAlgorithmException ex) {
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
