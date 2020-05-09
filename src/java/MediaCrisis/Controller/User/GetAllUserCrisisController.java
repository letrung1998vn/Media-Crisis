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
            List<UserCrisis> listAllCrisis = new ArrayList<>();
            UserCrisis uc = new UserCrisis();
            Crisis crisis = new Crisis();
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            HttpSession session = request.getSession();
            params.add("userName");
            //value.add(session.getAttribute("USERID")+"");
            value.add("le");

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlGetNewPost, params, value);
            String result = ac.connect();
            //System.out.println(result);

            try {
                JSONArray keywordHaveCrisisList = new JSONArray(result);
                JSONObject keywordObj;
                //System.out.println("This user have " + keywordHaveCrisisList.length() + " keywords have crisis.");
                for (int i = 0; i < keywordHaveCrisisList.length(); i++) {
                    uc = new UserCrisis();
                    listCrisis = new ArrayList<>();
                    keywordObj = new JSONObject(keywordHaveCrisisList.get(i).toString());
                    JSONArray crisisList = (JSONArray) keywordObj.get("crisisList");
                    keyword = keywordObj.getString("keyword");
                    uc.setKeyword(keyword);
                    //System.out.println("This keyword have " + crisisList.length() + " crisis.");
                    for (int j = 0; j < crisisList.length(); j++) {
                        //System.out.println(crisisList.get(j).toString());
                        JSONObject crisisOfEachKeyword = new JSONObject(crisisList.get(j).toString());
                        crisis = new Crisis();
                        crisis.setDetectType(crisisOfEachKeyword.getString("detectType"));
                        crisis.setPercentage(crisisOfEachKeyword.getDouble("percentage"));
                        crisis.setId(crisisOfEachKeyword.getInt("id"));
                        crisis.setType(crisisOfEachKeyword.getString("type"));
                        crisis.setContent(crisisOfEachKeyword.getString("content"));
                        listCrisis.add(crisis);
                    }
                    uc.setCrisisList(listCrisis);
                    listAllCrisis.add(uc);
                }
            } catch (JSONException e) {
                System.out.println("Dashboard convert json obj fail");
                //e.printStackTrace();
            }
            for (int i = 0; i < listAllCrisis.size(); i++) {
                System.out.println(listAllCrisis.get(i).getKeyword());
                listCrisis = listAllCrisis.get(i).getCrisisList();
                for (int j = 0; j < listCrisis.size(); j++) {
                    System.out.println(listCrisis.get(j).toString());
                }
            }
            System.out.println(listAllCrisis.size());
            String nextPage = "mainPage_JSP.jsp";
            session.setAttribute("USERALLCRISIS", listAllCrisis);
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
