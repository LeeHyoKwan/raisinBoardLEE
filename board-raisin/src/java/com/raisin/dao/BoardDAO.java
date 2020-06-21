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
		super.insert("insertBoard", boardDto);
	}

	/**
	 * 掲示板の情報を更新する
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateBoard(BoardDTO boardDto) throws SQLException {
		super.update("updateBoard", boardDto);
	}

	/**
	 * 照会カウンター更新する。
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateBoardcount(BoardDTO boardDto) throws SQLException {
		super.update("updateBoardCount", boardDto);
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

	/**
	 * 照会カウンター更新する。
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateVotecount(String boardid, String voteKbn) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardid", boardid);
		map.put("voteKbn", voteKbn);
		super.update("updateVoteCount", map);
	}
}
