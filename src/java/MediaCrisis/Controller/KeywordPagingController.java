/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller;

import MediaCrisis.Model.Keyword;
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
@WebServlet(name = "KeywordPagingController", urlPatterns = {"/KeywordPagingController"})
public class KeywordPagingController extends HttpServlet {

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
            String url = "http://media-crisis-api.herokuapp.com/keyword/getAllByPage/?page=";
            String pageNum = request.getParameter("pageNum");
            url += pageNum;
            String nextPage = "";
            HttpSession session = request.getSession();
            List<JSONObject> list = new ArrayList<>();
            List<Keyword> listKeyword = new ArrayList<>();
            int maxPage = 0;
            int thisPage = 0;
            
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responeCod = connection.getResponseCode();
            StringBuffer rp = new StringBuffer();

            Keyword keyDTO = new Keyword();

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
                    String[] keywords = listJson.split(";");
                    for (int i = 0; i < keywords.length; i++) {
                        JSONObject obj = new JSONObject(keywords[i]);
                        Keyword keyWord = new Keyword(obj.getInt("id"), obj.get("keyword").toString(),
                                obj.get("userId").toString());
                        listKeyword.add(keyWord);
                    }
                } catch (JSONException e) {
                    System.out.println("Ko parse duoc json obj");
                }
                session.setAttribute("LISTKEYWORD", listKeyword);
                session.setAttribute("COUNT", listKeyword.size());
                session.setAttribute("KEYWORDADMINTHISPAGE", thisPage);
                session.setAttribute("KEYWORDADMINMAXPAGE", maxPage);
                nextPage = keywordList;

//                listJson = listJson.replace("[", "");
//                listJson = listJson.replace("]", "");
//                List<JSONObject> list = new ArrayList<>();
//                while (listJson.contains("{") && listJson.contains("}")) {
//                    String json = listJson.substring(listJson.indexOf("{"), listJson.indexOf("}") + 1);
//                    JSONObject jobj = new JSONObject(json);
//                    list.add(jobj);
//                    listJson = listJson.replace(json, "");
//                }
//                System.out.println("JSON String Result " + list.toString());
//                try {                    
//                    List<Keyword> listKeyword = new ArrayList<>();
//                    for (int i = 0; i < list.size(); i++) {
//                        Keyword keyWord = new Keyword(list.get(i).getInt("id"), list.get(i).get("keyword").toString(), 
//                                list.get(i).get("userId").toString());
//                        listKeyword.add(keyWord);
//                    }
//                    session.setAttribute("LISTKEYWORD", listKeyword);
//                    session.setAttribute("COUNT", listKeyword.size());
//                    
//                    System.out.println(listKeyword.toString());
//                    nextPage = keywordList;
//                } catch (Exception e) {
//                    System.out.println("ko parse duoc ve json object");
//                    nextPage = error;
//                }
                RequestDispatcher rd = request.getRequestDispatcher(nextPage);
                rd.forward(request, response);
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
