package com.facenet.mina.entity;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class Logout extends XmlEntity {
    @Override
    public String toXML() {
        return "<logout></logout>";
    }
}
