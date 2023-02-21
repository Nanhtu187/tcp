package com.facenet.mina.Entity;
/*
 * Created at 17/02/2023:10:07:18
 */

/**
 * @author hungdinh
 */

public class Login extends XMLEntity{

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
     * @return String XML for class
     */
    @Override
    public String toXML() {
        return "<login><username>"+username +"</username></login>";
    }
}
