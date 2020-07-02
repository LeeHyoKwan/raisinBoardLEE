package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.action.PagingAction;
import com.raisin.model.dto.AccountDTO;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
import com.raisin.model.vo.BoardVO;
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

	private CommentDTO commentDto;

	private BoardService service;

	private CommentService commentService;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	private List<CommentDTO> commentList = new ArrayList<CommentDTO>();

	// ページング
	private int currentPage = 1; // 現在ページ
    private int totalCount; // 全掲示物数
    private int blockCount = 10; // 1ページあたり掲示物数
    private int blockPage = 5; // 一画面に表示するページ数
    private String pagingHtml; // パージングHTML
    private PagingAction page; // ページングクラス

	/** コンストラクタ */
	public BoardListAction() {
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
		logger.info("---------------- start {}.{} ----------------", "BoardListAction", "execute");
		try {
			//boardデータを取得し、リストに保存
			list = service.getBoard(boardDto);

			// 掲示物あたりのコメント数取得
			for (int listIndex = 0; listIndex < list.size(); listIndex++) {
				commentDto.setBoardid(list.get(listIndex).getBoardid());
				commentList =commentService.getComment(commentDto);
				list.get(listIndex).setCommentCount(commentList.size());
			}

			// ページング設定
			totalCount = list.size(); // 全掲示物数
			// pagingAction オブジェクト生成
            page = new PagingAction(currentPage, totalCount, blockCount, blockPage);
            // ページ html生成
            pagingHtml = page.getPagingHtml().toString();
            // 現在ページで表示する最後番号設定
            int lastCount = totalCount;

            // 現在ページの最後の番号が全体の番号より小さい場合はlastCountを+1に設定
            if(page.getEndCount() < totalCount) {
            	lastCount = page.getEndCount() + 1;
            }
            // 全リストから現在ページのリストを設定
            list = list.subList(page.getStartCount(), lastCount);


            // ユーザー権限確認
            AccountDTO account = super.getSessionUser();
            for(int accountIndex=0; accountIndex < list.size(); accountIndex++) {
            	if(account.getUserid().equals(list.get(accountIndex).getUserid())){
            		list.get(accountIndex).setAuthorityAccount(true);
            	} else {
            		list.get(accountIndex).setAuthorityAccount(false);
            	}
            }
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
			// ユーザーセッション情報
			AccountDTO account = super.getSessionUser();
			// 日付情報
			final String sysDate = super.getSysDate();
			// 登録データ設定（board）
			boardDto.setUserid(account.getUserid());
			boardDto.setCreateuser(account.getUsername());
			boardDto.setModiuser(account.getUsername());
			boardDto.setCreatedt(sysDate);
			boardDto.setModidt(sysDate);
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

	public BoardVO getBoardVO() {
		return boardVO;
	}

	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}


}
