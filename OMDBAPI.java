
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Scanner;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class OMDBAPI {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

	public static void main(String[] args) throws Exception {

		System.out.println("Enter your movie name:"); 
    Scanner scan = new Scanner(System.in);
    String MovieTitle="";
    MovieTitle+=scan.nextLine();
    scan.close();
    String url = "http://www.omdbapi.com/?t='"+MovieTitle+"'&apikey=8d6012";
    JSONObject json = readJsonFromUrl(url);
    System.out.println(json.toString());
    System.out.println(json.get("id"));
		
	}
}
