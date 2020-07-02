package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
import com.raisin.service.CommentService;

/**
 * 記事コマンドアクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardComentAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(BoardComentAction.class);

	private BoardDTO boardDto;

	private CommentDTO commentDto;

	private CommentService service;

	private List<CommentDTO> commentList = new ArrayList<CommentDTO>();

	// ページング
	private int currentPage = 1; // 現在ページ

	/** コンストラクタ */
	public BoardComentAction() {
		if (service == null) {
			service = new CommentService();
		}
	}

	@Override
	public String execute() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardComentAction", "execute");
		try {
			commentDto = service.getCommentObj(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardComentAction", "execute");
		}
		return SUCCESS;
	}

	public String writeAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardComentAction", "writeAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			// 日付情報
			// 登録データ設定（board）
			commentDto.setUserid(account.getUserid());
			commentDto.setCreateuser(account.getUsername());
			commentDto.setModiuser(account.getUsername());
			commentDto.setBoardid(boardDto.getBoardid());
			// コマンドデータを登録
			service.insertComment(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardComentAction", "writeAction");
		}
		return SUCCESS;
	}

	public String deleteAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "deleteAction");
		try {
			// コマンド削除
			service.deleteBoard(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "deleteAction");
		}
		return SUCCESS;
	}

	public String editAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "deleteAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			commentDto.setModiuser(account.getUsername());
			// コマンド削除
			service.updateBoard(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "deleteAction");
		}
		return SUCCESS;
	}

	public List<CommentDTO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}

	public BoardDTO getBoardDto() {
		return boardDto;
	}

	public void setBoardDto(BoardDTO boardDto) {
		this.boardDto = boardDto;
	}

	public CommentDTO getCommentDto() {
		return commentDto;
	}

	public void setCommentDto(CommentDTO commentDto) {
		this.commentDto = commentDto;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
