/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.APIConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class APIConnection {

    String url, method;

    public APIConnection() {
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
