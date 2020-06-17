package com.raisin.manager;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * メッセージを管理するUTILクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class MessageManager {

	private static ResourceBundle rb;

	static {
		rb = ResourceBundle.getBundle("message");
	}

	/**
	 * message.propertiesから文言を取得する
	 *
	 * @param key
	 * @param arg
	 * @return
	 */
	public static String getMessage(String key, Object... arg) {
		String pattern = rb.getString(key);
		if (pattern == null) {
			return null;
		}
		return MessageFormat.format(pattern, arg);

	}

}
