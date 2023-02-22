package com.facenet.mina.server.handler;

import com.facenet.mina.entity.*;
import com.facenet.mina.utils.XmlUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class ServerHandler extends IoHandlerAdapter {
    private final Map<Long, String> users = new ConcurrentHashMap<>();

    private final Room room = new Room();

    private final List<SocketAddress> blockedAddresses;
    private final List<IoSession> ioSessionList = new ArrayList<>();

    public ServerHandler() {
        Document blockedAddressesDoc =
                XmlUtils.readXmlFile(
                        "src/main/java/com/facenet/mina/xml_config/BlockedAddresses.xml");
        blockedAddresses = getBlockedAddresses(blockedAddressesDoc);

    }

    /**
     *
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
    }

    /**
     * return response for client
     * @param session
     * @param message
     */
    private void responseMessage(IoSession session, Message message) {
        session.write(message);
    }

    /**
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        if (message instanceof Login) {
            String username = ((Login) message).getUsername();
            if (!users.containsKey(session.getId())) {
                users.put(session.getId(), username);
                Message newMsg = new Message(username + " join chat room",
                        "Server");
                room.addNewMsg(newMsg);
                ioSessionList.add(session);
                ioSessionList.forEach(session1 -> {
                    if (!session1.equals(session)) {
                        responseMessage(session1, newMsg);
                    } else {
                        session1.write(room);
                    }
                });
            }
        } else if (message instanceof Message) {
            room.addNewMsg((Message) message);
            ioSessionList.forEach(session1 -> {
                responseMessage(session1, (Message) message);
            });
        } else if (message instanceof Logout) {
            String username = users.get(session.getId());
            Message newMsg =
                    new Message(username + " out chat room", "Server");
            room.addNewMsg(newMsg);
            ioSessionList.forEach(session1 -> {
                responseMessage(session1, newMsg);
            });
            session.closeOnFlush();
        } else {
            session.write(new Message("Something wrong", "Server"));
        }
    }


    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE " + session.getIdleCount(status));
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        blockedAddresses.forEach(socketAddress -> {
            if (((InetSocketAddress) socketAddress).getAddress().
                    equals(((InetSocketAddress) session.getRemoteAddress()).getAddress()) ) {
                    session.closeOnFlush();
            }
        });
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    private List<SocketAddress> getBlockedAddresses(Document blockedAddressesDoc) {
        NodeList blockedAddressNodes = blockedAddressesDoc.getElementsByTagName("ip");
        List<SocketAddress> blockedAddresses = new ArrayList<>();
        for (int i = 0; i < blockedAddressNodes.getLength(); ++i) {
            Node blockedAddressNode = blockedAddressNodes.item(i);
            blockedAddresses.add(new InetSocketAddress(blockedAddressNode.getTextContent(), 0));
        }

        return blockedAddresses;
    }
}
