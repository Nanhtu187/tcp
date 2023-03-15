package com.facenet.mina.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class Room extends XmlEntity {

    /**
     * List store messages
     */
    private final List<Message> messageList;

    /**
     * Init Room
     */
    public Room() {
        messageList = new ArrayList<Message>();
    }

    /**
     * put new Message to Room
     * @param message
     */
    public void addNewMsg(Message message) {
        this.messageList.add(message);
    }

    /**
     * Get List Message of Room
     * @return
     */
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
