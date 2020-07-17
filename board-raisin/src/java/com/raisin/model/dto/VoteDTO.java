package com.raisin.model.dto;

/**
 * BoardのDTO
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class VoteDTO {
	private int voteid;
	private int boardid;
	private String userid;
	private String createdt;
	private String voteCountUp;
	private String voteCountDown;
	public int getVoteid() {
		return voteid;
	}
	public void setVoteid(int voteid) {
		this.voteid = voteid;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreatedt() {
		return createdt;
	}
	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}
	public String getVoteCountUp() {
		return voteCountUp;
	}
	public void setVoteCountUp(String voteCountUp) {
		this.voteCountUp = voteCountUp;
	}
	public String getVoteCountDown() {
		return voteCountDown;
	}
	public void setVoteCountDown(String voteCountDown) {
		this.voteCountDown = voteCountDown;
	}

}
