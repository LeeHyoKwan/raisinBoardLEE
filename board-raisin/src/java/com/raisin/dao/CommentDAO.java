package com.raisin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Object> map = new HashMap<String, Object>();
		if (commentDto == null) {
			map.put("boardid", null);
		} else {
			map.put("boardid", commentDto.getBoardid());
		}
		return (List<CommentDTO>) super.queryForList("selectComment", map);
	}

	/**
	 * 掲示板のコマンド情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertComment(CommentDTO commentDto) throws SQLException {
		super.insert("insertComment", commentDto);
	}

	/**
	 * 掲示板のコマンド情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(CommentDTO commentDto) throws SQLException {
		super.delete("deleteComment", commentDto);
	}
}
