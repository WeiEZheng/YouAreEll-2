package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    HttpURLConnection connection;
    private String rootURL = "http://zipcode.rocks:8085";
    private static ServerController svr = new ServerController();
    private ServerController() {}

    public static ServerController shared() {
        return svr;
    }

    public JSONArray serverCall(String infoType, String command){
        BufferedReader reader = null;
        String line;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(rootURL+infoType);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(command);
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);
            int status = connection.getResponseCode();
            if (status >= 300){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine())!=null){
                    response.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine())!=null){
                    response.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONArray(response.toString());
    }

    public List<Id> idGet(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Id> ids = new ArrayList<>();
        try {
            ids = objectMapper.readValue(serverCall("/ids","GET").toString(), new TypeReference<List<Id>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(serverCall("/ids","GET").toString());
        return ids;
    }
//    public JsonString idPost(Id id) {
//        // url -> /ids/
//        // create json from Id
//        // request
//        // reply
//        // return json
//    }
//    public JsonString idPut(Id id) {
//        // url -> /ids/
//    }
}

// ServerController.shared.doGet()