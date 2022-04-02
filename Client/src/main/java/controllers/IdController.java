package controllers;

import models.Id;

import java.util.ArrayList;
import java.util.HashMap;

public class IdController {
    private HashMap<String, Id> allIds;

    Id myId;

    public ArrayList<Id> getIds() {
        ServerController serverController = ServerController.shared();
        return (ArrayList<Id>) serverController.idGet();
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