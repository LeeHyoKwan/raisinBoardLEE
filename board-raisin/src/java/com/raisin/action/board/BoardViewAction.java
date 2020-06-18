package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
import com.raisin.service.BoardService;
import com.raisin.service.CommentService;

/**
 * 記事一覧アクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardViewAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(BoardViewAction.class);

	private BoardDTO boardDto;

	private CommentDTO commentDto;

	private BoardService service;

	private CommentService commentService;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	private List<CommentDTO> commentList = new ArrayList<CommentDTO>();

	/** コンストラクタ */
	public BoardViewAction() {
		if (service == null) {
			service = new BoardService();
		}

		if (commentService == null) {
			commentService = new CommentService();
		}

		if (commentDto == null) {
			commentDto = new CommentDTO();
		}
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "execute");
		try {
			//boardデータを取得し、リストに保存
			list = service.getBoard(boardDto);
			boardDto.setTitle(list.get(0).getTitle());
			boardDto.setContent(list.get(0).getContent());
			request.setAttribute("boardid", boardDto.getBoardid());

			boardDto.setBoardid(null);
			list = service.getBoard(boardDto);

			boardDto.setBoardid((String)request.getAttribute("boardid"));

			//commentデータを取得し、リストに保存
			commentDto.setBoardid((String)request.getAttribute("boardid"));
			commentList = commentService.getComment(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "execute");
		}
		return SUCCESS;
	}

	public String writeForm() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardListAction", "writeForm");
		logger.info("---------------- end {}.{} ----------------", "BoardListAction", "writeForm");
		return SUCCESS;
	}

	public String deleteAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "deleteAction");
		try {
			list = service.getBoard(boardDto);
			if (list.size() > 0) {
				service.deleteBoard(boardDto);
			}
			// コマンド削除処理
			commentDto.setBoardid(boardDto.getBoardid());
			commentList = commentService.getComment(commentDto);
			if (list.size() > 0) {
				commentService.deleteBoard(commentDto);
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "deleteAction");
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

	public List<CommentDTO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDTO> commentList) {
		this.commentList = commentList;
	}

	public CommentDTO getCommentDto() {
		return commentDto;
	}

	public void setCommentDto(CommentDTO commentDto) {
		this.commentDto = commentDto;
	}



}
