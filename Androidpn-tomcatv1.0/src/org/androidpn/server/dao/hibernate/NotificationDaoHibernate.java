package org.androidpn.server.dao.hibernate;

import java.util.List;

import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NotificationDaoHibernate extends HibernateDaoSupport implements NotificationDao{

	public void saveNotification(Notification notification) {

		getHibernateTemplate().saveOrUpdate(notification);
		getHibernateTemplate().flush();
		
	}

    //一个用户名有多个消息
	public List<Notification> findNotificationsByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<Notification> list = getHibernateTemplate().find("from Notification where username=?",username);
		
		if(list!=null&&list.size()>0){
			return list;
		}
		
		return null;
	}
	public void deleteNotification(Notification notification) {
       //会自动将类中的数据删除
		getHibernateTemplate().delete(notification);
	}

	public void deleteNotificationByUuid(String uuid) {
		@SuppressWarnings("unchecked")
		List<Notification> list = getHibernateTemplate().find("from Notification where uuid=?",uuid);
		if(list!=null&&list.size()>0){
		//	getHibernateTemplate().delete(list.get(0));
			deleteNotification(list.get(0));
		}		
	}



}
