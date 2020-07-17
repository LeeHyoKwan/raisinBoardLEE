package com.raisin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.raisin.model.dto.VoteDTO;

/**
 * 掲示板のDAOクラス
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class VoteDAO extends BaseDAO {

	/**
	 * 掲示板のコマンド情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public List<VoteDTO> selectVote(VoteDTO voteDto) throws SQLException {
		return (List<VoteDTO>) super.queryForList("selectReply", voteDto);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public VoteDTO selectVoteObj(String boardid, String userid) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardid", boardid);
		map.put("userid", userid);
		return (VoteDTO)super.queryForObject("selectVoteObject", map);
	}

	/**
	 * 掲示板のコメント情報を取得する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void insertVote(String boardid, String userid, String voteCountUp, String voteCountDown) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardid", boardid);
		map.put("userid", userid);
		map.put("voteCountUp", voteCountUp);
		map.put("voteCountDown", voteCountDown);
		super.insert("insertVote", map);
	}


	/**
	 * 掲示板のコメント情報を削除する
	 *
	 * @return
	 * @throws SQLException
	 */
	public void deleteVote(VoteDTO voteDto) throws SQLException {
		super.delete("deleteVote", voteDto);
	}

	/**
	 * 掲示板のコメント情報を更新する
	 *
	 * @return
	 * @throws SQLExceptionupdateBoard
	 */
	public void updateVote(String boardid, String userid, String voteCountUp, String voteCountDown) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardid", boardid);
		map.put("userid", userid);
		map.put("voteCountUp", voteCountUp);
		map.put("voteCountDown", voteCountDown);
		super.update("updateVote", map);
	}
}
