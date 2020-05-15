package Base;

import Utils.Constant;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicAction {

    public static String issueCreationString(String ProjectKey, String Summary, String IssueType, String Priority){
        String String1 ="{\"fields\":{\"project\":{\"key\":\"";
        String String2 = "\"},\"summary\":\"";
        String String3 = "\",\"issuetype\":{\"name\":\"";
        String String4 = "\"},\"priority\":{\"name\":\"";
        String String5 = "\"}}}";
        String issueCreationString = String1 + ProjectKey + String2 + Summary + String3 + IssueType + String4 + Priority + String5;
        return issueCreationString;
    }

    public static String subTaskCreationString(String ProjectKey, String Summary, String Priority, String ParentKey ){
        String String1 ="{\"fields\":{\"project\":{\"key\":\"";
        String String2 = "\"},\"summary\":\"";
        String String3 = "\",\"issuetype\":{\"name\":\"Sub-task\"},\"priority\":{\"name\":\"";
        String String4 = "\"},\"parent\":{\"key\":\"";
        String String5 = "\"}}}";
        String subTaskString = String1 + ProjectKey + String2 + Summary + String3 + Priority + String4 + ParentKey +String5;
        return subTaskString;
    }

    public static String updateFieldString(String fieldName, String fieldValue){
        String fieldUpdate = "{\"fields\":{\""+fieldName+"\":\""+fieldValue+"\"}}";
        return fieldUpdate;
    }

    public static String updateFieldString(String fieldName, String name, String value){
        String fieldUpdate = "{\"fields\":{\""+fieldName+"\":{\""+name+"\":\""+value+"\"}}}";
        return fieldUpdate;
    }

    public static String transitionString(int transitionID){
        String transitionString = "{\"transition\":{\"id\":\"" + transitionID + "\"}}";
        return transitionString;
    }

    public static String linkingIssue(String linktype, String inwardissue, String outwardissue){
        String linkIssueString = "{\"type\":{\"name\":\""+linktype +"\"},\"inwardIssue\":{\"key\":\""
                +inwardissue+"\"},\"outwardIssue\":{\"key\":\""+outwardissue+"\"}}";
        return linkIssueString;
    }

    public Response createIssue(String ProjectKey, String Summary, String IssueType, String Priority){
        String jsonBody = BasicAction.issueCreationString(ProjectKey, Summary, IssueType, Priority);
        String endPoint = Constant.issueEndPoint;
        BaseClass b = new BaseClass();
        Response response = b.makePostCall(jsonBody, endPoint);
        return response;
    }

    public Response createSubTask(String ProjectKey, String Summary, String Priority, String ParentKey){
        String jsonBody = BasicAction.subTaskCreationString(ProjectKey, Summary,Priority, ParentKey);
        String endPoint = Constant.issueEndPoint;
        BaseClass b = new BaseClass();
        Response response = b.makePostCall(jsonBody, endPoint);
        return response;
    }

    public Response updateField(String fieldName, String fieldValue, String issueKey){
        String jsonBody = BasicAction.updateFieldString(fieldName, fieldValue);
        String endPoint = "/rest/api/2/issue"+ "/" + issueKey;
        BaseClass b = new BaseClass();
        Response response = b.makePutCall(jsonBody, endPoint);
        return response;
    }

    public Response updateField(String fieldName, String name, String fieldValue, String issueKey){
        String jsonBody = BasicAction.updateFieldString(fieldName, name, fieldValue);
        String endPoint = "/rest/api/2/issue"+ "/" + issueKey;
        BaseClass b = new BaseClass();
        Response response = b.makePutCall(jsonBody, endPoint);
        return response;
    }

    public Response getIssueResponse(String issueKey){
        String endPoint = Constant.issueEndPoint + "/" +issueKey;
        BaseClass b = new BaseClass();
        Response response = b.makeGetCall(endPoint);
        return response;
    }

    public Response transitionIssue(String issueKey, int transitionID ){
        String jsonBody = BasicAction.transitionString(transitionID);
        String endPoint = Constant.issueEndPoint + "/" + issueKey + "/transitions";
        BaseClass b = new BaseClass();
        Response response = b.makePostCall(jsonBody, endPoint);
        return response;
    }

    public Response linkIssues(String linktype, String inwardissue, String outwardissue ){
        String jsonBody = BasicAction.linkingIssue(linktype, inwardissue, outwardissue);
        String endPoint = Constant.issueLink;
        BaseClass b = new BaseClass();
        Response response = b.makePostCall(jsonBody, endPoint);
        return response;
    }

    /*public static void main (String arg[]){
        String l = BasicAction.linkingIssue("Blocks", "RT-1", "RT-2");
        System.out.println(l);
    }*/
}
