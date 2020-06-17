package com.raisin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.raisin.model.dto.AccountDTO;

/**
 * アカウントのDAOクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class AccountDAO extends BaseDAO {

	/**
	 * アカウントを取得する
	 *
	 * @param userid
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public AccountDTO selectAccount(String userid, String password) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("password", password);
		return (AccountDTO) super.queryForObject("selectAccount", map);
	}
}
