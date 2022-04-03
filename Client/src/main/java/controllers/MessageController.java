package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MessageController {

    private HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        ServerController serverController = ServerController.shared();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> messages = new ArrayList<>();
        try {
            messages = objectMapper.readValue(serverController.MakeURLCall("/messages","GET").toString(), new TypeReference<List<Message>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (Message i : messages){
            messagesSeen.add(i);
        }
        return (ArrayList<Message>) messages;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}