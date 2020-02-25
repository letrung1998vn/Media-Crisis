/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.Model.Keyword;
import MediaCrisis.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
@WebServlet(name = "SearchUserController", urlPatterns = {"/SearchUserController"})
public class SearchUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String error = "error.html";
    private final String userPage = "User_JSP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "https://media-crisis-api.herokuapp.com/user/findAllUserInfo/?username=";
            url += request.getParameter("txtSearch");
            url += "&page=1";
            String nextPage = "";
            int maxPage = 0;
            int thisPage = 0;
            HttpSession session = request.getSession();

            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responeCod = connection.getResponseCode();
            StringBuffer rp = new StringBuffer();

            if (responeCod == HttpURLConnection.HTTP_OK) {
                //read and get data from url
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    rp.append(readLine);
                }
                in.close();

                String listJson = rp.toString();
                try {
                    JSONObject jobj = new JSONObject(listJson);
                    System.out.println("Jobj: " + jobj);
                    thisPage = jobj.getInt("number") + 1;
                    maxPage = jobj.getInt("totalPages");
                    listJson = jobj.get("content").toString();
                    listJson = listJson.substring(1, listJson.length() - 1);
                    listJson = listJson.replace("},{", "};{");
                    String[] users = listJson.split(";");
                    List<User> listUser = new ArrayList<User>();
                    for (int i = 0; i < users.length; i++) {
                        JSONObject obj = new JSONObject(users[i]);
                        User userObj = new User();
                        userObj.setUsername(obj.getString("userId"));
                        userObj.setName(obj.getString("name"));
                        userObj.setEmail(obj.getString("email"));
                        JSONObject obj1 = new JSONObject(obj.get("user").toString());
                        userObj.setPassword(obj1.getString("password"));
                        userObj.setRole(obj1.getString("role"));
                        userObj.setIsAvailable(obj1.getBoolean("available"));
                        System.out.println(userObj.toString());
                        //có noti thì thêm vào đây
                        listUser.add(userObj);
                        session.setAttribute("LISTUSER", listUser);
                        session.setAttribute("COUNT", listUser.size());
                    }

                } catch (JSONException e) {
                    System.out.println("Ko parse dc ve jsonobj");
                    session.setAttribute("LISTUSER", null);
                    session.setAttribute("COUNT", null);
                }
            }
            nextPage = userPage;

            session.setAttribute("USERADMINTHISPAGE", thisPage);
            session.setAttribute("USERADMINMAXPAGE", maxPage);
            session.setAttribute("SEARCHINGKEYWORD", "");
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
