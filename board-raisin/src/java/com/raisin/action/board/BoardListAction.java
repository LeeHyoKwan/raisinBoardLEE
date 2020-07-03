package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.vo.BoardVO;
import com.raisin.model.vo.PagingVO;
import com.raisin.service.BoardService;
import com.raisin.service.CommentService;

/**
 * 記事一覧アクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardListAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(BoardListAction.class);

	private BoardDTO boardDto;

	private BoardVO boardVO;

	private PagingVO pagingVO;

	private BoardService service;

	private CommentService commentService;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	/** コンストラクタ */
	public BoardListAction() {
		if (service == null) {
			service = new BoardService();
		}
		if (commentService == null) {
			commentService = new CommentService();
		}
		if (pagingVO == null) {
			pagingVO = new PagingVO();
		}
	}

	@Override
	public String execute() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardListAction", "execute");
		try {
			// リスト設定（ページング）
			list = service.getBoard(boardDto);
			list = super.setPaging(list, pagingVO);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardListAction", "execute");
		}
		return SUCCESS;
	}

	public String writeForm() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardListAction", "writeForm");
		logger.debug("---------------- end {}.{} ----------------", "BoardListAction", "writeForm");
		return SUCCESS;
	}

	public String writeAction() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardListAction", "writeAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			// 登録データ設定（board）
			boardDto.setUserid(account.getUserid());
			boardDto.setCreateuser(account.getUsername());
			boardDto.setModiuser(account.getUsername());
			// displayがeditの場合、更新処理実施
			if ("edit".equals(boardVO.getDisplayType())) {
				service.updateBoard(boardDto);
			} else {
				//boardデータを登録
				service.insertBoard(boardDto);
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardListAction", "writeAction");
		}
		return SUCCESS;
	}

	public String editAction() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardListAction", "writeAction");
		try {
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			// 更新データ設定（board）
			boardDto.setModiuser(account.getUsername());
			service.updateBoard(boardDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardListAction", "writeAction");
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

	public BoardVO getBoardVO() {
		return boardVO;
	}

	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}

	public PagingVO getPagingVO() {
		return pagingVO;
	}

	public void setPagingVO(PagingVO pagingVO) {
		this.pagingVO = pagingVO;
	}


}
