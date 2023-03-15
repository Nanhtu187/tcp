package com.facenet.mina.entity;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class Login extends XmlEntity {

    /**
     * XML Element
     */
    private String username;

    public Login(String username) {
        this.username = username;
    }

    /**
     * @return XML element content
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "<login><username>"+username +"</username></login>";
    }


    /**
     * String XML for element Login
     * @return
     */
    @Override
    public String toXML() {
        return "<login><username>"+ username +"</username></login>";
    }
}
