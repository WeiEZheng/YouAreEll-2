package models;

/* 
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
public class Message implements Comparable {

    private String message = "";
    private String toId = "";
    private String fromId = "";
    private String timestamp = "";
    private String seqId = "";

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Message (String message, String fromId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = "";
    }

    public Message(){}

    @Override
    public String toString() {
        return "to: " + this.toId + "\nfrom: "+ this.fromId + "\n" + this.message + "\n----\n";
    }

    public int compareTo(Object o) {
        return this.seqId.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toId;
    }

    public void setToid(String toid) {
        this.toId = toid;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromid(String fromId) {
        this.fromId = fromId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp){this.timestamp=timestamp;}

    public String getSeqId() {
        return seqId;
    }

    public void setSequence(String seqId) {this.seqId=seqId;}
}