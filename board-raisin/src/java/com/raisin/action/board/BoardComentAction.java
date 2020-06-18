package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
import com.raisin.service.CommentService;

/**
 * 記事一覧アクションクラス
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

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	/** コンストラクタ */
	public BoardComentAction() {
		if (service == null) {
			service = new CommentService();
		}
	}

	@Override
	public String execute() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardListAction", "execute");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardListAction", "execute");
		}
		return SUCCESS;
	}

	public String writeForm() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardListAction", "writeForm");
		logger.info("---------------- end {}.{} ----------------", "BoardListAction", "writeForm");
		return SUCCESS;
	}

	public String writeAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardListAction", "writeAction");
		try {
			AccountDTO account = super.getSessionUser();
			final String sysDate = super.getSysDate();
			commentDto.setUserid(account.getUserid());
			commentDto.setCreateuser(account.getUsername());
			commentDto.setModiuser(account.getUsername());
			commentDto.setCreatedt(sysDate);
			commentDto.setModidt(sysDate);
			commentDto.setBoardid(boardDto.getBoardid());
			// コマンドデータを登録
			service.insertComment(commentDto);
//			HttpServletRequest request = ServletActionContext.getRequest();
//			request.setAttribute("boardid", commentDto.getBoardid());
//			BoardViewAction boardViewAction = new BoardViewAction();
//			boardViewAction.execute();
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardListAction", "writeAction");
		}
		return SUCCESS;
	}

	public List<BoardDTO> getList() {
		return list;
	}
    public void setList(List<BoardDTO> list) {
    	this.list = list;
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

}
