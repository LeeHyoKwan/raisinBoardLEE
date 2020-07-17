package com.raisin.service;

import java.sql.SQLException;
import java.util.List;

import com.raisin.dao.VoteDAO;
import com.raisin.model.dto.VoteDTO;

/**
 * 掲示板のサービスクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class VoteService extends BaseService {

	private VoteDAO voteDAO;

	/** コンストラクタ */
	public VoteService() {
		if (voteDAO == null) {
			voteDAO = new VoteDAO();
		}
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<VoteDTO> selectVote(VoteDTO voteDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return voteDAO.selectVote(voteDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public VoteDTO selectVoteObj(String boardid, String userid) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		return voteDAO.selectVoteObj(boardid, userid);
	}

	/**
	 * 掲示板のコメント情報を登録する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertVote(String boardid, String userid, String voteCountUp, String voteCountDown) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		voteDAO.insertVote(boardid, userid, voteCountUp, voteCountDown);
	}

	/**
	 * 掲示板のコメント情報を削除する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteVote(VoteDTO voteDto) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		voteDAO.deleteVote(voteDto);
	}

	/**
	 * 掲示板のコメント情報を更新する。
	 *
	 * @return
	 * @throws SQLException
	 */
	public void updateVote(String boardid, String userid, String voteCountUp, String voteCountDown) throws SQLException {
		super.startTransaction();
		super.commitTransaction();
		super.endTransaction();
		voteDAO.updateVote(boardid, userid, voteCountUp, voteCountDown);
	}
}
