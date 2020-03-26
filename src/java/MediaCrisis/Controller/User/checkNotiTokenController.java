/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.User;
import com.sun.prism.Texture;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "checkNotiTokenController", urlPatterns = {"/checkNotiTokenController"})
public class checkNotiTokenController extends HttpServlet {

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
    private final String notificationPage = "Notification.jsp";
    private final String login = "login_JSP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String token = request.getParameter("txtToken");
            HttpSession session = request.getSession();
            int resultCode = 0;
            String result = "";
            String nextPage = "";

            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            String url = "http://localhost:8181/notificationToken/checkNotiTokenExist";

            params.add("token");
            params.add("username");
            value.add(token);
            value.add(session.getAttribute("USERID").toString());
            //Call API connection and get return JSON string
            APIConnection ac = new APIConnection(url, params, value);
            result = ac.connect();
            try {
                JSONObject jsonResult = new JSONObject(result);
                resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
                if (resultCode == 0) {
                    session.setAttribute("isEnable", true);
                } else {
                    session.setAttribute("isEnable", false);
                }
                session.setAttribute("notiToken", token);
                nextPage = notificationPage;
            } catch (Exception e) {
                System.out.println("Ko parse duoc json object");
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
