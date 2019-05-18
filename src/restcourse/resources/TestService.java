package restcourse.resources;

import restcourse.models.Person;

import restcourse.models.Money;
import java.lang.*;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST; 
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import utils.JsonExample;


@Path("/currency")
public class TestService {
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public String getCurrency() throws IOException,ParseException {
    System.out.println("getting the currency!");
    JSONObject currency = JsonExample.readJsonFromUrl("https://api.exchangeratesapi.io/latest?base=HUF&symbols=EUR");
    Double cHED = currency.getJSONObject("rates").getDouble("EUR");
    String currencyString = Double.toString(cHED);
    System.out.println(currencyString);
    return currencyString;
  }


  @POST
  @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public Response saveCurrency(Money money) throws IOException,ParseException {

    Response res;
    res = Response.ok().build();
    String fileName = "currency.txt";
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    System.out.println(money.getCurrency());
    writer.write(String.valueOf(money.getCurrency()));
    writer.close();
    return res;

  }

}
