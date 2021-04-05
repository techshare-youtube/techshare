package com.rugbyaholic.techshare.comms.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TopicCreationForm {
	
	@NotBlank
	@Size(max = 64)
	private String subject;
	
	@NotBlank
	@Size(max = 640)
	private String primaryPost;
	
	private String topicNo;
	
	private boolean error;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPrimaryPost() {
		return primaryPost;
	}

	public void setPrimaryPost(String primaryPost) {
		this.primaryPost = primaryPost;
	}

	public String getTopicNo() {
		return topicNo;
	}

	public void setTopicNo(String topicNo) {
		this.topicNo = topicNo;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}