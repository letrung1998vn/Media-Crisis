/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Crisis;
import MediaCrisis.Model.UserCrisis;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "GetAllUserCrisisController", urlPatterns = {"/GetAllUserCrisisController"})
public class GetAllUserCrisisController extends HttpServlet {

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
            String urlGetNewPost = "http://localhost:8181/user/getAllUserCrisis";
            String keyword, jsonString;
            List<Crisis> listCrisis = new ArrayList<>();
            Crisis crisis = new Crisis();
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            List<String> keywords = new ArrayList<>();
            HttpSession session = request.getSession();
            params.add("userName");
            value.add(session.getAttribute("USERID")+"");

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlGetNewPost, params, value);
            String result = ac.connect();
            //System.out.println(result);

            try {
                JSONArray crisisList = new JSONArray(result);
                JSONObject keywordObj;
                //System.out.println("This user have " + keywordHaveCrisisList.length() + " keywords have crisis.");
                for (int i = 0; i < crisisList.length(); i++) {
                    keywordObj = new JSONObject(crisisList.get(i).toString());
                    //System.out.println("This keyword have " + crisisList.length() + " crisis.");
                    //System.out.println(crisisList.get(j).toString());
                    crisis = new Crisis();
                    crisis.setDetectType(keywordObj.getString("detectType"));
                    crisis.setPercentage(keywordObj.getDouble("percentage"));
                    crisis.setKeyword(keywordObj.getString("keyword"));
                    if (!keywords.contains(crisis.getKeyword())) {
                        keywords.add(crisis.getKeyword());
                    }
                    String dateStr = keywordObj.getString("detectDate");
                    dateStr = dateStr.replace("T", " ");
                    dateStr = dateStr.replace(".000+0000", "");
                    crisis.setDetectDate(dateStr);
                    crisis.setId(keywordObj.getInt("id"));
                    crisis.setType(keywordObj.getString("type"));
                    crisis.setContent(keywordObj.getString("content"));
                    listCrisis.add(crisis);
                }
            } catch (JSONException e) {
                System.out.println("Dashboard convert json obj fail");
                //e.printStackTrace();
            }
            System.out.println(listCrisis.size());
            System.out.println(keywords.size());
            String nextPage = "mainPage_JSP.jsp";
            session.setAttribute("USERALLCRISIS", listCrisis);
            session.setAttribute("KEYWORDLIIST", keywords);
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
