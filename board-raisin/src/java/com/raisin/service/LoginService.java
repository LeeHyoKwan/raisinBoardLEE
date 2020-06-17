package com.raisin.service;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.raisin.constants.CommonContants;
import com.raisin.dao.AccountDAO;
import com.raisin.manager.MessageManager;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.vo.LoginVO;

/**
 * ログインのサービスクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class LoginService extends BaseService {

	private AccountDAO accountDAO;

	/** コンストラクタ */
	public LoginService() {
		if (accountDAO == null) {
			accountDAO = new AccountDAO();
		}
	}

	/**
	 * ログイン画面の単項目チェック
	 * @param loginVO
	 * @return
	 * @throws SQLException
	 */
	public String chkAccount(LoginVO loginVO) throws SQLException {
		String userid = loginVO.getUserid();
		String password = loginVO.getPassword();

		if (StringUtils.isEmpty(userid)) {
			return MessageManager.getMessage(CommonContants.MESSAGE_COMMON_INPUT_ISEMPTY, "Userid");
		}

		if (StringUtils.isEmpty(password)) {
			return MessageManager.getMessage(CommonContants.MESSAGE_COMMON_INPUT_ISEMPTY, "Password");
		}

		return null;

	}

	/**
	 * 入力からユーザーのアカウント情報を取得する
	 *
	 * @param loginVO
	 * @return
	 * @throws SQLException
	 */
	public AccountDTO getAccount(LoginVO loginVO) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return accountDAO.selectAccount(loginVO.getUserid(), loginVO.getPassword());
	}
}
