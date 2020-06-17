package com.raisin.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.raisin.config.SqlMapConfig;

/**
 * DAOの基底クラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public abstract class BaseDAO {

	private static SqlMapClient client = SqlMapConfig.getInstance();

	/**
	 * Selectを実行する「リスト」
	 *
	 * @param id
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	protected List<?> queryForList(String id, Object object) throws SQLException {
		return client.queryForList(id, object);
	}

	/**
	 * Selectを実行する「単体」
	 *
	 * @param id
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	protected Object queryForObject(String id, Object object) throws SQLException {
		return client.queryForObject(id, object);
	}

	/**
	 * Insertを実行する
	 *
	 * @param id
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	protected Object insert(String id, Object object) throws SQLException {
		return client.insert(id, object);
	}

	/**
	 * Updateを実行する
	 *
	 * @param id
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	protected int update(String id, Object object) throws SQLException {
		return client.update(id, object);
	}

	/**
	 * Deleteを実行する
	 *
	 * @param id
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	protected int delte(String id, Object object) throws SQLException {
		return client.delete(id, object);
	}

}
