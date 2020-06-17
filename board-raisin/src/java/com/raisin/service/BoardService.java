package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import com.raisin.dao.BoardDAO;
import com.raisin.model.dto.BoardDTO;

/**
 * 掲示板のサービスクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardService extends BaseService {

	private BoardDAO boardDAO;

	/** コンストラクタ */
	public BoardService() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
	}

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<BoardDTO> getBoard(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return boardDAO.selectBoard(boardDto);
	}

	/**
	 * 掲示板の情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertBoard(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDAO.insertBoard(boardDto);
	}
}
