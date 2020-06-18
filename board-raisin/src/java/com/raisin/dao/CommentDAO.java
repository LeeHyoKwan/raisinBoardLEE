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
		Map<String, Object> map = new HashMap<String, Object>();
		final String boardid = commentDto.getBoardid();
		final String content = commentDto.getContent();
		final String createuser = commentDto.getCreateuser();
		final String modiuser = commentDto.getModiuser();
		final String userid = commentDto.getUserid();
		final String createdt = commentDto.getCreatedt();
		final String modidt = commentDto.getModidt();

		map.put("boardid", boardid);
		map.put("content", content);
		map.put("createuser", createuser);
		map.put("modiuser", modiuser);
		map.put("userid", userid);
		map.put("createdt", createdt);
		map.put("modidt", modidt);
		super.insert("insertComment", map);
	}

	/**
	 * 掲示板のコマンド情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(CommentDTO commentDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (commentDto == null) {
			map.put("boardid", null);
		} else {
			map.put("boardid", commentDto.getBoardid());
		}
		super.delete("deleteComment", map);
	}
}
