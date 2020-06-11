/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Comment;
import MediaCrisis.Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "GetNegativeCommentController", urlPatterns = {"/GetNegativeCommentController"})
public class GetNegativeCommentController extends HttpServlet {

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
            String txtkeyword = request.getParameter("keyword");
            HttpSession session = request.getSession();
            String url = "http://localhost:8181/comment/getNegativeCommentByKeyword";
            String result = "";
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            params.add("keyword");
            value.add(txtkeyword);
            APIConnection ac = new APIConnection(url, params, value);
            result = ac.connect();
            List<Comment> listComment = new ArrayList<>();
            try {
                JSONArray list = new JSONArray(result);
                for (int i = 0; i < list.length(); i++) {
                    JSONObject json = list.getJSONObject(i);
//                    String id = json.getString("id");
//                    BigInteger comment_id = (BigInteger) json.get("comment_id");
                    String comment_content = json.getString("comment_content");
//                    String uuid_post = json.getString("uuid_post");
                    Date createDate = (Date) json.get("createDate");
                    String linkDetail = json.getString("linkDetail");
                    float numberOfReact = (float) json.get("numberOfReact");
                    float numberOfReply = (float) json.get("numberOfReply");
                    Date crawlDate = (Date) json.get("crawlDate");
//                    boolean isNew = (boolean) json.get("isNew");
//                    boolean isNegative = (boolean) json.get("isNegative");
//                    String language = json.getString("language");
                    Comment comment = new Comment();
                    comment.setContent(comment_content);
                    comment.setReplies(Math.round(numberOfReply));
                    comment.setCrawlDate(crawlDate.toString());
                    comment.setLikes(Math.round(numberOfReact));
                    comment.setUploadDate(createDate.toString());
                    comment.setLinkDetail(linkDetail);
                    listComment.add(comment);
                }
                request.setAttribute("listComment", listComment);
            } catch (Exception e) {
                System.out.println("Error in Get Negative Post");
                e.printStackTrace();
            }
            String nextPage = "NegativeCommentDetail.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
            rd.forward(request, response);
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
