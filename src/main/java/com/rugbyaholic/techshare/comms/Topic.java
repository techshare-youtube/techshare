package com.rugbyaholic.techshare.comms;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

public class Topic {
	
	private String topicNo;
	
	private String subject;
	
	private AuthenticatedUser owner;
	
	private Date createdAt;
	
	private List<Post> posts;
	
	public boolean isOwnedBy(AuthenticatedUser user) {
		return Objects.equals(user.getEmpNo(), owner.getEmpNo());
	}

	public String getTopicNo() {
		return topicNo;
	}

	public void setTopicNo(String topicNo) {
		this.topicNo = topicNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public AuthenticatedUser getOwner() {
		return owner;
	}

	public void setOwner(AuthenticatedUser owner) {
		this.owner = owner;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}