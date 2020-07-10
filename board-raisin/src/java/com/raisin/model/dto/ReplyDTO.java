package com.raisin.model.dto;

/**
 * BoardのDTO
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class ReplyDTO {
	private int boardid;
	private int commentid;
	private int replyid;
	private String content;
	private String createdt;
	private String createuser;
	private String modidt;
	private String modiuser;
	private String userid;
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedt() {
		return createdt;
	}
	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getModidt() {
		return modidt;
	}
	public void setModidt(String modidt) {
		this.modidt = modidt;
	}
	public String getModiuser() {
		return modiuser;
	}
	public void setModiuser(String modiuser) {
		this.modiuser = modiuser;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}



}
