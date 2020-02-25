/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.NewController;

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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SearchKeywordsController", urlPatterns = {"/SearchKeywordsController"})
public class SearchKeywordsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String keywordList = "Keyword_Admin_JSP.jsp";
    private final String error = "error.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int maxPage = 0;
            int thisPage = 0;
            List<Keyword> listKeyword = new ArrayList<>();
            String nextPage = "";
            String[] usersList = null;
            HttpSession session = request.getSession();

            String search = request.getParameter("searchValue");
            String userId = request.getParameter("userId");
            String page = request.getParameter("page");

            //url get all keyword config
            String urlGetAllKeyword = "http://media-crisis-api.herokuapp.com/keyword/search/?page=";
            urlGetAllKeyword += page;
            urlGetAllKeyword += "&username=";
            urlGetAllKeyword += userId;
            urlGetAllKeyword += "&keyword=";
            urlGetAllKeyword += search;
            //System.out.println(url);

            //url get all username in keyword table
            String urlGetUsername = "http://media-crisis-api.herokuapp.com/keyword/getUsers";

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlGetAllKeyword, "GET");
            String jsonString = ac.connect();
            //System.out.println(jsonString);

            //Parse JSONOBJ Keyword to Keyword class
            try {
                JSONObject jobj = new JSONObject(jsonString);
                System.out.println("Jobj: " + jobj);
                thisPage = jobj.getInt("number") + 1;
                maxPage = jobj.getInt("totalPages");
                jsonString = jobj.get("content").toString();
                jsonString = jsonString.substring(1, jsonString.length() - 1);
                jsonString = jsonString.replace("},{", "};{");
                String[] keywords = jsonString.split(";");
                for (int i = 0; i < keywords.length; i++) {
                    JSONObject obj = new JSONObject(keywords[i]);
                    Keyword keyWord = new Keyword(obj.getInt("id"), obj.get("keyword").toString(),
                            obj.get("userId").toString());
                    listKeyword.add(keyWord);
                }
            } catch (JSONException e) {
                //Add logger later
                System.out.println("Ko parse duoc json obj");
            }

            //Call API Connection get user in keyword table
            ac = new APIConnection(urlGetUsername, "GET");
            jsonString = ac.connect();

            //Parse to array of username
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            usersList = jsonString.split(",");
            for (int i = 0; i < usersList.length; i++) {
                usersList[i] = usersList[i].substring(1, usersList[i].length() - 1);
            }
            session.setAttribute("SEARCHINGKEYWORD", search);
            session.setAttribute("SEARCHINGUSERNAMEOFKEYWORD", userId);
            session.setAttribute("USERSKEYWORDADMIN", usersList);
            session.setAttribute("LISTKEYWORD", listKeyword);
            session.setAttribute("COUNT", listKeyword.size());
            session.setAttribute("KEYWORDADMINTHISPAGE", thisPage);
            session.setAttribute("KEYWORDADMINMAXPAGE", maxPage);
            nextPage = keywordList;
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
