package com.raisin.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raisin.constants.CommonContants;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
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
	protected AccountDTO getSessionUser() {
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
	protected AccountDTO getSessionUser2() {
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
	 * ユーザー判断
	 * @param account
	 */
	protected String isAuthority(String userid) {
		AccountDTO account = getSessionUser();
		if (!account.getUserid().equals(userid)) {
			return INPUT;
		}
		return SUCCESS;
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
		PagingAction page = new PagingAction();
		page.PagingAction1(pagingVO.getCurrentPage(), totalCount, pagingVO.getBlockCount(), pagingVO.getBlockPage());
        // ページ html生成
        pagingVO.setPagingHtml(page.getPagingHtml().toString());
	}

	/**
	 * ページング処理
	 * @param account
	 */
	protected List<CommentDTO> setPagingCmt(List<CommentDTO> list, PagingVO pagingVO, String boardid) {
		int totalCount = list.size();// 全掲示物数
		// pagingAction オブジェクト生成7
		PagingAction page = new PagingAction();
		int currentPage = pagingVO.getCurrentPageCmt();
		if (currentPage == 0) {
			currentPage = totalCount;
		}
		page.cmtPagingAction(currentPage, totalCount, pagingVO.getBlockCount(), pagingVO.getBlockPage(), boardid);

        // ページ html生成
        pagingVO.setPagingHtmlCmt(page.getPagingHtml().toString());
        // 現在ページで表示する最後番号設定
        int lastCount = totalCount;
        // 現在ページの最後の番号が全体の番号より小さい場合はlastCountを+1に設定
        if(page.getEndCount() < totalCount) {
        	lastCount = page.getEndCount() + 1;
        }
        // 全リストから現在ページのリストを設定
        return list = list.subList(page.getStartCount(), lastCount);
	}

}
