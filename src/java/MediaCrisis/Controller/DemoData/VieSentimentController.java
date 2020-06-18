/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Controller.DemoData;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author letru
 */
@WebServlet(name = "VieSentimentController", urlPatterns = {"/VieSentimentController"})
public class VieSentimentController extends HttpServlet {

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
            String text = request.getParameter("txtText");
            try {
                Properties props = new Properties();
                props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
                props.setProperty("sentiment.model", "D:\\Capstone\\code\\Media-Crisis\\Web\\lib\\my.model.ser.gz");
                StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
                Annotation annotation = pipeline.process(text);
                List<CoreMap> listSentence = annotation.get(CoreAnnotations.SentencesAnnotation.class);
                List<Double> sm = new ArrayList<>();
                int totalSentiment = 0;
                double totalNegativeConfidence = 0;
                double totalPositiveConfidence = 0;
                double totalNeutralConfidence = 0;
                for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                    Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                    sm = RNNCoreAnnotations.getPredictionsAsStringList(tree);
                    totalSentiment += RNNCoreAnnotations.getPredictedClass(tree);
                    if (RNNCoreAnnotations.getPredictedClass(tree) < 2) {
                        for (int i = 0; i < 2; i++) {
                            totalNegativeConfidence += sm.get(i);
                        }
                    } else if (RNNCoreAnnotations.getPredictedClass(tree) == 2) {
                        totalNeutralConfidence += sm.get(2);
                    } else {
                        for (int i = 3; i < 5; i++) {
                            totalPositiveConfidence += sm.get(i);
                        }
                    }
                }
                double meanSentiment = (double) totalSentiment / (double) listSentence.size();
                double confidence = 0;
                String result;
                if (meanSentiment < 2.0) {
                    result = "Câu này có nghĩa xấu";
                    confidence = totalNegativeConfidence / (double) listSentence.size();
                } else if (meanSentiment == 2) {
                    result = "Câu này có nghĩa thông thường";
                    confidence = totalNeutralConfidence / (double) listSentence.size();
                } else {
                    result = "Câu này có nghĩa tốt";
                    confidence = totalPositiveConfidence / (double) listSentence.size();
                }
                session.setAttribute("CREATE_MESSAGE", result + " with confidence is: " + confidence * 100 + "%");
                session.setAttribute("RESULT", 2);
                session.setAttribute("SEND", true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String nextPage = "Demo_Sentiment_Page.jsp";
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
