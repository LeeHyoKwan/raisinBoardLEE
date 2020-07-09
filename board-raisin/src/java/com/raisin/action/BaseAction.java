package com.raisin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raisin.constants.CommonContants;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.vo.PagingVO;

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
	}

	/**
	 * セッションにユーザー情報を設定する
	 * @param account
	 */
	protected void removeSession() {
		session.remove(CommonContants.SESSION_USER);
	}

	/**
	 * ページング用LIMIT設定
	 * @param account
	 */
	protected void setLimitStart(PagingVO pagingVO, BoardDTO boardDto) {
		int currentPage = pagingVO.getCurrentPage();
		if (currentPage == 1) {
			boardDto.setLimitStart(0);
		} else {
			boardDto.setLimitStart(currentPage * 10 - 10);
		}

	}

	/**
	 * ページング処理
	 * @param account
	 */
	protected void setPaging(BoardDTO boardDto, PagingVO pagingVO) {
		int totalCount = boardDto.getRowCount();// 全掲示物数
		// pagingAction オブジェクト生成7
		PagingAction page = new PagingAction(
        		pagingVO.getCurrentPage(), totalCount, pagingVO.getBlockCount(), pagingVO.getBlockPage());
        // ページ html生成
        pagingVO.setPagingHtml(page.getPagingHtml().toString());
	}

}
