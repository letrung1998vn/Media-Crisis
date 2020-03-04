/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateUserProfileController", urlPatterns = {"/UpdateUserProfileController"})
public class UpdateUserProfileController extends HttpServlet {

    private final String error = "error.html";
    private final String update = "userProfile.jsp";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String userId = session.getAttribute("USERID").toString().trim();
        String name = request.getParameter("txtName");
        String email = request.getParameter("txtEmail");

        String url = "https://media-crisis-api.herokuapp.com/user/updateProfile";

        String nextPage = "";

        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("POST");
        conection.setDoOutput(true);
        Map<String, String> arguments = new HashMap<>();
        arguments.put("name", name);
        arguments.put("email", email);
        arguments.put("userId", userId);
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        try (OutputStream os = conection.getOutputStream()) {
            os.write(out);
        }
        int responseCode = conection.getResponseCode();
        StringBuffer rp = new StringBuffer();
        String result = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            while ((readLine = in.readLine()) != null) {
                rp.append(readLine);
            }
            in.close();
            System.out.println("JSON String Result " + rp.toString());
            result = rp.toString();
            try {
                JSONObject jsonResult = new JSONObject(result);
                int resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
                session.setAttribute("CREATE_MESSAGE", jsonResult.get("statusMessage").toString());
                session.setAttribute("RESULT", resultCode);
                if (resultCode == 2) {
                    User userDTO = (User) session.getAttribute("USERLOGIN");
                    userDTO.setName(name);
                    userDTO.setEmail(email);
                }
                if (resultCode == 3) {
                    nextPage = "login_JSP.jsp";
                } else {
                    nextPage = update;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            session.setAttribute("SEND", true);
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
        processRequest(request, response);
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
