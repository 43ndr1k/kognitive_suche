package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeseFaroo {

	private String key = "";

	public LeseFaroo(){
		//lese txt mit key
		key = "2CJIbhzsHU4nlSqBVZ2OP3fimb4_";
	}

	public String getHTML(String urlToRead) {
		String result = "";
		try {
			URL url;
			HttpURLConnection conn;
			url = new URL(urlToRead + "&key=" + key);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			BufferedReader rd;
			String line;
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

/**
 * Please add the following API key to your query url:
&key=2CJIbhzsHU4nlSqBVZ2OP3fimb4_
 * 
 */


