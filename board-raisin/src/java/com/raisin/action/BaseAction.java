package com.raisin.action;

import java.util.List;
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
	 * ページング処理
	 * @param account
	 */
	protected List<BoardDTO> setPaging(List<BoardDTO> list, PagingVO pagingVO) {
		int totalCount = list.size(); // 全掲示物数
		// pagingAction オブジェクト生成7
		PagingAction page = new PagingAction(
        		pagingVO.getCurrentPage(), totalCount, pagingVO.getBlockCount(), pagingVO.getBlockPage());
        // ページ html生成
        pagingVO.setPagingHtml(page.getPagingHtml().toString());
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
