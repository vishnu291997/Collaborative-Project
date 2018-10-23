package com.niit.collaborationbackend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notification_s190035")
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String blogPostTitle;
private String email;
private String approvalStatus; //[Approved or Rejected]
private String rejectionReason;
private boolean viewed;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBlogPostTitle() {
	return blogPostTitle;
}
public void setBlogPostTitle(String blogPostTitle) {
	this.blogPostTitle = blogPostTitle;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getApprovalStatus() {
	return approvalStatus;
}
public void setApprovalStatus(String approvalStatus) {
	this.approvalStatus = approvalStatus;
}
public String getRejectionReason() {
	return rejectionReason;
}
public void setRejectionReason(String rejectionReason) {
	this.rejectionReason = rejectionReason;
}
public boolean isViewed() {
	return viewed;
}
public void setViewed(boolean viewed) {
	this.viewed = viewed;
}
}
