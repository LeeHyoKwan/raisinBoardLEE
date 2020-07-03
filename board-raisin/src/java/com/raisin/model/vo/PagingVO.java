package com.raisin.model.vo;

import com.raisin.action.PagingAction;

public class PagingVO {

	// ページング
    private int currentPage = 1; // 現在ページ
    private final static int blockCount = 10; // 1ページあたり掲示物数
    private final static int blockPage = 5; // 一画面に表示するページ数
    private String pagingHtml; // パージングHTML
    private PagingAction page; // ページングクラス

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}
	public PagingAction getPage() {
		return page;
	}
	public void setPage(PagingAction page) {
		this.page = page;
	}



}
