package com.facenet.mina.service;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public interface ClientService {

    public void login(String name);

    public void sendMessage(String message);

    public void logout();

    public void sendRequestGetRoom();



}
