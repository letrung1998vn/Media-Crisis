/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Keyword;
import MediaCrisis.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteKeywordController", urlPatterns = {"/DeleteKeywordController"})
public class DeleteKeywordController extends HttpServlet {

    private final String error = "error.html";

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
        String url = "http://localhost:8181/keyword/deleteKeyword/?";
        String idString = request.getParameter("id");
        String keywordVersion = request.getParameter("version");
        List<String> params = new ArrayList<>();
        List<String> value = new ArrayList<>();

        params.add("id");
        params.add("logVersion");
        params.add("author");
        value.add(idString);
        value.add(keywordVersion);
        value.add(session.getAttribute("USERID").toString());

        String nextPage = "";

        //Call API connection and get return JSON string
        APIConnection ac = new APIConnection(url, params, value);
        String result = ac.connect();
        System.out.println(result);
        try {
            JSONObject jsonResult = new JSONObject(result);
            session.setAttribute("CREATE_MESSAGE", jsonResult.get("statusMessage"));
            int resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
            session.setAttribute("RESULT", resultCode);
            session.setAttribute("SEND", true);
            if (resultCode == 3) {
                nextPage = "login_JSP.jsp";
            } else {
                nextPage = "MainController?btnAction=SearchKeywordUser&userId=" + session.getAttribute("USERID");
            }
        } catch (Exception e) {
            System.out.println("Ko parse duoc json object");
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
