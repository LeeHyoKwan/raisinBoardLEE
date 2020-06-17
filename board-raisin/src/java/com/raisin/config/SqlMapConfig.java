package com.raisin.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * SqlMapクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public abstract class SqlMapConfig {

	private static SqlMapClient client;

	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("SqlMapConfig.xml");

			client = SqlMapClientBuilder.buildSqlMapClient(reader);

		} catch (IOException e) {
			throw new RuntimeException("sqlMapperの処理途中でエラーが発生しました。", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("sqlMapperの処理途中でエラーが発生しました。", e);
				}
			}
		}

	}

	/**
	 * Client取得する。
	 *
	 * @return
	 */
	public static SqlMapClient getInstance() {
		return client;
	}
}
