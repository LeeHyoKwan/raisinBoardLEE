package com.raisin.dao;

import java.sql.SQLException;
import java.util.List;

import com.raisin.model.dto.CommentDTO;

/**
 * 掲示板のDAOクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class CommentDAO extends BaseDAO {

	/**
	 * 掲示板のコマンド情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<CommentDTO> selectComment(CommentDTO commentDto) throws SQLException {
		return (List<CommentDTO>) super.queryForList("selectComment", commentDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public CommentDTO selectCommentObj(CommentDTO commentDto) throws SQLException {
		return (CommentDTO)super.queryForObject("selectComment", commentDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertComment(CommentDTO commentDto) throws SQLException {
		super.insert("insertComment", commentDto);
	}

	/**
	 * 掲示板のコメント情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(CommentDTO commentDto) throws SQLException {
		super.delete("deleteComment", commentDto);
	}

	/**
	 * 掲示板のコメント情報を更新する
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateBoard(CommentDTO commentDto) throws SQLException {
		super.update("updateComment", commentDto);
	}
}
