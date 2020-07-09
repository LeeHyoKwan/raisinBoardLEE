package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.raisin.constants.CommonContants;
import com.raisin.dao.BoardDAO;
import com.raisin.manager.MessageManager;
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
	 * nullチェックする。
	 * @param boardDto
	 * @return
	 * @throws SQLException
	 */
	public String nullChkBoard(BoardDTO boardDto) throws SQLException {
		String title = boardDto.getTitle();
		String content = boardDto.getContent();

		if (StringUtils.isEmpty(title)) {
			return MessageManager.getMessage(CommonContants.MESSAGE_COMMON_INPUT_ISEMPTY, "タイトル");
		}

		if (StringUtils.isEmpty(content)) {
			return MessageManager.getMessage(CommonContants.MESSAGE_COMMON_INPUT_ISEMPTY, "本文");
		}
		return null;
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
	public BoardDTO getBoardObj(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return boardDAO.selectBoardObj(boardDto);
	}

	/**
	 * 掲示板の件数を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void getBoardCount(BoardDTO boardDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDto.setRowCount(boardDAO.selectBoardCount().getRowCount());
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

	/**
	 * 推薦カウンター更新
	 *
	 * @return
	 * @throws SQLException
	 */
	public void updateVotecount(String boardid, String voteKbn) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		boardDAO.updateVotecount(boardid, voteKbn);
	}
}
