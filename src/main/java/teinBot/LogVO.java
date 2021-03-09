package teinBot;

public class LogVO {

    private String author;
    private String name;
    private String channel;
    private String channelid;
    private String message;
    private String changed;
    private String deleteMsg;
    private String messageId;

    public LogVO(){}

    public LogVO(String author, String name, String channel, String channelid, String message, String changed, String deleteMsg, String messageId) {
        this.author = author;
        this.name = name;
        this.channel = channel;
        this.channelid = channelid;
        this.message = message;
        this.changed = changed;
        this.deleteMsg = deleteMsg;
        this.messageId = messageId;
    }

    public LogVO(String author, String name, String channel, String channelid, String messageId, String message) {
        this.author = author;
        this.name = name;
        this.channel = channel;
        this.channelid = channelid;
        this.messageId = messageId;
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogVO{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                ", chaneelid='" + channelid + '\'' +
                ", messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", changed='" + changed + '\'' +
                ", deleteMsg='" + deleteMsg + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getDeleteMsg() {
        return deleteMsg;
    }

    public void setDeleteMsg(String delete) {
        this.deleteMsg = delete;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}//class
