package com.raisin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;

/**
 * 掲示板のDAOクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardDAO extends BaseDAO {

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<BoardDTO> selectBoard(BoardDTO boardDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (boardDto == null) {
			map.put("boardid", null);
		} else {
			map.put("boardid", boardDto.getBoardid());
		}
		return (List<BoardDTO>) super.queryForList("selectBoard", map);
	}

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<CommentDTO> selectComment(BoardDTO boardDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (boardDto == null) {
			map.put("boardid", null);
		} else {
			map.put("boardid", boardDto.getBoardid());
		}
		return (List<CommentDTO>) super.queryForList("selectComment", map);
	}

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertBoard(BoardDTO boardDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		final String title = boardDto.getTitle();
		final String content = boardDto.getContent();
		final String createuser = boardDto.getCreateuser();
		final String modiuser = boardDto.getModiuser();
		final String userid = boardDto.getUserid();
		final String createdt = boardDto.getCreatedt();
		final String modidt = boardDto.getModidt();

		map.put("title", title);
		map.put("content", content);
		map.put("createuser", createuser);
		map.put("modiuser", modiuser);
		map.put("userid", userid);
		map.put("createdt", createdt);
		map.put("modidt", modidt);
		super.insert("insertBoard", map);
	}

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateBoard(BoardDTO boardDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		final String boardid = boardDto.getBoardid();
		final String title = boardDto.getTitle();
		final String content = boardDto.getContent();
		final String modiuser = boardDto.getModiuser();
		final String modidt = boardDto.getModidt();

		map.put("boardid", boardid);
		map.put("title", title);
		map.put("content", content);
		map.put("modiuser", modiuser);
		map.put("modidt", modidt);
		super.update("updateBoard", map);
	}

	/**
	 * 掲示板の情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(BoardDTO boardDto) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (boardDto == null) {
			map.put("boardid", null);
		} else {
			map.put("boardid", boardDto.getBoardid());
		}
		super.delete("deleteBoard", map);
	}
}
