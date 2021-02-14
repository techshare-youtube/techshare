package com.rugbyaholic.techshare.common.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class NotificationMessage {
	
	@Autowired
	private MessageSource messageSource;
	
	private int messageLevel;
	
	private String messageCode;
	
	private Object[] args;
	
	public boolean isInfo() { return messageLevel == MESSAGE_LEVEL_INFO; }
	
	public boolean isSuccess() { return messageLevel == MESSAGE_LEVEL_SUCCESS; }
	
	public boolean isWarning() { return messageLevel == MESSAGE_LEVEL_WARNING; }
	
	public boolean isError() { return messageLevel == MESSAGE_LEVEL_ERROR; }
	
	public NotificationMessageBuilder builder() {
		return new NotificationMessageBuilder(this);
	}
	
	public String getMessage() {
		return messageSource.getMessage(messageCode, args, Locale.getDefault());
	}
	
	/**
	 * メッセージレベル：インフォメーション
	 */
	public static final int MESSAGE_LEVEL_INFO = 0;
	
	/**
	 * メッセージレベル：処理成功
	 */
	public static final int MESSAGE_LEVEL_SUCCESS = 1;
	
	/**
	 * メッセージレベル：警告終了
	 */
	public static final int MESSAGE_LEVEL_WARNING = 2;
	
	/**
	 * メッセージレベル：異常終了
	 */
	public static final int MESSAGE_LEVEL_ERROR = 3;
	
	public class NotificationMessageBuilder {
		
		private NotificationMessage notificationMessage;
		
		private NotificationMessageBuilder(NotificationMessage notificationMessage) {
			this.notificationMessage = notificationMessage;
		}
		
		public NotificationMessageBuilder messageLevel(int messageLevel) {
			this.notificationMessage.messageLevel = messageLevel;
			return this;
		}
		
		public NotificationMessageBuilder messageCode(String messageCode) {
			this.notificationMessage.messageCode = messageCode;
			return this;
		}
		
		public NotificationMessageBuilder messageArguments(Object... args) {
			this.notificationMessage.args = args;
			return this;
		}
		
		public NotificationMessage build() {
			return this.notificationMessage;
		}
	}
}