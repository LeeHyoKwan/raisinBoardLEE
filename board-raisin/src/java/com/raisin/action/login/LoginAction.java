package com.raisin.action.login;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.constants.CommonContants;
import com.raisin.manager.MessageManager;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.vo.LoginVO;
import com.raisin.service.LoginService;

/**
 * ログインのアクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class LoginAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(LoginAction.class);

	private LoginVO loginVO;
	private LoginService service;

	/** コンストラクタ */
	public LoginAction() {
		if (service == null) {
			service = new LoginService();
		}
	}

	@Override
	public String execute() throws Exception {
		super.setSessionUser(null);
		return SUCCESS;
	}

	/**
	 * ログインボタンのクリックイベント
	 *
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {

		logger.info("----------------start {}.{}----------------", "loginAction", "execute");

		try {
			String errMessage = service.chkAccount(loginVO);
			if (StringUtils.isNotEmpty(errMessage)) {
				loginVO.setErrMessage(errMessage);
				return INPUT;
			}

			AccountDTO account = service.getAccount(loginVO);
			if (account == null) {
				String errMsg = MessageManager.getMessage(CommonContants.MESSAGE_LOGIN_ACCOUNT_UNKNOWN);
				loginVO.setErrMessage(errMsg);
				return INPUT;
			}

			super.setSessionUser(account);

			return SUCCESS;
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "loginAction", "execute");
		}
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

}
