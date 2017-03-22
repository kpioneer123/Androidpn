package org.androidpn.server.xmpp.handler;

import org.androidpn.server.service.NotificationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;


public class IQdeliverConfirmHandler extends IQHandler {

	//和客戶端保持一致
	private static final String NAMESPACE = "androidpn:iq:deliverconfirm";
	
	private NotificationService notificationService;
	public IQdeliverConfirmHandler(){
		notificationService = ServiceLocator.getNotificationService();
	}
	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		IQ reply = null;
		
		ClientSession session = sessionManager.getSession(packet.getFrom());
        if (session == null) {
            log.error("Session not found for key " + packet.getFrom());
            reply = IQ.createResultIQ(packet);
            reply.setChildElement(packet.getChildElement().createCopy());
            reply.setError(PacketError.Condition.internal_server_error);
            return reply;
        }
        if(session.getStatus()==Session.STATUS_AUTHENTICATED){//保证客户端用账号密码登录，避免伪造请求发生
        	if(IQ.Type.set.equals(packet.getType())){
        		Element element = packet.getChildElement();
        		String uuid = element.elementText("uuid");
        		notificationService.deleteNotification(uuid);
        	}
        }
		return null;
	}

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

}
