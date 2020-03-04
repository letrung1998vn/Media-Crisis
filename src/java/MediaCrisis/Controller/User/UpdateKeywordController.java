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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "UpdateKeywordController", urlPatterns = {"/UpdateKeywordController"})
public class UpdateKeywordController extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String url = "http://media-crisis-api.herokuapp.com/keyword/updateKeyword";
            String idString = request.getParameter("txtKeywordId");
            int id = Integer.parseInt(idString);
            String keywordVersion = request.getParameter("txtLogversion");
            String newKeyword = request.getParameter("txtNewKeyword").trim();
            String posString = request.getParameter("txtNo");
            int pos = Integer.parseInt(posString);
            boolean validate = true;
            String nextPage = "";
            String result = "";

            if (newKeyword.isEmpty()) {
                session.setAttribute("CREATE_MESSAGE", "Keyword field is empty, can not add");
                session.setAttribute("RESULT", 4);
                validate = false;
            } else {
                List<Keyword> list = new ArrayList<>();
                try {
                    list = (List<Keyword>) session.getAttribute("LISTKEYWORD");
                    for (int i = 0; i < list.size(); i++) {
                        if (i != pos) {
                            if (list.get(i).getKeyword().equals(newKeyword)) {
                                validate = false;
                                session.setAttribute("CREATE_MESSAGE", "This keyword is existed!");
                                session.setAttribute("RESULT", 4);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                session.setAttribute("SEND", true);
                nextPage = "MainController?btnAction=SearchKeywordUser&userId=" + session.getAttribute("USERID");
            }

            if (validate) {
//                url += "keyword=";
//                url += newKeyword;
//                url += "&keywordId=";
//                url += id;
//                url += "&logVersion=";
//                url += keywordVersion;
//                url += "&author=";
//                url += session.getAttribute("USERID");
//
//                System.out.println(url);
                try {
                    URL urlForGetRequest = new URL(url);
                    String readLine = null;
                    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
                    conection.setRequestMethod("POST");
                    conection.setDoOutput(true);
                    Map<String, String> arguments = new HashMap<>();
                    arguments.put("keyword", newKeyword);
                    arguments.put("keywordId", idString);
                    arguments.put("logVersion", keywordVersion);
                    arguments.put("author", session.getAttribute("USERID").toString());
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
                        result = rp.toString();
                    } else {
                        System.out.println("Loi api roi");
                        nextPage = error;
                    }
                } catch (Exception e) {
                    System.out.println("Error at Create new keyword controller: ");
                    e.printStackTrace();
                }
                System.out.println(result);
                try {
                    JSONObject jsonResult = new JSONObject(result);
                    session.setAttribute("CREATE_MESSAGE", jsonResult.get("statusMessage"));
                    int resultCode = Integer.parseInt(jsonResult.get("statusCode").toString());
                    session.setAttribute("RESULT", resultCode);
                    session.setAttribute("SEND", true);
                    if (resultCode == 3) {
                        nextPage = "login_JSP.jsp";
                    } else {
                        nextPage = "MainController?btnAction=SearchKeywordUser&userId=" + session.getAttribute("USERID");
                    }
                } catch (Exception e) {
                    System.out.println("Ko parse duoc json object");
                }
            } else {
                session.setAttribute("UPDATINGVALUE", newKeyword);
                session.setAttribute("UPDATINGPOS", pos);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
