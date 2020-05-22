package TestCase;

import Base.BasicAction;
import Utils.ParseJSON;
import Utils.Util;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class TestCase1 {

    @Test
    public void testone1(){
        BasicAction basicAction = new BasicAction();
        basicAction.updateField("duedate", "2020-02-11", "PS-16");
        basicAction.updateField("assignee","name","qa1","PS-16");
        Response response1 = basicAction.getIssueResponse("PS-16");
        Util util = new Util(response1);
        /*String responseJSONString = util.ResponseJSON(response1);*/
        JSONObject jsonObject = util.StringToJSON(response1.asString());
        Object newvalue = ParseJSON.getKey(jsonObject, "duedate");
        System.out.println("Value: "+newvalue);

    }

    /*@Test
    public void testone(){
        BasicAction basicAction = new BasicAction();
        Response response = basicAction.getIssueResponse("RT-1");
        Util util = new Util(response);
        *//*System.out.println("JSON Output" +util.ResponseJSON(response));*//*
        String responseJSONString = util.ResponseJSON(response);
        JSONObject jsonObject = util.StringToJSON(responseJSONString);
        Object value = ParseJSON.getKey(jsonObject, "status");
        System.out.println("Value: "+value);
        basicAction.transitionIssue("RT-1", 31);
        response = basicAction.getIssueResponse("RT-1");
        *//*System.out.println("JSON Output" +util.ResponseJSON(response));*//*
        responseJSONString = util.ResponseJSON(response);
        jsonObject = util.StringToJSON(responseJSONString);
        value = ParseJSON.getKey(jsonObject, "status");
        System.out.println("Value: "+value);

    }*/

    /*@Test
    public void testone(){
        BasicAction basicAction = new BasicAction();
        Response isssueCreationResponse = basicAction.createIssue("PS", "Test Summary","Bug","High");
        Util util = new Util(isssueCreationResponse);
        String responseJSONString = util.ResponseJSON(isssueCreationResponse);
        String issue = ParseJSON.getIssueKey(responseJSONString, "key");
        System.out.println(issue);
        Response response = basicAction.getIssueResponse(issue);
        util = new Util(response);
        responseJSONString = util.ResponseJSON(response);
        System.out.println(responseJSONString);
        basicAction.updateField("assignee", "name", "qa1", issue);
        Response updateResponse = basicAction.getIssueResponse(issue);
        util = new Util(updateResponse);
        String uddateResponse =util.ResponseJSON(updateResponse);
        System.out.println(uddateResponse);
        util = new Util(updateResponse);
        String updateresponseJSONString = util.ResponseJSON(updateResponse);
        String assigneebefore = ParseJSON.getFieldName(responseJSONString, "assingee", "name");
        String assigneeafter = ParseJSON.getFieldName(updateresponseJSONString, "assingee", "name");
        System.out.println(assigneebefore);
        System.out.println(assigneeafter);

    }*/

}
