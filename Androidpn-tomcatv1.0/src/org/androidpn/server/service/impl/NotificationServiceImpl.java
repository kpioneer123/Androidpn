package org.androidpn.server.service.impl;

import java.util.List;

import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.androidpn.server.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
	
	private NotificationDao  notificationDao;
	

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
	//引入数据库访问层对象
	public void saveNotification(Notification notification) {
		this.notificationDao.saveNotification(notification);
	}

	public List<Notification> findNotificationsByUsername(String username) {
		
		return notificationDao.findNotificationsByUsername(username);
	}

	public void deleteNotification(Notification notification) {
		this.notificationDao.deleteNotification(notification);
	}

	public void deleteNotification(String uuid) {
		this.notificationDao.deleteNotificationByUuid(uuid);
	}

}
