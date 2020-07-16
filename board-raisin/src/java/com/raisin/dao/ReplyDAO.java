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

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public ReplyDTO selectReplyObj(ReplyDTO replyDto) throws SQLException {
		return (ReplyDTO)super.queryForObject("selectReplyObject", replyDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertReply(ReplyDTO replyDto) throws SQLException {
		super.insert("insertReply", replyDto);
	}


	/**
	 * 掲示板のコメント情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteReply(ReplyDTO replyDto) throws SQLException {
		super.delete("deleteReply", replyDto);
	}

	/**
	 * 掲示板のコメント情報を更新する
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateReply(ReplyDTO replyDto) throws SQLException {
		super.update("updateReply", replyDto);
	}
}
