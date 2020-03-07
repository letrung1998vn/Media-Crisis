/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.APIConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Administrator
 */
public class APIConnection {

    String url;
    String method;
    List<String> listName;
    List<String> listValue;

    public APIConnection() {
    }

    public APIConnection(String url, List<String> listName, List<String> listValue) {
        this.url = url;
        listName = listName;
        listValue = listValue;
    }
    public APIConnection(String url, String method) {
        this.url = url;
        this.method = method;
    }
    public String connect() {
        String output = "";
        try {
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            Map<String, String> arguments = new HashMap<>();
            for(int i=0;i<listName.size();i++){
                arguments.put(listName.get(i), listValue.get(i));
            }
            StringJoiner sj = new StringJoiner("&");
            for (Map.Entry<String, String> entry : arguments.entrySet()) {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(out);
            }
            int responeCod = connection.getResponseCode();
            StringBuffer rp = new StringBuffer();

            if (responeCod == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    rp.append(readLine);
                }
                in.close();
                output = rp.toString();
            }
        } catch (Exception e) {
            System.out.println("Error at API Connection");
            e.printStackTrace();
        }
        return output;
    }
    public String connectWithoutParam() {
        String output = "";
        try {
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod(method);
            int responeCod = connection.getResponseCode();
            StringBuffer rp = new StringBuffer();

            if (responeCod == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                while ((readLine = in.readLine()) != null) {
                    rp.append(readLine);
                }
                in.close();
                output = rp.toString();
            }
        } catch (Exception e) {
            System.out.println("Error at API Connection");
            e.printStackTrace();
        }
        return output;
    }
}
