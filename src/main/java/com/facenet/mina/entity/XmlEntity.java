package com.facenet.mina.entity;

import java.io.Serializable;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public abstract class XmlEntity implements Serializable {

    /**
     *
     * @return
     */
    public abstract String toXML();
}
