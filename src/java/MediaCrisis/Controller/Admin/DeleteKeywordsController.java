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
            String nextPage = "";
            HttpSession session = request.getSession();

            //url get all keyword config
            String urlDeleteKeyword = "http://media-crisis-api.herokuapp.com/keyword/deleteKeyword/?";
            urlDeleteKeyword += "id=";
            urlDeleteKeyword += "deleteKeywordId";
            //System.out.println(urlDeleteKeyword);

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlDeleteKeyword, "POST");
            String jsonString = ac.connect();
            if (jsonString.isEmpty()) {
                request.setAttribute("CREATE_MESSAGE", "Delete keyword fail, please try again.");
                request.setAttribute("RESULT", 4);
                request.setAttribute("SEND", true);
            } else {
                request.setAttribute("CREATE_MESSAGE", "Deleted keyword.");
                request.setAttribute("RESULT", 2);
                request.setAttribute("SEND", true);
            }
            nextPage = "MainController?btnAction=SearchKeyword&page=" + session.getAttribute("KEYWORDADMINTHISPAGE").toString() 
                    +"&userId=" + session.getAttribute("SEARCHINGUSERNAMEOFKEYWORD").toString() + "&searchValue=" 
                    + session.getAttribute("SEARCHINGKEYWORD").toString();
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
