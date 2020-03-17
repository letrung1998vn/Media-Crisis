/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.Guest;

import MediaCrisis.APIConnection.APIConnection;
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

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

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
    private final String loginPage = "login_JSP.jsp";
    private final String signupPage = "signup_JSP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            String url = "http://localhost:8181/user/registration/?";
            String nextPage = loginPage;
            HttpSession session = request.getSession();
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            User userDTO = new User();

            //Hash password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordInByte = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : passwordInByte) {
                sb.append(String.format("%02x", b));
            }

            params.add("username");
            params.add("name");
            params.add("password");
            params.add("email");

            value.add(username);
            value.add(name);
            value.add(sb.toString());
            value.add(email);

            //Call API connection and get return JSON string
            APIConnection ac = new APIConnection(url, params, value);
            String result = ac.connect();
            //System.out.println(result);

            try {
                JSONObject returnObject = new JSONObject(result);
                int resultCode = returnObject.getInt("statusCode");
                if (resultCode != 2) {
                    userDTO.setUsername(username);
                    userDTO.setName(name);
                    userDTO.setEmail(email);
                    session.setAttribute("INPUT_USER", userDTO);
                    nextPage = signupPage;
                }
                session.setAttribute("CREATE_MESSAGE", returnObject.get("statusMessage"));
                session.setAttribute("RESULT", resultCode);
            } catch (JSONException e) {
                System.out.println("Login controller: K parse duoc ve JSONObj");
            }
            session.setAttribute("SEND", true);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
