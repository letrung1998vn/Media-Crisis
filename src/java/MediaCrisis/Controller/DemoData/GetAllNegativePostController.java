/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.DemoData;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Post;
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
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "GetAllNegativePostController", urlPatterns = {"/GetAllNegativePostController"})
public class GetAllNegativePostController extends HttpServlet {

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
            String urlGetNewPost = "http://localhost:8181/post/getNegativePost";
            String page = request.getParameter("page");
            List<String> params = new ArrayList<>();
            List<String> value = new ArrayList<>();
            HttpSession session = request.getSession();
            params.add("page");
            value.add(page);

            //Call API Connection get all keyword
            APIConnection ac = new APIConnection(urlGetNewPost, params, value);
            String result = ac.connect();
            System.out.println(result);
            String jsonString;
            List<Post> listPost = new ArrayList<Post>();
            int thisPage = 0, maxPage = 0, totalPost = 0;
            Post post = new Post();
            try {
                JSONObject jobj = new JSONObject(result);
                thisPage = jobj.getInt("number") + 1;
                maxPage = jobj.getInt("totalPages");
                totalPost = jobj.getInt("totalElements");
                jsonString = jobj.get("content").toString();
                jsonString = jsonString.substring(1, jsonString.length() - 1);
                jsonString = jsonString.replace("},{", "}&nbsp;{");
                String[] posts = jsonString.split("&nbsp;");
                for (int i = 0; i < posts.length; i++) {
                    JSONObject obj = new JSONObject(posts[i]);
                    post = new Post();
                    post.setContent(obj.getString("postContent"));
                    post.setLike((int) obj.getDouble("numberOfReact"));
                    post.setComment((int) obj.getDouble("numberOfReply"));
                    post.setShare((int) obj.getDouble("numberOfReweet"));
                    String dateStr = obj.getString("createDate");
                    dateStr = dateStr.replace("T", " ");
                    dateStr = dateStr.replace(".000+0000", "");
                    post.setUploadDate(dateStr);
                    dateStr = obj.getString("crawlDate");
                    dateStr = dateStr.replace("T", " ");
                    dateStr = dateStr.replace("+0000", "");
                    post.setCrawlDate(dateStr);
                    listPost.add(post);
                }
            } catch (Exception e) {
                System.out.println("Ko parse duoc json obj");
            }
            session.setAttribute("THISPAGE", thisPage);
            session.setAttribute("MAXPAGE", maxPage);
            session.setAttribute("totalPost", totalPost);
            session.setAttribute("POSTS", listPost);
            String nextPage = "Demo_Data_Page.jsp";
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
