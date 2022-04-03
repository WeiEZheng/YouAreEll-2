package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.HashSet;

public class MessageController {

    private HashSet<Message> messagesSeen = new HashSet<>();
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        ServerController serverController = ServerController.shared();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Message> messages = new ArrayList<>();
        try {
            messages = objectMapper.readValue(serverController.MakeURLCall("/messages","GET").toString(), new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (Message i : messages){
            messagesSeen.add(i);
        }
        return messages;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        ArrayList <Message> messages = new ArrayList<>();
        for (Message m :messagesSeen){
            if (m.getFromId().equals(Id.getGithub()))
                messages.add(m);
        }
        return messages;
    }

    public Message getMessageForSequence(String seq) {
        for (Message m :messagesSeen){
            if (m.getSeqId().equals(seq))
                return m;
        }
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        ArrayList <Message> messages = new ArrayList<>();
        for (Message m :messagesSeen){
            if (m.getFromId().equals(friendId.getGithub()) && m.getToId().equals(myId.getGithub()))
                messages.add(m);
        }
        return messages;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
}