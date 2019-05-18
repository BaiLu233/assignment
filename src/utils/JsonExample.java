package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonExample {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String surl) throws IOException, JSONException {

    URL url = new URL(surl);
    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
    httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

    InputStream is = httpcon.getInputStream();

    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void Currency(){
    try {
      JSONObject cHEJ = readJsonFromUrl("https://api.exchangeratesapi.io/latest?base=HUF&symbols=EUR");
      Double cHED = cHEJ.getJSONObject("rates").getDouble("EUR");
      String cHES = Double.toString(cHED);
      System.out.println("The Currency for HUF to EUR is:"+cHES);
      JSONObject cHCUJ = readJsonFromUrl("https://api.exchangeratesapi.io/latest?base=HUF&symbols=CNY,USD");
      Double cHCD = cHCUJ.getJSONObject("rates").getDouble("CNY");
      String cHCS = Double.toString(cHCD);
      System.out.println("The Currency for HUF to CNY is:"+cHCS);
      Double cHUD = cHCUJ.getJSONObject("rates").getDouble("USD");
      String cHUS = Double.toString(cHUD);
      System.out.println("The Currency for HUF to USD is:"+cHUS);

      System.out.println("DONE");
    }catch(Exception e){

      System.out.println(e);
    }
}






}
