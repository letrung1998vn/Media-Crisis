/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.User;

import MediaCrisis.APIConnection.APIConnection;
import MediaCrisis.Model.Keyword;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "GetUserKeywordController", urlPatterns = {"/GetUserKeywordController"})
public class GetUserKeywordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String keywordList = "Keyword_JSP.jsp";
    private final String error = "error.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Keyword> listKeyword = new ArrayList<>();
            String nextPage = "";
            HttpSession session = request.getSession();
            String jsonString = "";

            //get parameter
            String userId = request.getParameter("userId");

            //url get all keyword config
            String urlGetAllKeyword = "http://media-crisis-api.herokuapp.com/keyword/search";
            System.out.println(urlGetAllKeyword);

            //Call API Connection get all keyword
            try {
            URL urlForGetRequest = new URL(urlGetAllKeyword);
            String readLine = null;
            
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("POST");
            conection.setDoOutput(true);
            Map<String, String> arguments = new HashMap<>();
            arguments.put("page", "1");
            arguments.put("username", userId);
            arguments.put("keyword", "");
            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : arguments.entrySet()) {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            byte[] out1 = sj.toString().getBytes(StandardCharsets.UTF_8);
            try (OutputStream os = conection.getOutputStream()) {
                os.write(out1);
            }
            int responseCode = conection.getResponseCode();
            StringBuffer rp = new StringBuffer();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    rp.append(readLine);
                }
                in.close();
                System.out.println("JSON String Result " + rp.toString());
                jsonString = rp.toString();
            } else {
                System.out.println("Loi api roi");
                nextPage = error;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            //System.out.println(jsonString);

            //Parse JSONOBJ Keyword to Keyword class
            try {
                JSONObject jobj = new JSONObject(jsonString);
//                System.out.println("Jobj: " + jobj);
                jsonString = jobj.get("content").toString();
                jsonString = jsonString.substring(1, jsonString.length() - 1);
                jsonString = jsonString.replace("},{", "}&nbsp;{");
                String[] keywords = jsonString.split("&nbsp;");
                for (int i = 0; i < keywords.length; i++) {
                    JSONObject obj = new JSONObject(keywords[i]);
                    Keyword keyWord = new Keyword(obj.getInt("id"), StringEscapeUtils.escapeHtml4(obj.get("keyword").toString()),
                            obj.get("userId").toString(), obj.getBoolean("available"), obj.getInt("version"));
                    listKeyword.add(keyWord);
                }
            } catch (JSONException e) {
                //Add logger later
                System.out.println("Ko parse duoc json obj");
            }

            session.setAttribute("LISTKEYWORD", listKeyword);
            session.setAttribute("COUNT", listKeyword.size());
            nextPage = keywordList;
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
