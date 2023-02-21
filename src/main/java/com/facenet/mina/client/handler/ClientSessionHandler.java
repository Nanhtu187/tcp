package com.facenet.mina.client.handler;

import com.facenet.mina.ClientService;
import com.facenet.mina.ClientServiceImpl;
import com.facenet.mina.Entity.Message;
import com.facenet.mina.Entity.Room;
import com.facenet.mina.GUI.client.MainFrame;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

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
            mainFrame.msgReceive((Message) message);
        } else if (message instanceof Room) {
            ((Room) message).getMessageList().forEach(msg -> {
                mainFrame.msgReceive(msg);
            });
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    public void setServerWritingSession(IoSession serverWritingSession) {
        this.serverWritingSession = serverWritingSession;
        this.clientService = new ClientServiceImpl(serverWritingSession);

        mainFrame = new MainFrame(clientService);
        mainFrame.setSize(400, 400);
        mainFrame.setVisible(true);
    }
}
