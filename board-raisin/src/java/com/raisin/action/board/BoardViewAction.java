
package com.raisin.action.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raisin.action.BaseAction;
import com.raisin.action.PagingAction;
import com.raisin.model.dto.BoardDTO;
import com.raisin.model.dto.CommentDTO;
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

	private CommentDTO commentDto;

	private BoardService service;

	private CommentService commentService;

	private List<BoardDTO> list = new ArrayList<BoardDTO>();

	private List<BoardDTO> allList = new ArrayList<BoardDTO>();

	private List<CommentDTO> commentList = new ArrayList<CommentDTO>();

	// ページング
	private int currentPage = 1; // 現在ページ
    private int totalCount; // 全掲示物数
    private int blockCount = 10; // 1ページあたり掲示物数
    private int blockPage = 5; // 一画面に表示するページ数
    private String pagingHtml; // パージングHTML
    private PagingAction page; // ページングクラス

    private String voteKbn;

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
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "execute");
		try {
			// 詳細をクリックした場合
			if ("view".equals(boardDto.getDisplayType())) {
				// 照会カウンター更新
				service.updateBoardcount(boardDto);
			}

			// 掲示物の詳細情報設定
			list = service.getBoard(boardDto);
			boardDto.setTitle(list.get(0).getTitle());
			boardDto.setContent(list.get(0).getContent());
			boardDto.setCreateuser(list.get(0).getCreateuser());
			boardDto.setCreatedt(list.get(0).getCreatedt());
			boardDto.setBoardcount(list.get(0).getBoardcount());
			boardDto.setVoteCountUp(list.get(0).getVoteCountUp());
			boardDto.setVoteCountDown(list.get(0).getVoteCountDown());

			//commentデータを取得し、リストに保存
			commentDto.setBoardid(boardDto.getBoardid());
			commentList = commentService.getComment(commentDto);
			boardDto.setCommentCount(commentList.size());
			// 1掲示物のコマンド数設定
			final String selBoardid = boardDto.getBoardid();

			// 全掲示板取得
			boardDto.setBoardid(null);
			allList = service.getBoard(boardDto);

			// 掲示物あたりのコメント数設定
			for (int listIndex = 0; listIndex < allList.size(); listIndex++) {
				commentDto.setBoardid(allList.get(listIndex).getBoardid());
				allList.get(listIndex).setCommentCount(commentService.getComment(commentDto).size());
			}

			// 該当掲示物の番号設定
			boardDto.setBoardid(selBoardid);


			// ページング設定
			totalCount = list.size(); // 全掲示物数
			// pagingAction オブジェクト生成
            page = new PagingAction(currentPage, totalCount, blockCount, blockPage);
            // ページ html生成
            pagingHtml = page.getPagingHtml().toString();
            // 現在ページで表示する最後番号設定
            int lastCount = totalCount;

            // 現在ページの最後の番号が全体の番号より小さい場合はlastCountを+1に設定
            if(page.getEndCount() < totalCount)
                  lastCount = page.getEndCount() + 1;
            // 全リストから現在ページのリストを設定
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
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "writeForm");
		logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "writeForm");
		return SUCCESS;
	}

	public String deleteAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "deleteAction");
		try {
			// 削除対象boardデータ取得
			list = service.getBoard(boardDto);
			// データがある場合は削除処理
			if (list.size() > 0) {
				service.deleteBoard(boardDto);
			}
			// 削除対象のコマンドデータ取得
			commentDto.setBoardid(boardDto.getBoardid());
			commentList = commentService.getComment(commentDto);
			// データがある場合は削除処理
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

	public String voteAction() throws Exception {
		logger.info("---------------- start {}.{} ----------------", "BoardViewAction", "voteUp");
		try {
			service.updateVotecount(boardDto.getBoardid(),voteKbn);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			logger.info("---------------- end {}.{} ----------------", "BoardViewAction", "voteUp");
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

	public String getVoteKbn() {
		return voteKbn;
	}

	public void setVoteKbn(String voteKbn) {
		this.voteKbn = voteKbn;
	}


}
