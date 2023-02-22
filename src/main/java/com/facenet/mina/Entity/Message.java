package com.facenet.mina.Entity;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class Message extends XMLEntity{

    private String content;
    private String username;

    public Message(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public void setMessage(String message) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + " - " + content;
    }

    @Override
    public String toXML() {
        return "<message><from>"+username+"</from><content>"+content+"</content></message>";
    }
}
