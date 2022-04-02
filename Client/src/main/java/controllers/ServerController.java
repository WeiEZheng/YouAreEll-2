package controllers;

import com.fasterxml.jackson.core.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ServerController {
    HttpURLConnection connection;
    private String rootURL = "http://zipcode.rocks:8085";
    private static ServerController svr = new ServerController();
    private ServerController() {}

    public static ServerController shared() {
        return svr;
    }

    public serverCall(String infoType, String command){
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(rootURL+infoType);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(command);
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);
            int status = connection.getResponseCode();
            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public JsonString idGet() {
//        // url -> /ids/
//        // send the server a get with url
//        // return json from server
//    }
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