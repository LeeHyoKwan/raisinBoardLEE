package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import com.raisin.dao.ReplyDAO;
import com.raisin.model.dto.ReplyDTO;

/**
 * 掲示板のサービスクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class ReplyService extends BaseService {

	private ReplyDAO replyDAO;

	/** コンストラクタ */
	public ReplyService() {
		if (replyDAO == null) {
			replyDAO = new ReplyDAO();
		}
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<ReplyDTO> getReply(ReplyDTO replyDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return replyDAO.selectReply(replyDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public ReplyDTO getReplyObj(ReplyDTO replyDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return replyDAO.selectReplyObj(replyDto);
	}

	/**
	 * 掲示板のコメント情報を登録する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertReply(ReplyDTO replyDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		replyDAO.insertReply(replyDto);
	}

	/**
	 * 掲示板のコメント情報を削除する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteReply(ReplyDTO replyDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		replyDAO.deleteReply(replyDto);
	}

	/**
	 * 掲示板のコメント情報を更新する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void updateReply(ReplyDTO replyDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		replyDAO.updateReply(replyDto);
	}
}
