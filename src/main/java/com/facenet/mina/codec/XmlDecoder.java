package com.facenet.mina.codec;

import com.facenet.mina.entity.*;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class XmlDecoder extends ProtocolDecoderAdapter {

    /**
     *
     * @param ioSession
     * @param ioBuffer
     * @param protocolDecoderOutput
     * @throws Exception
     */
    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer,
                       ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        while (ioBuffer.hasRemaining()) {
            byte[] dataBytes = new byte[ioBuffer.remaining()];
            ioBuffer.get(dataBytes);
            XmlEntity dataObject = SerializationUtils.deserialize(dataBytes);
            protocolDecoderOutput.write(dataObject);
        }

        //Old decode (Dùng với XML)
        /*
        String xmlString = ioBuffer.getString(Charset.defaultCharset().newDecoder());
        Document xmlDoc =  XmlUtils.loadXMLFromString(xmlString);
        xmlDoc.getDocumentElement().normalize();
        String rootElement = xmlDoc.getDocumentElement().getNodeName();
        Object out = new String("Failed");
        if (rootElement.equals("login")) {
            NodeList list = xmlDoc.getElementsByTagName("username");
            Node node = list.item(0);
            String username = node.getTextContent();
            if (!username.equals("")) {
                out = new Login(username);
            }
        } else if (rootElement.equals("message")) {
            Node userNode = xmlDoc.getElementsByTagName("from").item(0);
            Node contentNode = xmlDoc.getElementsByTagName("content").item(0);
            out = new Message(contentNode.getTextContent(), userNode.getTextContent());
        } else if (rootElement.equals("room")) {
            out = new Room();
            NodeList messageList = xmlDoc.getElementsByTagName("message");
            for (int i = 0; i < messageList.getLength(); i++) {
                Node messageNode = messageList.item(i);
                Node userNode = messageNode.getChildNodes().item(0);
                Node contentNode = messageNode.getChildNodes().item(1);
                Message message = new Message(contentNode.getTextContent(), userNode.getTextContent());
                ((Room) out).addNewMsg(message);
            }
        } else if (rootElement.equals("logout")) {
            out = new Logout();
        }
        protocolDecoderOutput.write(out);*/

    }


}
