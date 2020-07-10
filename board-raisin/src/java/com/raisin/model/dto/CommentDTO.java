package com.raisin.model.dto;

/**
 * BoardのDTO
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class CommentDTO {
	private String commentid;
	private String boardid;
	private String userid;
	private String content;
	private String createuser;
	private String createdt;
	private String modiuser;
	private String modidt;

	private int replyid;
	private int replyCount;

	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getBoardid() {
		return boardid;
	}
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getCreatedt() {
		return createdt;
	}
	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}
	public String getModiuser() {
		return modiuser;
	}
	public void setModiuser(String modiuser) {
		this.modiuser = modiuser;
	}
	public String getModidt() {
		return modidt;
	}
	public void setModidt(String modidt) {
		this.modidt = modidt;
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}



}
