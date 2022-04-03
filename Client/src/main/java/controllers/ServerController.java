package controllers;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerController {
    HttpURLConnection connection;
    private String rootURL = "http://zipcode.rocks:8085";
    private static ServerController svr = new ServerController();
    private ServerController() {}

    public static ServerController shared() {
        return svr;
    }

    public JSONArray MakeURLCall(String infoType, String command){
        BufferedReader reader;
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

//    public List<Id> idGet(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Id> ids = new ArrayList<>();
//        try {
//            ids = objectMapper.readValue(MakeURLCall("/ids","GET").toString(), new TypeReference<List<Id>>(){});
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(MakeURLCall("/ids","GET").toString());
//        return ids;
//    } moved to id controller
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