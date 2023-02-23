package com.facenet.mina.server.handler;

import com.facenet.mina.entity.*;
import com.facenet.mina.utils.XmlUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.net.InetAddress;
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

    /**
     * Init Server Handler
     */
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
    public void messageReceived(IoSession session, Object message)
            throws Exception {

        if (message instanceof Login) {
            String username = ((Login) message).getUsername();
            if (!users.containsKey(session.getId())) {
                if (users.containsValue(username)) {
                    responseMessage(session, Message.MESSAGE_REJECT);
                    return;
                } else {
                    users.put(session.getId(), username);
                    responseMessage(session, Message.LOGIN_SUCCESS);
                }
                Message newMsg = new Message(username + " join chat room",
                        "Server");
                room.addNewMsg(newMsg);
                ioSessionList.add(session);
                ioSessionList.forEach(session1 -> {
                    if (!session1.equals(session)) {
                        responseMessage(session1, newMsg);
                    }
                });
            }
        } else if (message instanceof Message) {
            if (((Message) message).equals(Message.GET_ROOM)) {
                session.write(room);
            }
            room.addNewMsg((Message) message);
            ioSessionList.forEach(session1 -> {
                responseMessage(session1, (Message) message);
            });
        } else {
            session.write(new Message("Something wrong", "Server"));
        }
    }


    /**
     * Keep - Alive
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        Message keepAlive = new Message("Keep - Alive", "Server", false);
        session.write(keepAlive);
    }

    /**
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        blockedAddresses.forEach(socketAddress -> {
            InetAddress blockedAddress =
                    ((InetSocketAddress) socketAddress).getAddress();
            InetAddress clientAddress =
                    ((InetSocketAddress) session.getRemoteAddress()).getAddress();
            if (blockedAddress != null && blockedAddress.equals(clientAddress)) {
                    session.closeOnFlush();
            }
        });
    }

    /**
     *
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        //DO Something when session Open
    }

    /**
     *
     * @param blockedAddressesDoc - Document from xml file
     * @return List of Ip is blocked
     */
    private List<SocketAddress> getBlockedAddresses(Document blockedAddressesDoc) {
        NodeList blockedAddressNodes = blockedAddressesDoc.getElementsByTagName("ip");
        List<SocketAddress> blockedAddresses = new ArrayList<>();
        for (int i = 0; i < blockedAddressNodes.getLength(); ++i) {
            Node blockedAddressNode = blockedAddressNodes.item(i);
            blockedAddresses.add(new InetSocketAddress(blockedAddressNode.getTextContent(), 0));
        }
        return blockedAddresses;
    }

    /**
     * Call when session close
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        String username = users.get(session.getId());
        Message newMsg =
                new Message(username + " out chat room", "Server");
        room.addNewMsg(newMsg);
        ioSessionList.remove(session);
        ioSessionList.forEach(session1 -> {
            System.out.println(session1.getId());
            responseMessage(session1, newMsg);
        });
        users.remove(session.getId());
        session.closeOnFlush();
    }
}
