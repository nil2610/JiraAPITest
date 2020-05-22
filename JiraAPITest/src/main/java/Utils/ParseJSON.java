package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class ParseJSON {
    public static Object value;

    public static String getFieldName(String inputJSON ,String field, String name){
        JSONObject inputJSONObject = new JSONObject(inputJSON);
        JSONObject fieldsJSON = inputJSONObject.getJSONObject("fields");
        if( !fieldsJSON.isNull(field)){
            JSONObject fieldJSON = fieldsJSON.getJSONObject(field);
            if(!fieldJSON.isNull(name)) {
                String fieldName = getKey(fieldJSON, name).toString();
                return fieldName;
            }
        }
        return null;
    }

    public static String getIssueKey(String inputJSON, String key){
        JSONObject inputJSONObject = new JSONObject(inputJSON);
        String issuekey = inputJSONObject.get(key).toString();
        return issuekey;
    }

    public static Object parseObject(JSONObject json, String key) {
        return json.get(key);
    }

    public static Object getKey(JSONObject json, String key) {
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        if ( exists == false ) {
                            getKey(json.getJSONObject(nextKeys), key);

                        } else if (json.get(nextKeys) instanceof JSONArray) {
                            JSONArray jsonarray = json.getJSONArray(nextKeys);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                String jsonArrayString = jsonarray.get(i).toString();
                                JSONObject innerJSON = new JSONObject(jsonArrayString);
                                if ( exists == false ) {
                                    getKey(innerJSON, key);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {
            value = parseObject(json, key);
        }
        return value;
    }
}