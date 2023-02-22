package com.facenet.mina.Entity;

import java.io.Serializable;

/**
 * @author: hungdinh
 * Date created: 20/02/2023
 */

public abstract class XMLEntity implements Serializable {

    /**
     *
     * @return
     */
    public abstract String toXML();
}
