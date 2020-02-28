/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.Admin;

import MediaCrisis.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
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
 * @author Administrator
 */
@WebServlet(name = "ChangeUserStatusController", urlPatterns = {"/ChangeUserStatusController"})
public class ChangeUserStatusController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String userPage = "User_JSP.jsp";
    private final String error = "error.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            String url = "https://media-crisis-api.herokuapp.com/user/changeStatus/?username=";
            url += request.getParameter("username");
            String nextPage = "";

            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("POST");
            int responseCode = connection.getResponseCode();
            StringBuffer rp = new StringBuffer();
            boolean result = true;
            
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
                    JSONObject jobj1 = new JSONObject(jobj.get("user").toString());
                    result = jobj1.getBoolean("available");
                } catch (Exception e) {
                }
            } else {
                System.out.println("Loi api");
                nextPage = error;
            }
            HttpSession session = request.getSession();
            session.setAttribute("CREATE_MESSAGE", "User status changed!");
            session.setAttribute("RESULT", 2);
            session.setAttribute("SEND", true);
            List<User> list = (List<User>) session.getAttribute("LISTUSER");
            int changeStatusUserLocation = Integer.parseInt(request.getParameter("no"));
            list.get(changeStatusUserLocation).setIsAvailable(result);
            session.setAttribute("LISTUSER", list);
            nextPage = userPage;
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
