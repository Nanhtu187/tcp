package com.facenet.mina.CustomFilterChain;
/*
 * Created at 17/02/2023:17:29:48
 */

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author hungdinh
 */

public class XmlCodecFactory implements ProtocolCodecFactory {

    private XmlEncoder encoder = new XmlEncoder();

    private XmlDecoder decoder = new XmlDecoder();
    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return this.encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return this.decoder;
    }
}
