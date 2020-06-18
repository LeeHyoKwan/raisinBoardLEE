package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.raisin.action.BaseAction;
import com.raisin.action.PagingAction;
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

	//pagin
	private int currentPage = 1; // 현재 페이지
    private int totalCount; // 총 게시물의 수
    private int blockCount = 10; // 한 페이지의  게시물의 수
    private int blockPage = 5; // 한 화면에 보여줄 페이지 수
    private String pagingHtml; // 페이징을 구현한 html
    private PagingAction page; // 페이징 클래스

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
			request.setAttribute("commentSize", commentList.size());

			// 掲示物あたりのコメント数取得
			for (int listIndex = 0; listIndex < list.size(); listIndex++) {
				commentDto.setBoardid(list.get(listIndex).getBoardid());
//				commentList =commentService.getComment(commentDto);
				list.get(listIndex).setCommentCount(commentService.getComment(commentDto).size());
			}

			// ★★ページング開始
			//paging
			totalCount = list.size(); // 전체 글 개수를 구한다.
			// pagingAction 객체 생성
            page = new PagingAction(currentPage, totalCount, blockCount, blockPage);
            pagingHtml = page.getPagingHtml().toString(); // 페이지 html 생성
            pagingHtml = page.getPagingHtml().toString(); // 페이지 html 생성
            // 현재 페이지에서 보여줄 마지막 글의 번호 설정
            int lastCount = totalCount;

            /**
             * 현재 페이지의 마지막 글의 번호가
             * 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정
             */
            if(page.getEndCount() < totalCount)
                  lastCount = page.getEndCount() + 1;

            // 전체 리스트에서 현재 페이지만큼의 리스트만 가져온다.
            list = list.subList(page.getStartCount(), lastCount);

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
			if (commentList.size() > 0) {
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public PagingAction getPage() {
		return page;
	}
}
