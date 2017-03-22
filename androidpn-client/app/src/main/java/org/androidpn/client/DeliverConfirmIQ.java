package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

/**
 * Created by Xionghu at 2017/3/16 10:57
 * function:
 * version:
 */

public class DeliverConfirmIQ extends IQ{

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;


    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("deliverconfirm").append(" xmlns=\"").append(
                "androidpn:iq:deliverconfirm").append("\">");
        if (uuid != null) {
            buf.append("<uuid>").append(uuid).append("</uuid>");
        }
        buf.append("</").append("deliverconfirm").append("> ");
        return buf.toString();
    }
}
