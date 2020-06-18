package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.commentDTO;
import com.raisin.service.BoardService;

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

	private BoardService service;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	private List<commentDTO> commentList = new ArrayList<commentDTO>();

	/** コンストラクタ */
	public BoardViewAction() {
		if (service == null) {
			service = new BoardService();
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
			commentList = service.getComment(boardDto);
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

	public List<commentDTO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<commentDTO> commentList) {
		this.commentList = commentList;
	}



}
