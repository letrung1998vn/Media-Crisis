/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.Guest;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Keyword;
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
import java.util.ArrayList;
import java.util.List;
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
import org.apache.commons.lang3.StringEscapeUtils;

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
    private final String login = "login_JSP.jsp";
    private final String mainPage = "mainPage_JSP.jsp";
    private final String adminPage = "adminPage_JSP.jsp";

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

            APIConnection ac = new APIConnection(url, "GET");
            String result = ac.connect();
            System.out.println(result);

            if (result.isEmpty()) {
                request.setAttribute("CREATE_MESSAGE", "Invalid username or passowrd, please try again.");
                request.setAttribute("RESULT", 4);
                request.setAttribute("SEND", true);
                nextPage = login;
            } else {
                try {
                    JSONObject obj = new JSONObject(result);
                    User userDTO = new User();
                    userDTO.setUsername(obj.getString("userId"));
                    userDTO.setName(obj.getString("name"));
                    userDTO.setEmail(obj.getString("email"));
                    JSONObject obj1 = new JSONObject(obj.get("user").toString());
                    userDTO.setPassword(obj1.getString("password"));
                    userDTO.setRole(obj1.getString("role"));
                    userDTO.setIsAvailable(obj1.getBoolean("available"));
                    System.out.println(userDTO.toString());

                    HttpSession session = request.getSession();
                    System.out.println(userDTO);
                    if (userDTO.isIsAvailable()) {
                        if (userDTO.getRole().equals("admin")) {
                            nextPage = adminPage;
                        } else if (userDTO.getRole().equals("user")) {
                            nextPage = mainPage;
                        }
                    } else {
                        session.setAttribute("CREATE_MESSAGE", "Your account have been disabled. Please contact admin for more infomation.");
                        session.setAttribute("RESULT", 4);
                        session.setAttribute("SEND", true);
                        nextPage = login;
                    }

                    session.setAttribute("USERLOGIN", userDTO);
                    session.setAttribute("USERID", userDTO.getUsername());
                    session.setAttribute("PWD", password);
                } catch (JSONException e) {
                    System.out.println("Login controller: K parse duoc ve JSONObj");
                    //
                }
            }
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
