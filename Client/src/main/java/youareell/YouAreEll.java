package youareell;

import controllers.*;
import models.Id;
import views.IdTextView;

import java.util.List;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String MakeURLCall(String infoType, String command, String filter) {
        return "";
    }

    public String get_ids() {
        List<Id> ids = tt.getIds();
        String display = "";
        for (Id i:ids) {
            IdTextView idView = new IdTextView(i);
            display+= idView.toString();
        }
        return display;
//        return tt.makecall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }
}
