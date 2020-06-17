package com.raisin.service;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.raisin.config.SqlMapConfig;

/**
 * サービスの基底ラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public abstract class BaseService {

	private static SqlMapClient client = SqlMapConfig.getInstance();

	/**
	 * トランザクション開始
	 *
	 * @throws SQLException
	 */
	protected void startTransaction() throws SQLException {
		client.startTransaction();
	}

	/**
	 * トランザクションコミット
	 *
	 * @throws SQLException
	 */
	protected void commitTransaction() throws SQLException {
		client.commitTransaction();
	}

	/**
	 * トランザクション終了
	 *
	 * @throws SQLException
	 */
	protected void endTransaction() throws SQLException {
		client.endTransaction();
	}
}
