package com.facenet.mina.Entity;
/*
 * Created at 21/02/2023:09:07:48
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author hungdinh
 */

public class Room extends XMLEntity{

    /**
     * List store messages
     */
    private List<Message> messageList;

    public Room() {
        messageList = new ArrayList<Message>();
    }


    public void addNewMsg(Message message) {
        this.messageList.add(message);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public String toXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("<room>");
        messageList.forEach(message -> {
            xml.append(message.toXML());
        });
        xml.append("</room>");
        return xml.toString();
    }
}
