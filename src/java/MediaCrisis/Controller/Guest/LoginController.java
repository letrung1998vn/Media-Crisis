/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.Guest;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.User;
import java.io.IOException;
import java.io.PrintWriter;
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
    private final String mainPage = "GetAllUserCrisisController";
    private final String adminPage = "adminPage_JSP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "http://localhost:8181/user/login/?";
            String nextPage = "";
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            HttpSession session = request.getSession();
            User userDTO = new User();

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String token = request.getParameter("txtToken");

            //Hash password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordInByte = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : passwordInByte) {
                sb.append(String.format("%02x", b));
            }

            //Prepare value for connection
            params.add("username");
            params.add("password");
            params.add("notiToken");
            value.add(username);
            value.add(sb.toString());
            value.add(token);
            //Call API connection and get return JSON string
            APIConnection ac = new APIConnection(url, params, value);
            String result = ac.connect();

            try {
                JSONObject returnObject = new JSONObject(result);
                int resultCode = returnObject.getInt("statusCode");
                System.out.println(returnObject.toString());
                //Paging
                if (resultCode == 2) {
                    JSONObject obj = new JSONObject(returnObject.get("obj").toString());
                    userDTO.setUsername(obj.getString("userName"));
                    userDTO.setPassword(obj.getString("password"));
                    userDTO.setRole(obj.getString("role"));
                    userDTO.setIsAvailable(obj.getBoolean("available"));
                    JSONObject obj1 = new JSONObject(obj.get("user").toString());
                    userDTO.setName(obj1.getString("name"));
                    userDTO.setEmail(obj1.getString("email"));
                    userDTO.setLink_webhook(obj1.getString("link_webhook"));
                    if (userDTO.getRole().equals("admin")) {
                        nextPage = adminPage;
                    } else if (userDTO.getRole().equals("user")) {
                        nextPage = mainPage;
                        if (!token.isEmpty()) {
                            params = new ArrayList<>();
                            value = new ArrayList<>();
                            url = "http://localhost:8181/notificationToken/checkNotiTokenExist";
                            params.add("token");
                            params.add("username");
                            value.add(token);
                            System.out.println("Token: " + token);
                            value.add(userDTO.getUsername());
                            //Call API connection and get return JSON string
                            ac = new APIConnection(url, params, value);
                            result = ac.connect();
                            System.out.println("Check Result:" + result);
                            try {
                                JSONObject jsonResult = new JSONObject(result);
                                resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
                                if (resultCode == 0) {
                                    session.setAttribute("isEnable", true);
                                    System.out.println("Register Token: " + true);
                                    url = "http://localhost:8181/notificationToken/enableNotiToken";
                                    params.add("token");
                                    params.add("username");
                                    value.add(token);
                                    value.add(userDTO.getUsername());
                                    //Call API connection and get return JSON string
                                    ac = new APIConnection(url, params, value);
                                    result = ac.connect();
                                    System.out.println("Enable Result:" + result);
                                    try {
                                        jsonResult = new JSONObject(result);
                                        resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
                                        session.setAttribute("CREATE_MESSAGE", jsonResult.get("statusMessage"));
                                        session.setAttribute("RESULT", resultCode);
                                        session.setAttribute("SEND", true);
                                        if (resultCode == 3) {
                                            nextPage = login;
                                        } else {
                                            session.setAttribute("isEnable", false);
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ko parse duoc json object");
                                    }
                                } else if (resultCode == 1) {
                                    session.setAttribute("isEnable", false);
                                } else if (resultCode == 5) {
                                    session.setAttribute("isEnable", true);
                                }
                                session.setAttribute("notiToken", token);
                            } catch (Exception e) {
                                System.out.println("Ko parse duoc json object");
                            }
                        } else {
                            session.setAttribute("isEnable", false);
                        }
                    }
                } else {
                    session.setAttribute("CREATE_MESSAGE", returnObject.get("statusMessage"));
                    session.setAttribute("RESULT", resultCode);
                    session.setAttribute("SEND", true);
                    nextPage = login;
                }
                session.setAttribute("USERLOGIN", userDTO);
                session.setAttribute("USERID", userDTO.getUsername());
                session.setAttribute("PWD", password);
            } catch (JSONException e) {
                System.out.println("Login controller: K parse duoc ve JSONObj");
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
