package com.facenet.mina.CustomFilterChain;
/*
 * Created at 17/02/2023:17:31:40
 */

import com.facenet.mina.Entity.XMLEntity;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @author hungdinh
 */

public class XmlEncoder extends ProtocolEncoderAdapter {
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

        XMLEntity xmlEntity = (XMLEntity) o;

        IoBuffer buf = IoBuffer.allocate(xmlEntity.toXML().length()).setAutoExpand(true);
        buf.putString(xmlEntity.toXML(), Charset.defaultCharset().newEncoder());
        buf.flip();
        protocolEncoderOutput.write(buf);

    }
}
