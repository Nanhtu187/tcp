package com.facenet.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class XmlCodecFactory implements ProtocolCodecFactory {

    private XmlEncoder encoder = new XmlEncoder();

    private XmlDecoder decoder = new XmlDecoder();

    /**
     *
     * @param ioSession
     * @return
     * @throws Exception
     */
    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return this.encoder;
    }

    /**
     *
     * @param ioSession
     * @return
     * @throws Exception
     */
    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return this.decoder;
    }
}
