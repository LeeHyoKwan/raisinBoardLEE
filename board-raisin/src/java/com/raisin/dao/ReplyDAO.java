package com.raisin.dao;

import java.sql.SQLException;
import java.util.List;

import com.raisin.model.dto.ReplyDTO;

/**
 * 掲示板のDAOクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class ReplyDAO extends BaseDAO {

	/**
	 * 掲示板のコマンド情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<ReplyDTO> selectReply(ReplyDTO replyDto) throws SQLException {
		return (List<ReplyDTO>) super.queryForList("selectReply", replyDto);
	}

}
