/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.HistoryRatioModel;
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
import org.json.JSONObject;

/**
 *
 * @author letru
 */
@WebServlet(name = "GetAllUserRatioController", urlPatterns = {"/GetAllUserRatioController"})
public class GetAllUserRatioController extends HttpServlet {

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
            HttpSession session = request.getSession();
            String url = "http://localhost:8181/user/getAllUserRatio";
            String result = "";
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            params.add("userName");
            value.add(session.getAttribute("USERID") + "");
            APIConnection ac = new APIConnection(url, params, value);
            result = ac.connect();
            System.out.println("Result: " + result);
            List<String> keywords = new ArrayList<>();
            try {
                JSONArray jsonResult = new JSONArray(result);
                List<HistoryRatioModel> listRatioModel = new ArrayList<>();
                for (int i = 0; i < jsonResult.length(); i++) {
                    JSONObject json = jsonResult.getJSONObject(i);
                    String postKeyword = json.getString("keyword");
                    if (!keywords.contains(postKeyword)) {
                        keywords.add(postKeyword);
                    }
                    System.out.println("Keyword: " + postKeyword);
                    JSONArray listRatio = (JSONArray) json.get("listRatio");
                    String type = json.getString("type");
                    List<String> listRatioStr = new ArrayList<>();
                    List<String> listDateStr = new ArrayList<>();
                    for (int x = 0; x < listRatio.length(); x++) {
                        String str = listRatio.getString(x);
                        String delim = "and||and";
                        String ratio = str.substring(0, str.indexOf(delim));
                        listRatioStr.add(ratio);
                        str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                        str = str.trim();
                        String date = str;
                        listDateStr.add(date);
                    }
                    HistoryRatioModel hrm = new HistoryRatioModel();
                    System.out.println("Date:" + listDateStr);
                    System.out.println("Ratio:" + listRatioStr);
                    hrm.setKeyword(postKeyword);
                    hrm.setListDateStr(listDateStr);
                    hrm.setListRatioStr(listRatioStr);
                    hrm.setType(type);
                    listRatioModel.add(hrm);
                }
                session.setAttribute("listRatioHistory", listRatioModel);
                session.setAttribute("RATIOKEYWORDLIST", keywords);
                String nextPage = "mainPage_JSP.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(nextPage);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Get User ratio fail");
            }
        } catch (Exception e) {
            HttpSession session = request.getSession();
            e.printStackTrace();
            session.setAttribute("CREATE_MESSAGE", "Unexpected error, please try login again!");
            session.setAttribute("RESULT", 3);
            session.setAttribute("SEND", true);
            RequestDispatcher rd = request.getRequestDispatcher("login_JSP.jsp");
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
