package com.raisin.action;


public class PagingAction
{
      private int currentPage; // 現在ページ
      private int totalCount; // 全掲示物数
      private int totalPage; // 全ページ数
      private int blockCount; // 1ページあたり掲示物数
      private int blockPage; // 一画面に表示するページ数
      private int startCount; // 1ページで表示する掲示物の最初番号
      private int endCount; // 1ページで表示する掲示物の最後の番号
      private int startPage; // 最初ページ
      private int endPage; // 最後ページ

      private StringBuffer pagingHtml; // パージングHTML

      // ページングコンストラクター
      public PagingAction(int currentPage, int totalCount, int blockCount, int blockPage)
      {
            this.blockCount = blockCount;
            this.blockPage = blockPage;
            this.currentPage = currentPage;
            this.totalCount = totalCount;

            // 全ページ数
            totalPage = (int)Math.ceil((double) totalCount / blockCount);

            if(totalPage == 0){
            	totalPage = 1;
            }

            // 現在ページが全ページ数より大きければ、全ページ数で設定
            if(currentPage > totalPage){
            	currentPage = totalPage;
            }

            // 現在ページの最初と最後の掲示物の番号を取得
            startCount = (currentPage - 1) * blockCount;
            endCount = startCount + blockCount - 1;

            // 最初ページと最後ページを取得
            startPage = (int)((currentPage - 1) / blockPage) * blockPage + 1;
            endPage = startPage + blockPage - 1;

            // 最後ページが全ページ数より大きければ、全ページ数で設定
            if(endPage > totalPage) {
            	endPage = totalPage;
            }
            // ページングHTML
            pagingHtml = new StringBuffer();

            if(currentPage > blockPage) {
	            pagingHtml.append("<a href=../board/index.action?currentPage="
	                     + (startPage - 1) + ">");
	            pagingHtml.append("이전");
	            pagingHtml.append("</a>");
            }
            pagingHtml.append("&nbsp;&nbsp;");

            // ページ番号、現在ページは赤色で表示し、リンクを除去
            for(int i = startPage; i <= endPage; i++) {
              if(i > totalPage) {
            	  break;
              }
              if(i == currentPage) {
                pagingHtml.append("<b> <font color='#d31900' style="+ "'    margin-left: 9px;text-decoration: underline;'"+">");
                pagingHtml.append(i);
                pagingHtml.append("</font></b>");
              }
              else {
                pagingHtml.append("&nbsp;<a style=" +"'margin-left: 9px;font-size: 14px;font-weight: bold;color:#333;'" + "href='../board/index.action?currentPage=");
                pagingHtml.append(i);
                pagingHtml.append("'>");
                pagingHtml.append(i);
                pagingHtml.append("</a>");
              }
              pagingHtml.append("");
            }
            pagingHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;");

            if(totalPage - startPage >= blockPage) {
              pagingHtml.append("<a href=../board/index.action?currentPage="
                     + (endPage + 1) + ">");
              pagingHtml.append("다음");
              pagingHtml.append("</a>");
            }
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

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

}
