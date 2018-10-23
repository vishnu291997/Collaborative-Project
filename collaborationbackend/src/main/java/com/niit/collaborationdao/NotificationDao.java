package com.niit.collaborationdao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.Notification;

@Repository("notificationDao")
public interface NotificationDao {
	void addNotification(Notification notification);
	List<Notification> getNotificationNotViewed(String email);
	Notification  getNotification(int id);
	void  updateNotification(int id);

}
