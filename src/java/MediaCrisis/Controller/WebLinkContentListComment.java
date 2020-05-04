/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.EmailContentListModel;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author letru
 */
@WebServlet(name = "WebLinkContentListComment", urlPatterns = {"/WebLinkContentListComment"})
public class WebLinkContentListComment extends HttpServlet {

    private final String emailContent = "EmailContentRatio.jsp";

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
            String keyWord = request.getParameter("keyword");
            String id = request.getParameter("comment_id");
            String time = request.getParameter("time");
            String nextPage = "";
            String url = "http://localhost:8181/notification/emailContentListComment";
            String result = "";
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            params.add("keyword");
            params.add("comment_id");
            params.add("time");
            value.add(keyWord);
            value.add(id);
            value.add(time);
            APIConnection ac = new APIConnection(url, params, value);
            result = ac.connect();
            System.out.println("Result: " + result);
            try {
                JSONObject jsonResult = new JSONObject(result);
                String commentKeyword = jsonResult.getString("keyword");
                System.out.println("List Comment");
                JSONArray listLink = (JSONArray) jsonResult.get("listContentAndLink");
                JSONArray listRatio = (JSONArray) jsonResult.get("listRatio");
                List<EmailContentListModel> listEmailContent = new ArrayList<>();
                List<String> listRatioStr = new ArrayList<>();
                List<String> listDateStr = new ArrayList<>();
                for (int i = 0; i < listLink.length(); i++) {
                    String str = listLink.getString(i);
                    String delim = "and||and";
                    String content = str.substring(0, str.indexOf(delim));
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String linkDetail = str;
                    EmailContentListModel eclm = new EmailContentListModel();
                    eclm.setContent(content);
                    eclm.setLink(linkDetail);
                    listEmailContent.add(eclm);
                }
                for (int i = 0; i < listRatio.length(); i++) {
                    String str = listRatio.getString(i);
                    String delim = "and||and";
                    String ratio = str.substring(0, str.indexOf(delim));
                    listRatioStr.add(ratio);
                    str = str.substring(str.indexOf(delim) + delim.length(), str.length());
                    str = str.trim();
                    String date = str;
                    listDateStr.add(date);

                }
                request.setAttribute("keyword", commentKeyword);
                request.setAttribute("listEmailContent", listEmailContent);
                request.setAttribute("listRatio", listRatioStr);
                request.setAttribute("listDate", listDateStr);
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
