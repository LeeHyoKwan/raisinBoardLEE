package com.raisin.action;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raisin.constants.CommonContants;
import com.raisin.model.dto.AccountDTO;

/**
 * アクションの基底クラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {

	Map<String, Object> session;
	ActionContext context = ActionContext.getContext();

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public abstract String execute() throws Exception;

	/**
	 * セッションからユーザー情報を取得する
	 * @return
	 */
	protected AccountDTO getSessionUser2() {
		Object account = session.get(CommonContants.SESSION_USER);
		if (account == null) {
			return null;
		}
		return (AccountDTO) account;
	}

	/**
	 * セッションからユーザー情報を取得する
	 * @return
	 */
	protected AccountDTO getSessionUser() {
		Object account = context.getSession().get(CommonContants.SESSION_USER);
		if (account == null) {
			return null;
		}
		return (AccountDTO) account;
	}

	/**
	 * セッションにユーザー情報を設定する
	 * @param account
	 */
	protected void setSessionUser(AccountDTO account) {
		session.put(CommonContants.SESSION_USER, account);
		context.setSession(session);
	}

	protected String getSysDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy/MM/dd HH:mm:ss");
		String format_time1 = format1.format (System.currentTimeMillis());
		return format_time1;
	}

}
