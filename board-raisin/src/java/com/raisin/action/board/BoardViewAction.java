
package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
import com.raisin.model.vo.BoardVO;
import com.raisin.model.vo.PagingVO;
import com.raisin.service.BoardService;
import com.raisin.service.CommentService;

/**
 * 記事詳細アクションクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class BoardViewAction extends BaseAction {

	/* ロガー */
	private Logger logger = LogManager.getLogger(BoardViewAction.class);

	private BoardDTO boardDto;

	private BoardVO boardVO;

	private PagingVO pagingVO;

	private CommentDTO commentDto;

	private BoardService service;

	private CommentService commentService;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	private List<BoardDTO> allList = new ArrayList<BoardDTO>();

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

		if (boardVO == null) {
			boardVO = new BoardVO();
		}

		if (pagingVO == null) {
			pagingVO = new PagingVO();
		}
	}

	@Override
	public String execute() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardViewAction", "execute");
		try {
			// 初期リストから照会した場合照会数をカウントする
			if ("view".equals(boardVO.getDisplayType())) {
				// 照会カウンター更新
				service.updateBoardcount(boardDto);
			}

			// 掲示物の詳細情報設定
			boardDto = service.getBoardObj(boardDto);

			//commentデータを取得し、リストに保存
			commentDto.setBoardid(boardDto.getBoardid());
			commentList = commentService.getComment(commentDto);

			final String selBoardid = boardDto.getBoardid();

			// 全掲示板取得（下段）
			boardDto.setBoardid(null);
			setLimitStart(pagingVO,boardDto);
			allList = service.getBoard(boardDto);
			service.getBoardCount(boardDto);
			super.setPaging(boardDto, pagingVO);

			// 該当掲示物の番号設定
			boardDto.setBoardid(selBoardid);

		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardViewAction", "execute");
		}
		return SUCCESS;
	}

	public String writeForm() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardViewAction", "writeForm");
		// 掲示物の詳細情報設定
		boardDto = service.getBoardObj(boardDto);
		logger.debug("---------------- end {}.{} ----------------", "BoardViewAction", "writeForm");
		return SUCCESS;
	}

	public String deleteAction() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardViewAction", "deleteAction");
		try {
			//削除処理
			service.deleteBoard(boardDto);
			commentDto.setBoardid(boardDto.getBoardid());
			commentService.deleteBoard(commentDto);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardViewAction", "deleteAction");
		}
		return SUCCESS;
	}

	public String voteAction() throws Exception {
		logger.debug("---------------- start {}.{} ----------------", "BoardViewAction", "voteUp");
		try {
			service.updateVotecount(boardDto.getBoardid(),boardVO.getVoteKbn());
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.debug("---------------- end {}.{} ----------------", "BoardViewAction", "voteUp");
		}
		return SUCCESS;
	}

	public List<BoardDTO> getList() {
		return list;
	}
    public void setList(List<BoardDTO> list) {
    	this.list = list;
    }

	public List<BoardDTO> getAllList() {
		return allList;
	}

	public void setAllList(List<BoardDTO> allList) {
		this.allList = allList;
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
