package com.facenet.mina.entity;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class Message extends XmlEntity {

    private String content;

    private String username;

    private Boolean visible;

    /**
     *
     * @param content - text
     * @param username - sender
     */
    public Message(String content, String username) {
        this.content = content;
        this.username = username;
        this.visible = true;
    }

    /**
     *
     * @param content
     * @param username
     * @param visible
     */
    public Message(String content, String username, Boolean visible) {
        this.content = content;
        this.username = username;
        this.visible = visible;
    }

    /**
     *
     */
    public String getContent() {
        return content;
    }

    /**
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     *
     * @param message - content of message
     */
    public void setMessage(String message) {
        this.content = message;
    }

    /**
     *
     * @param username - name of sender
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * To String
     */
    @Override
    public String toString() {
        return username + " - " + content;
    }

    /**
     * Generate string Xml of element
     */
    @Override
    public String toXML() {
        return "<message><from>"+username+
                "</from><content>"+content+
                "</content></message>";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            Message messageObj = (Message) obj;
            return this.username.equals(messageObj.getUsername()) &&
                    this.content.equals(messageObj.getContent());
        }

        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static final Message MESSAGE_REJECT = new Message("Server reject", "Server", false);
    public static final Message LOGIN_SUCCESS = new Message("Login Success", "Server", false);
    public static final Message GET_ROOM = new Message("Get room", "Client", false);

}
