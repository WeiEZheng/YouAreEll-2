package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

import java.util.ArrayList;
import java.util.HashMap;

public class IdController {
    private HashMap<String, Id> allIds = new HashMap<>();

    Id myId;

    public ArrayList<Id> getIds() {
        ServerController serverController = ServerController.shared();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> ids = new ArrayList<>();
        try {
            ids = objectMapper.readValue(serverController.MakeURLCall("/ids","GET").toString(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (Id i : ids){
            allIds.put(i.getUid(),i);
        }
        return ids;
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
}