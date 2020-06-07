/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            List<Post> listPost = new ArrayList<>();
            try {
                JSONArray list = new JSONArray(result);
                for (int i = 0; i < list.length(); i++) {
                    JSONObject json = list.getJSONObject(i);
                    String id = json.getString("id");
                    BigInteger postId = (BigInteger) json.get("postId");
                    String postContent = json.getString("postContent");
                    Date createDate = (Date) json.get("createDate");
                    String linkDetail = json.getString("linkDetail");
                    float numberOfReact = (float) json.get("numberOfReact");
                    float numberOfReweet = (float) json.get("numberOfReweet");
                    float numberOfReply = (float) json.get("numberOfReply");
                    Date crawlDate = (Date) json.get("crawlDate");
                    String keyword = json.getString("keyword");
                    boolean isNew = (boolean) json.get("isNew");
                    boolean isNegative = (boolean) json.get("isNegative");
                    String language = json.getString("language");
                    Post post = new Post();
                    post.setContent(postContent);
                    post.setComment(Math.round(numberOfReply));
                    post.setCrawlDate(crawlDate.toString());
                    post.setLike(Math.round(numberOfReact));
                    post.setShare(Math.round(numberOfReweet));
                    post.setUploadDate(createDate.toString());
                    post.setLinkDetail(linkDetail);
                    listPost.add(post);
                }
            } catch (Exception e) {
                System.out.println("Error in Get Negative Post");
                e.printStackTrace();
            }
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
