/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.Admin;

import MediaCrisis.APIConnection.APIConnection;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteKeywordsController", urlPatterns = {"/DeleteKeywordsController"})
public class DeleteKeywordsController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            //get parameter
            String deleteKeywordId = request.getParameter("id");
            String keywordVersion = request.getParameter("version");
            String nextPage = "";
            HttpSession session = request.getSession();

            //url get all keyword config
            String urlDeleteKeyword = "http://media-crisis-api.herokuapp.com/keyword/deleteKeyword/?";
            urlDeleteKeyword += "id=";
            urlDeleteKeyword += deleteKeywordId;
            urlDeleteKeyword += "&logVersion=";
            urlDeleteKeyword += keywordVersion;
            urlDeleteKeyword += "&author=";
            urlDeleteKeyword += session.getAttribute("USERID");
            //System.out.println(urlDeleteKeyword);

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlDeleteKeyword, "POST");
            String jsonString = ac.connect();
            try {
                JSONObject jsonResult = new JSONObject(jsonString);
                session.setAttribute("CREATE_MESSAGE", jsonResult.get("statusMessage"));
                session.setAttribute("RESULT", jsonResult.get("statusCode"));
            } catch (JSONException e) {
                System.out.println("Ko parse duoc json object");
            }
            session.setAttribute("SEND", true);
            nextPage = "MainController?btnAction=SearchKeyword&page=" + session.getAttribute("KEYWORDADMINTHISPAGE")
                    + "&userId=" + session.getAttribute("SEARCHINGUSERNAMEOFKEYWORD") + "&searchValue="
                    + session.getAttribute("SEARCHINGKEYWORD");
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
