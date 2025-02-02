package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashSet;

public class MessageController {

    private HashSet<Message> messagesSeen = new HashSet<>();
    private ServerController serverController = ServerController.shared();

    public MessageController(){}

    public void update(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            messagesSeen = objectMapper.readValue(serverController.MakeURLCall("/messages","GET").toString(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Message> getMessages() {
        update();
        ArrayList<Message> messages = new ArrayList<>();
        for (Message i : messagesSeen){
            messages.add(i);
        }
        return messages;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        update();
        ArrayList <Message> messages = new ArrayList<>();
        for (Message m :messagesSeen){
            if (m.getFromId().equals(Id.getGithub()))
                messages.add(m);
        }
        return messages;
    }

    public Message getMessageForSequence(String seq) {
        update();
        for (Message m :messagesSeen){
            if (m.getSeqId().equals(seq))
                return m;
        }
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        update();
        ArrayList <Message> messages = new ArrayList<>();
        for (Message m :messagesSeen){
            if (m.getFromId().equals(friendId.getGithub()) && m.getToId().equals(myId.getGithub()))
                messages.add(m);
        }
        return messages;
    }

    public Message postMessage(Message msg, String githubId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = serverController.MakeURLCall("/ids/"+githubId+"/messages", "POST", jsonString);
        if (jsonArray.toString().contains("[{\"error\":")) {
            msg = null;
            System.out.println(jsonArray);
        }
        else {
            try {
                msg = objectMapper.readValue(jsonArray.getJSONObject(0).toString(), Message.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}