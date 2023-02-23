package com.facenet.mina.client.handler;

import com.facenet.mina.service.ClientService;
import com.facenet.mina.service.impl.ClientServiceImpl;
import com.facenet.mina.entity.Message;
import com.facenet.mina.entity.Room;
import com.facenet.mina.gui.client.MainFrame;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.awt.*;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */
public class ClientSessionHandler extends IoHandlerAdapter {
    private IoSession serverWritingSession;


    private ClientService clientService;

    private MainFrame mainFrame;


    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (message instanceof Message) {
            Message messageData = (Message) message;
            mainFrame.msgReceived((Message) message);
        } else if (message instanceof Room) {
            ((Room) message).getMessageList().forEach(msg -> {
                mainFrame.msgReceived(msg);
            });
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
        this.clientService = new ClientServiceImpl(session);
        mainFrame = new MainFrame(clientService);
        mainFrame.setSize(400, 400);
        mainFrame.setVisible(true);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
        mainFrame.close();
    }

}
