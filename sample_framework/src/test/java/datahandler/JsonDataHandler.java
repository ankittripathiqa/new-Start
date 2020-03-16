package datahandler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonDataHandler {

    public static JSONObject getJsonObject(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "//src/test/resources/configs/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getObject(JSONObject jsonObject, String userType, String key) {
        return ((JSONObject) jsonObject.get(userType)).get(key).toString();
    }
}
