package com.facenet.mina;
/*
 * Created at 15/02/2023:14:54:41
 */

import com.facenet.mina.Entity.Login;
import com.facenet.mina.Entity.Logout;
import com.facenet.mina.Entity.Message;
import org.apache.mina.core.session.IoSession;

/**
 * @author hungdinh
 */
public class ClientServiceImpl implements ClientService{

    private IoSession session;

    private String username;

    public static final String HEADER_XML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

    public ClientServiceImpl(IoSession session) {
        this.session = session;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void login(String name) {
        Login login = new Login(name);
        setUsername(name);
        session.write(login);
    }

    @Override
    public void sendMessage(String message) {
        Message msg = new Message(message, this.username);
        session.write(msg);
    }

    @Override
    public void logout() {
        Logout logout = new Logout();
        session.write(logout);
    }
}
