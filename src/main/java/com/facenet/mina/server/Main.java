package com.facenet.mina.server;

import com.facenet.mina.server.handler.ServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import com.facenet.mina.CustomFilterChain.XmlCodecFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    /**
     * Default port
     */
    private static final int PORT = 8081;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger", new LoggingFilter("TCPServer"));
//        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
//                new TextLineCodecFactory(Charset.forName("UTF-8"), "\n", "\n")
//        ));

        acceptor.getFilterChain().addLast("xml", new ProtocolCodecFilter(
                new XmlCodecFactory()
        ));

        acceptor.setHandler(new ServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 20);
        acceptor.bind(new InetSocketAddress(PORT));
    }


}