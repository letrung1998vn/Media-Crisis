/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Keyword;
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
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "GetUserKeywordController", urlPatterns = {"/GetUserKeywordController"})
public class GetUserKeywordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String keywordList = "Keyword_JSP.jsp";
    private final String error = "error.html";
    private final String login = "login_JSP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Keyword> listKeyword = new ArrayList<>();
            String nextPage = "";
            HttpSession session = request.getSession();
            String jsonString = "";

            //url get all keyword config
            String urlGetAllKeyword = "http://localhost:8181/user/getUserKeyword/?username=";
            urlGetAllKeyword += session.getAttribute("USERID");

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlGetAllKeyword, "GET");
            String result = ac.connectWithoutParam();

            try {
                JSONObject returnObject = new JSONObject(result);
                int resultCode = returnObject.getInt("statusCode");
                //System.out.println(returnObject.toString());
                if (resultCode == 2) {
                    jsonString = returnObject.get("obj").toString();
                    jsonString = jsonString.substring(1, jsonString.length() - 1);
                    jsonString = jsonString.replace("},{", "}&nbsp;{");
                    String[] keywords = jsonString.split("&nbsp;");
                    for (int i = 0; i < keywords.length; i++) {
                        JSONObject obj = new JSONObject(keywords[i]);
                        JSONObject obj1 = new JSONObject(obj.get("user").toString());
                        Keyword keyWord = new Keyword(obj.getInt("id"), StringEscapeUtils.escapeHtml4(obj.get("keyword").toString()),
                                obj1.get("userName").toString(), obj.getBoolean("available"), obj.getInt("version"), obj.getDouble("percent_of_crisis"));
                        listKeyword.add(keyWord);
                    }
                    session.setAttribute("LISTKEYWORD", listKeyword);
                    session.setAttribute("COUNT", listKeyword.size());
                    nextPage = keywordList;
                } else if (resultCode == 3) {
                    session.setAttribute("LISTKEYWORD", listKeyword);
                    session.setAttribute("COUNT", listKeyword.size());
                    session.setAttribute("CREATE_MESSAGE", returnObject.get("statusMessage"));
                    session.setAttribute("RESULT", resultCode);
                    session.setAttribute("SEND", true);
                    nextPage = login;
                } else {
                    session.setAttribute("LISTKEYWORD", listKeyword);
                    session.setAttribute("COUNT", listKeyword.size());
                    session.setAttribute("CREATE_MESSAGE", returnObject.get("statusMessage"));
                    session.setAttribute("RESULT", resultCode);
                    session.setAttribute("SEND", true);
                    nextPage = keywordList;
                }
            } catch (JSONException e) {
                //Add logger later
                System.out.println("Ko parse duoc json obj");
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
