package de.cpdfjarrtm.pdfborx.onlinefetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Lorenz
 */
public class Connection {

    private final String USER_AGENT = "Mozilla/5.0";
    HttpURLConnection con;
    

    public Connection() {
        
    }

      /**
     *
     * @param url to build connection to
     * @return response as string
     * @throws IOException
     */
    // HTTP GET request
    public String createConnection(String url) throws IOException {

        URL obj = new URL(url);
        con = (HttpURLConnection) obj.openConnection();

        //set Timeout to 5 seconds
        con.setReadTimeout(50000);

        // optional default is GET
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode + "\n");

        if (responseCode == 200) {
            System.out.println("Connection okay ...");
        } else if (responseCode == 403) {
            System.err.println("HTTP Request forbidden");
            return "Error";
        } else {
            System.err.println("Something went wrong ... ");
            return "Error";
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        StringBuffer response = new StringBuffer();

        // add line by line to StringBuffer response from the Buffered Reader in                         
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }
        // close connection
        in.close();
        return response.toString();
    }
}
