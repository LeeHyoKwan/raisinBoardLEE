package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import com.raisin.dao.BoardDAO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;

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
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<CommentDTO> getComment(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return boardDAO.selectComment(boardDto);
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

	/**
	 * 掲示板の情報を更新する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void updateBoard(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDAO.updateBoard(boardDto);
	}

	/**
	 * 照会カウンター更新
	 *
	 * @return
	 * @throws SQLException
	 */
	public void updateBoardcount(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDAO.updateBoardcount(boardDto);
	}

	/**
	 * 掲示板の情報を削除する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteBoard(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDAO.deleteBoard(boardDto);
	}
}
