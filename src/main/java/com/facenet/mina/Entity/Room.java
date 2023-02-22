package com.facenet.mina.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class Room extends XMLEntity{

    /**
     * List store messages
     */
    private final List<Message> messageList;

    public Room() {
        messageList = new ArrayList<Message>();
    }


    public void addNewMsg(Message message) {
        this.messageList.add(message);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     *
     * @return
     */
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
