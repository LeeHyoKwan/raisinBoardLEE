package com.raisin.action.board;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.ReplyDTO;
import com.raisin.model.vo.PagingVO;
import com.raisin.service.ReplyService;

/**
 * 記事コマンドアクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardReplyAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(BoardReplyAction.class);

	private ReplyDTO replyDto;

	private BoardDTO boardDto;

	private ReplyService service;

	private PagingVO pagingVO;

	/** コンストラクタ */
	public BoardReplyAction() {
		if (service == null) {
			service = new ReplyService();
		}
	}

	@Override
	public String execute() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardReplyAction", "execute");
		try {
			replyDto = service.getReplyObj(replyDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardReplyAction", "execute");
		}
		return SUCCESS;
	}

	public String writeAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardReplyAction", "writeAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			// 日付情報
			// 登録データ設定（board）
			replyDto.setUserid(account.getUserid());
			replyDto.setCreateuser(account.getUsername());
			replyDto.setModiuser(account.getUsername());
			replyDto.setBoardid(Integer.parseInt(boardDto.getBoardid()));
			// コマンドデータを登録
			service.insertReply(replyDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardReplyAction", "writeAction");
		}
		return SUCCESS;
	}

	public String deleteAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardReplyAction", "deleteAction");
		try {
			// コマンド削除
			service.deleteReply(replyDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardReplyAction", "deleteAction");
		}
		return SUCCESS;
	}

	public String editAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardReplyAction", "deleteAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			replyDto.setModiuser(account.getUsername());
			// コマンド削除
			service.updateReply(replyDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardReplyAction", "deleteAction");
		}
		return SUCCESS;
	}




	public ReplyDTO getReplyDto() {
		return replyDto;
	}

	public void setReplyDto(ReplyDTO replyDto) {
		this.replyDto = replyDto;
	}

	public BoardDTO getBoardDto() {
		return boardDto;
	}

	public void setBoardDto(BoardDTO boardDto) {
		this.boardDto = boardDto;
	}

	public PagingVO getPagingVO() {
		return pagingVO;
	}

	public void setPagingVO(PagingVO pagingVO) {
		this.pagingVO = pagingVO;
	}


}
