package com.ssm.common.exception;


import com.ssm.common.utils.MessageFormat;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 6021833540765034196L;
	
	private static final String BUNDLE_NAME = "service_exception";

	private String code;

	private String msg;

	private Object[] messageParams;
	
	public String getCode() {
		return code;
	}
	
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String code, String messagePattern, Object... messageParams) {
		super(messagePattern);
		this.code = this.getStackTrace()[0].getClassName() + "." + code;
		this.msg = messagePattern;
		this.messageParams = messageParams;
	}

	public ServiceException(Throwable cause, String code, String messagePattern, Object... messageParams) {
		super(messagePattern, cause);
		this.code = this.getStackTrace()[0].getClassName() + "." + code;
		this.msg = messagePattern;
		this.messageParams = messageParams;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append(":    code=");
		sb.append(code);
		sb.append("     ");
		if (messageParams == null) {
			sb.append(msg);
		} else {
			sb.append(getMessageByLocale(Locale.getDefault()));
		}
		
		return sb.toString();
	}

	public String getMessageByLocale(Locale locale) {
		return MessageFormat.format(locale, getString(locale, code, msg),messageParams);
	}

	private static String getString(Locale locale, String key, String defaultValue) {
		if ("en".equals(locale.getLanguage())) {
			return defaultValue;
		}
		try {
			return ResourceBundle.getBundle(BUNDLE_NAME, locale).getString(key);
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}

}
