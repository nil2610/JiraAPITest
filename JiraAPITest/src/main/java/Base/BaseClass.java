package Base;

import Utils.Constant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class BaseClass {

    public Response response;

    public Response makeGetCall (String endPoint) {
        RestAssured.baseURI = Constant.URL_String;
        Response response = null;
        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .auth().preemptive()
                    .basic(Constant.username,Constant.password)
                    .get(endPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response makePostCall (String jsonBody, String endPoint) {
        RestAssured.baseURI = Constant.URL_String;
        Response response = null;
        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .auth().preemptive()
                    .basic(Constant.username,Constant.password)
                    .body(jsonBody)
                    .post(endPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response makePutCall (String jsonBody, String endPoint) {
        RestAssured.baseURI = Constant.URL_String;
        Response response = null;
        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .auth().preemptive()
                    .basic(Constant.username,Constant.password)
                    .body(jsonBody)
                    .put(endPoint);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
