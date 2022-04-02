package controllers;

import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        idCtrl=j;
        msgCtrl=m;
    }

    public List<Id> getIds() {
        List<Id> ids = idCtrl.getIds();
        return ids;
    }

//    public String postId(String idtoRegister, String githubName) {
//        Id tid = new Id(idtoRegister, githubName);
//        tid = idCtrl.postId(tid);
//        return ("Id registered.");
//    }
}
