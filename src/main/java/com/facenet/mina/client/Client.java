package com.facenet.mina.client;

import com.facenet.mina.client.handler.ClientSessionHandler;
import com.facenet.mina.codec.XmlCodecFactory;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import java.net.InetSocketAddress;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */
public class Client {

    private static final int PORT = 8081;
    private static final long CONNECT_TIMEOUT = 30*1000L;



    public static void main(String[] args) throws InterruptedException {
        ClientSessionHandler clientSessionHandler = new ClientSessionHandler();
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getFilterChain().addLast("logger", new LoggingFilter("TCPClient"));
        connector.getFilterChain().addLast("xml",
                new ProtocolCodecFilter( new XmlCodecFactory()));
        connector.setHandler(clientSessionHandler);
        IoSession session;
        for (;;) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", PORT));
                connector.getSessionConfig().setReuseAddress(true);
                future.awaitUninterruptibly();
                session = future.getSession();
                break;
            } catch (RuntimeIoException ex) {
                System.err.println("Failed to connect.");
                ex.printStackTrace();
                Thread.sleep(5000);
            }
        }

    }



}
