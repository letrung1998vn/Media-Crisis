/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.EmailContentModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author letru
 */
public class WebLinkContent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String emailContent = "EmailContent.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String keyWord = request.getParameter("keyword");
            String id = request.getParameter("id");
            String nextPage = "";
            String url = "http://localhost:8181/notification/emailContentList";
            String result = "";
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            params.add("keyword");
            params.add("id");
            value.add(keyWord);
            value.add(id);
            APIConnection ac = new APIConnection(url, params, value);
            result = ac.connect();
            try {
                JSONObject jsonResult = new JSONObject(result);
                String crisisKeyword = jsonResult.getString("keyword");
                JSONArray listLink = (JSONArray) jsonResult.get("listLinkDetail");
                List<EmailContentModel> list = new ArrayList<>();
                for (int i = 0; i < listLink.length(); i++) {
                    String str = listLink.getString(i);
                    EmailContentModel ecm = new EmailContentModel();
                    String delim = "and||and";
                    String content = str.substring(0, str.indexOf(delim));
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String linkDetail = str.substring(0, str.indexOf(delim));
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String type = str.substring(0, str.indexOf(delim));
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String std = str.substring(0, str.indexOf(delim));
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String number = str;
                    ecm.setContent(content.trim());
                    ecm.setLink(linkDetail.trim());
                    ecm.setType(type.trim());
                    ecm.setStd(std.trim());
                    ecm.setNumber(number.trim());
                    list.add(ecm);
                }
                request.setAttribute("size", list.size());
                request.setAttribute("keyword", crisisKeyword);
                request.setAttribute("list", list);
                nextPage = emailContent;
            } catch (Exception e) {
                e.printStackTrace();
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
