package com.facenet.mina.service.impl;

import com.facenet.mina.entity.Login;
import com.facenet.mina.entity.Message;
import com.facenet.mina.service.ClientService;
import org.apache.mina.core.session.IoSession;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */
public class ClientServiceImpl implements ClientService {

    private IoSession session;

    private String username;

    public ClientServiceImpl(IoSession session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Login Service
     * @param name
     */
    @Override
    public void login(String name) {
        Login login = new Login(name);
        setUsername(name);
        session.write(login);
    }

    /**
     * Send Message service
     * @param message
     */
    @Override
    public void sendMessage(String message) {
        Message msg = new Message(message, this.username);
        session.write(msg);
    }

    /**
     * Logout service
     */
    @Override
    public void logout() {
        session.closeOnFlush();
    }

    /**
     * Request all message service
     */
    @Override
    public void sendRequestGetRoom() {
        session.write(Message.GET_ROOM);
    }
}
