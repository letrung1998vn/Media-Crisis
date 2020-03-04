/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

    private final String error = "error.html";
    private final String updated = "changePassword.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "http://localhost:8181/user/updatePassword/?";

//        String oldPassword = request.getParameter("txtOldPassword");
        String newPassword = request.getParameter("txtPassword");
//        String confirmPassword = request.getParameter("txtConfirmPassword");

        String nextPage = "";

        HttpSession session = request.getSession();
        String userId = session.getAttribute("USERID").toString().trim();
//        String oldPassword = session.getAttribute("PWD").toString().trim();

        url += "userName=";
        url += userId;
        url += "&password=";

//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] oldPasswordInByte = md.digest(oldPassword.getBytes(StandardCharsets.UTF_8));
//        StringBuilder inputtedSb = new StringBuilder();
//        for (byte b : oldPasswordInByte) {
//            inputtedSb.append(String.format("%02x", b));
//        }
        MessageDigest md1 = MessageDigest.getInstance("SHA-256");
        byte[] newPasswordInByte = md1.digest(newPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder newPasswordSb = new StringBuilder();
        for (byte b1 : newPasswordInByte) {
            newPasswordSb.append(String.format("%02x", b1));
        }

        url += newPasswordSb;
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        int responseCode = connection.getResponseCode();
        StringBuffer rp = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            while ((readLine = in.readLine()) != null) {
                rp.append(readLine);
            }
            in.close();
            System.out.println("JSON String Result " + rp.toString());
            try {
                JSONObject jobj = new JSONObject(rp.toString());
                nextPage = updated;

                session.setAttribute("CHANGE_MESSAGE", "Changed password successfully.");
                session.setAttribute("RESULT", 2);
                session.setAttribute("SEND", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Loi api roi");
            nextPage = error;
        }
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);

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
            Logger.getLogger(UpdatePasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdatePasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
