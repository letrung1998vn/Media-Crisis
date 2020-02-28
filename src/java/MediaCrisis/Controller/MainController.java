/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String loginController = "LoginController";
    private final String signupController = "SignUpController";
    private final String loginPage = "login_JSP.jsp";
    private final String deleteKeyword = "DeleteKeywordController";
    private final String createKeyword = "CreateKeywordController";
    private final String deleteKeywordAdmin = "DeleteKeywordsController";
    private final String createKeywordAdmin = "CreateKeywordAdminController";
    private final String keywordSearch = "SearchKeywordsController";
    private final String userSearch = "SearchUserController";
    private final String changeUserStatus = "ChangeUserStatusController";
    private final String createUserAdmin = "CreateUserAdminController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("btnAction");
            String url = "error.html";
            System.out.println(button);
            if (button == null) {
                System.out.println("Btn bị null");
                //do nothing
            } else if (button.equals("Login")) {
                url = loginController;
            } else if (button.equals("SignUp")) {
                url = signupController;
            } else if (button.equals("LogOut")) {
                HttpSession session = request.getSession();
                session.invalidate();
                url = loginPage;
            } else if (button.equals("DeleteKeyword")) {
                url = deleteKeyword;
            } else if (button.equals("CreateKeyword")) {
                url = createKeyword;
            } else if (button.equals("DeleteKeywordAdmin")) {
                url = deleteKeywordAdmin;
            } else if (button.equals("CreateKeywordAdmin")) {
                url = createKeywordAdmin;
            } else if (button.equals("SearchKeyword")) {
                url = keywordSearch;
            } else if (button.equals("SearchUser")) {
                url = userSearch;
            } else if (button.equals("ChangeUserStatus")) {
                url = changeUserStatus;
            } else if (button.equals("CreateUser")) {
                url = createUserAdmin;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
