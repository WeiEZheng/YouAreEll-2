package youareell;

import controllers.*;
import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;

import java.util.List;

public class YouAreEll {

    static TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll(MessageController messageController, IdController idController) {
        tt = new TransactionController(messageController, idController);
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
//        tt.postId("Wei Zheng", "WeiEZheng");
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        System.out.println(tt.postMessage("test1", "WeiEZheng",""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        List<Id> ids = tt.getIds();
        String display = "";
        for (Id i:ids) {
            IdTextView idView = new IdTextView(i);
            display+= idView +"\n";
        }
        return display;
    }

    public String get_messages() {
        List<Message> messages = tt.getMessages();
        String display = "";
        for (Message i:messages) {
            MessageTextView messageTextView = new MessageTextView();
            display+= messageTextView.toString(i) +"\n";
        }
        return display;
    }

    public String postID(String name, String githubID){
        return tt.postId(name, githubID);
    }

    public String postMessage(String message, String fromID, String toID){
        return tt.postMessage(message,fromID,toID);
    }
}
