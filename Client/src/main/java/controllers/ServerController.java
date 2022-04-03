package controllers;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServerController {
    HttpURLConnection connection;
    private String rootURL = "http://zipcode.rocks:8085";
    private static ServerController svr = new ServerController();
    private ServerController() {}

    public static ServerController shared() {
        return svr;
    }

    public JSONArray MakeURLCall(String infoType, String command){
        return  MakeURLCall(infoType,command, null);
    }
    public JSONArray MakeURLCall(String infoType, String command, String jsonString){
        BufferedReader reader;
        String line;
        StringBuilder response = new StringBuilder();
        int status = 0;
        try {
            URL url = new URL(rootURL+infoType);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(command);
            if (command.equals("GET")) {
                connection.setConnectTimeout(500);
                connection.setReadTimeout(500);
                status = connection.getResponseCode();
            } else if (command.equals("POST")){
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
                status = connection.getResponseCode();
            }
            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();
        String result = response.toString();
        if (result.charAt(0)!='[')
            result = "["+result+"]";
        return new JSONArray(result);
    }
}