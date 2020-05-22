package Utils;

import io.restassured.response.Response;
import org.json.JSONObject;

public class Util {
    Response response;
    JSONObject JsonObject;
    String responseString;
    int statusCode;

    public Util (Response response){
        this.response = response;
    }

    public int StatusCode(Response response) {
        statusCode = response.getStatusCode();
        return statusCode;
    }

    public JSONObject StringToJSON(String stringToJSON) {
        JsonObject = new JSONObject(stringToJSON);
        return JsonObject;
    }

}