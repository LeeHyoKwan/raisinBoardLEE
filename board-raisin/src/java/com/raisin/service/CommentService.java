package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import com.raisin.dao.CommentDAO;
import com.raisin.model.dto.CommentDTO;

/**
 * 掲示板のサービスクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class CommentService extends BaseService {

	private CommentDAO commentDAO;

	/** コンストラクタ */
	public CommentService() {
		if (commentDAO == null) {
			commentDAO = new CommentDAO();
		}
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<CommentDTO> getComment(CommentDTO commentDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return commentDAO.selectComment(commentDto);
	}

	/**
	 * 掲示板のコマンド情報を登録する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertComment(CommentDTO commentDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		commentDAO.insertComment(commentDto);
	}

	/**
	 * 掲示板のコマンド情報を削除する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(CommentDTO commentDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		commentDAO.deleteBoard(commentDto);
	}
}
