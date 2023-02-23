package com.facenet.mina.codec;

import com.facenet.mina.entity.XmlEntity;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public class XmlEncoder extends ProtocolEncoderAdapter {

    /**
     * Encode message
     * @param ioSession
     * @param o - object from session write
     * @param protocolEncoderOutput
     * @throws Exception
     */
    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

        XmlEntity xmlEntity = (XmlEntity) o;
        byte[] data = SerializationUtils.serialize(xmlEntity);
        IoBuffer ioBuffer = IoBuffer.allocate(data.length).setAutoExpand(true);
        ioBuffer.put(data);
        ioBuffer.flip();
        protocolEncoderOutput.write(ioBuffer);

        //Old encode (để lại khi nào xài :))
//        ioBuffer = IoBuffer.allocate(xmlEntity.toXML().length()).setAutoExpand(true);
//        ioBuffer.putString(xmlEntity.toXML(), Charset.defaultCharset().newEncoder());
//        ioBuffer.flip();
//        protocolEncoderOutput.write(ioBuffer);
//        System.out.println("From encode");
        //Old encode


    }
}
