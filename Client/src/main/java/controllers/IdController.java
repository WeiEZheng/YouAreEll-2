package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class IdController {
    private HashMap<String, Id> allIds = new HashMap<>();
    private ServerController serverController = ServerController.shared();
    Id myId;

    public IdController(){
        this.update();
    }

    public void update(){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> ids = new ArrayList<>();
        try {
            ids = objectMapper.readValue(serverController.MakeURLCall("/ids","GET").toString(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (Id id: ids){
            allIds.put(id.getName(),id);
        }
    }

    public ArrayList<Id> getIds() {
        ArrayList<Id> ids = new ArrayList<>(allIds.values());
        return ids;
    }

    public Id postId(Id id) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = serverController.MakeURLCall("/ids", "POST", jsonString);
        try {
            id =objectMapper.readValue(jsonArray.getJSONObject(0).toString(), Id.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Id putId(Id id) {
        return null;
    }
}