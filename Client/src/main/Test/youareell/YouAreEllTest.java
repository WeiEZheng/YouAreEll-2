package youareell;

import controllers.IdController;
import controllers.MessageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class YouAreEllTest {
    YouAreEll url = new YouAreEll(new MessageController(), new IdController());

    @Test
    void postID() {
        String result = url.postID("TestWei", "TestWei");
        Assertions.assertEquals("Id registered.", result);
        String ids = url.get_ids();
        Assertions.assertTrue(ids.contains("TestWei (TestWei)"));
    }

    @Test
    void postMessage() {
        String result = url.postMessage("TestWei", "TestWei", "-");
        Assertions.assertEquals("Message sent.", result);
        String msgs = url.get_messages();;
        Assertions.assertTrue(msgs.contains("to: -\nfrom: TestWei\nTestWei\n"));
    }
}