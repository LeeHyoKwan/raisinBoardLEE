<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeBoard</title>

<link href="../resources/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/board-ui.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

<script src="../resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../resources/js/boardJquery.js" type="text/javascript"></script>
<script src="../resources/js/jquery-ui.js"></script>
<script src="../resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../resources/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../resources/js/i18n/grid.locale-ja.js" type="text/javascript"></script>
<script src="../resources/js/moment.min.js" type="text/javascript"></script>
<script src="../resources/js/boardJs.js" type="text/javascript"></script>
<script src="../resources/js/jqGridJs.js" type="text/javascript"></script>
<script>
$(function(){
    $("#dialogDelete").dialog({
        autoOpen:false,
        resizable:false,
        title: '削除',
        buttons:{
            "キャンセル":function(){
                $(this).dialog("close");
            },"確認":function(){
            	document.boardView_form.action = "../board/deleteAction";
            	document.boardView_form.submit();
            }
        }
    });

    $("#dialogCommentDeleteList").dialog({
        autoOpen:false,
        resizable:false,
        title: 'コメント削除',
        buttons:{
            "キャンセル":function(){
                $(this).dialog("close");
            },"確認":function(){
            	document.boardView_form.submit();
            }
        }
    });
    $(".ui-dialog-buttonpane button:contains('確認')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');

    $('.wrap').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.wrap').find( 'textarea' ).keyup();

    $('.content_div').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.content_div').find( 'textarea' ).keyup();
});
</script>
</head>
  <style>
  </style>
    <script>

  </script>
<body >
<main class="main_layout">
	<section>
		<form name="boardView_form"method="post" action="../board/writeAction">
				<div style=" text-align-last: right; font: 11px gulim, Arial;color: #777;margin-top: 15px;">
					<span>ログイン ユーザー:   </span>
					<span><s:property value="#session.SESSION_USER.username" /></span>
				</div>
				<div style="text-align-last: right; font: 11px gulim, Arial;color: #777;">
					<span><a href="../login/logout">ログアウト</a></span>
				</div>
				<h2>
					<a class="head_title" href="../board/index">掲示板</a>
				</h2>
				<article id="write_wrap">
					<div >
						<header>
							<div class="header_div">
								<h3>
									<s:label class="title_subject" id="title"  type="text" name="boardDto.title"></s:label>
								</h3>
								<div class="writer">
									<div class="writer_lf">
										<span>
											<s:label name="boardDto.createuser" />
										</span>
										<span class="writer_dt">
											<s:label name="boardDto.createdt" />
										</span>
									</div>
									<div class="writer_rf">
										<span class="view_count">照会 <s:label name="boardDto.boardcount" /></span>
										<span class="view_rec_num">推薦 <s:label name="boardDto.voteCountUp" /></span>
										<span class="view_comment_count">コメント <s:label name="boardDto.commentCount" /></span>
									</div>
								</div>
							</div>
						</header>
						<div class="content_div">
							<s:textarea class="content_ta" id="content" type="text" name="boardDto.content" disabled="true"/>
						</div>

						<div class="vote">
							<button type="button" class="vb-btn vb-white" onclick="onClickVote('up');">
								<span class="lang">
									<i class="xi-thumbs-up" style="color:#F94966"></i> 推薦
								</span>
								<span class="num" id="vm_v_count"><s:label name="boardDto.voteCountUp" /></span>
							</button>
							<button type="button" class="vb-btn vb-white" onclick="onClickVote('down');">
								<span class="lang">
									<i class="xi-thumbs-down" style="color:#336699"></i> 非推薦
								</span>
								<span class="num" id="vm_b_count"><s:label name="boardDto.voteCountDown" /></span>
							</button>
						</div>

						<div class="comment_div">
							<div class="comment_lf">コメント</div><div class="comment_rt"><s:label name="boardDto.commentCount"/></div><div>個</div>
						</div>
						<div>
							<table class="cmt_tbl" id="commentTbl">
								<s:iterator value="commentList" status = "stat">
									<s:if test="replyid==0">
										<s:if test="replyCount==0">
											<tr class="cmt_tr" align="center" style="border-bottom: 1px solid #ddd;">
										</s:if>
										<s:else>
											<tr class="cmt_tr" align="center">
										</s:else>
										    <td class="cmt_cu" id="createuser" width="132px " align = "left">
										    	<s:property value = "createuser" />
										    </td>
										    <s:if test="replyCount==0">
											    <td onclick='replyOpen(this,0);' class="cmt_cnt" id="content" align = "left" style="cursor: pointer;">
										    </s:if>
										    <s:else>
										    	<td onclick='replyOpen(this,1);' class="cmt_cnt" id="content" align = "left" style="cursor: pointer;">
										    </s:else>
										    	<s:property value = "content" />
										    </td>
										    <td class="date_time" id="createdt" align = "left">
										    	<s:property value = "createdt" />
										    </td>
										    <td id="content" align = "left">
												<s:if test="#session.SESSION_USER.userid == userid ">
													<s:url var="deleteAction"  action="../comment/commentDeleteAction">
													    <s:param name="commentDto.boardid"><s:property value="boardid" /></s:param>
													    <s:param name="commentDto.commentid"><s:property value="commentid" /></s:param>
												    </s:url>
												    <s:url var="editAction"  action="../comment/commentFormAction">
													    <s:param name="commentDto.boardid"><s:property value="boardid" /></s:param>
													    <s:param name="commentDto.commentid"><s:property value="commentid" /></s:param>
												    </s:url>
												    <s:a  href="%{editAction}">
												    	<i class="cmic xi-pen">編集&nbsp</i>
												    </s:a>
												    <s:a  href="javascript:onClickCommentDeleteList('%{deleteAction}')">
												    	<i class="cmic xi-eraser">削除</i>
												    </s:a>
												</s:if>
										    </td>
									    </tr>
								    </s:if>
								    <s:if test="replyid==0 ">
										<tr>
										<td colspan= 4>
											<div class="reply_div" style="height:160px; width:1020px; background: #fafafa; border: 1px solid #ddd;float: right; display: none;">
												<textarea style="width:998px; height:100px" class="cmt_txta" id="reply" maxlength="400"></textarea>
												<button class="view_btn" onclick = "onClickReplyInsert(this,<s:property value = "commentid" />)" style="margin-right: 10px; float:right; margin-top: -9px;"
													type="button" id="btn_comment_insert">投稿</button>
											</div>
										</tr>
									</s:if>
								    <s:else>
								    	<s:if test="replyCount == replyid">
											<tr style="border-bottom: 1px solid #ddd;">
								    	</s:if>
								    	<s:else>
								    		<tr>
								    	</s:else>
												<td colspan= 4>
													<div class="reply_div" style="height:160px; width:1020px; background: #fafafa; border: 1px solid #ddd;float: right; display: none;">
														<textarea style="width:998px; height:100px" class="cmt_txta" id="reply" maxlength="400"></textarea>
														<button class="view_btn" onclick = "onClickReplyInsert(this,<s:property value = "commentid" />)" style="margin-right: 10px; float:right; margin-top: -9px;"
															type="button" id="btn_comment_insert">投稿</button>
													</div>
													<s:if test="replyCount == replyid">
														<div style="padding:5px; margin-bottom:10px; font-size: 13px; background: #fafafa; border: 1px solid #ddd;width: 1020px;float: right;margin-top: -5px;">
													</s:if>
													<s:else>
														<div style="padding:5px; font-size: 13px; background: #fafafa; border: 1px solid #ddd;width: 1020px;float: right;margin-top: -5px;">
													</s:else>
														<div style="font-size: 12px;color: #777;float:left; width: 132px;">
															<s:property value = "createuser" />
														</div>
														<div  style="float:left; width: 20px;">
															<i class="cmic  xi-reply" style="transform: rotate(180deg); color: #3c4790;"></i>
														</div>
														<div  style="float:left; width: 640px;">
															<s:property value = "content" />
														</div>
														<s:if test="#session.SESSION_USER.userid == userid ">
															<div style="float:right; margin-right: 5px;">
																	<s:url var="deleteAction"  action="../reply/replyDeleteAction">
																	    <s:param name="replyDto.boardid"><s:property value="boardid" /></s:param>
																	    <s:param name="replyDto.commentid"><s:property value="commentid" /></s:param>
																	     <s:param name="replyDto.replyid"><s:property value="replyid" /></s:param>
																    </s:url>
																    <s:url var="editAction"  action="../reply/replyFormAction">
																	    <s:param name="replyDto.boardid"><s:property value="boardid" /></s:param>
																	    <s:param name="replyDto.commentid"><s:property value="commentid" /></s:param>
																	    <s:param name="replyDto.replyid"><s:property value="replyid" /></s:param>
																    </s:url>
																    <s:a  href="%{editAction}">
																    	<i class="cmic xi-pen">編集&nbsp</i>
																    </s:a>
																    <s:a  href="javascript:onClickCommentDeleteList('%{deleteAction}')">
																    	<i class="cmic xi-eraser">削除</i>
																    </s:a>
															</div>
															<div style="margin-right: 16px; font-size: 12px; color: #999;float:right">
																<s:property value = "createdt" />
															</div>
														</s:if>
														<s:else>
															<div style="margin-right: 16px; font-size: 12px; color: #999;float:right;width: 195px;">
																<s:property value = "createdt" />
															</div>
														</s:else>
													</div>
												</td>
										    </tr>
								    </s:else>
								</s:iterator>
							</table>
						</div>
						<s:set var="commentSize"><s:label name="boardDto.commentCount" /></s:set>
						<s:if test="%{#commentSize!=0}">
							<div style="height:69px">
							</div>
						</s:if>
						<!-- コメント入力欄 -->
						<div class="cmt_inp">
							<s:textarea class="cmt_txta" id="comment" type="text" name="commentDto.content" maxlength="400" onkeypress="userKeyPress('insert')"></s:textarea>
							<div class="cmt_in_div">
								<button class="view_btn" onclick = "onClickCommentInsert()"
									type="button" id="btn_comment_insert">投稿</button>
							</div>
						</div>
						<div class="view_ibtn_div">
							<button class="view_btn" onclick="javascript:location.href='../board/writeForm'" type="button" id="btn_edit">記事登録</button>
						</div>
						<div class="view_edbtn_div">
							<button class="view_btn" onclick = "onClickEdit()" type="button" id="btn_edit">編集</button>
						</div>
						<div class="view_edbtn_div">
							<button class="view_btn" onclick="onClickDelete()" type="button" id="btn_delete">削除</button>
						</div>
					</div>
				</article>
			<!-- 現在掲示板番号 -->
			<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
			<!-- 現在ページ -->
			<s:hidden id="currentPage"  type="text" name="pagingVO.currentPage" />
			<!-- 掲示板更新用パラメータ -->
			<s:hidden id="displayType"  type="text" name="boardVO.displayType" value="edit"/>
		</form>
	</section>
</main>
<main class="main_view_layout">
	<section>
	<div class="list_layout">
		<table id="boardTable"></table>
			<table class="page_table">
				<tr>
					<td colspan = "5">
						<s:property value = "pagingVO.pagingHtml"  escapeHtml = "false" />
					</td>
				</tr>
			</table>
	</div>
	<br/>
	<div style="display:none;">
		<table id="boardDataTbl" >
			<s:iterator value="allList" status = "stat">
				<tr align = "center">
		            <td id="boardid" align = "center"><s:property value = "boardid" /></td>
					<s:url var="boardViewAction"  action="viewForm">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="boardVO.displayType">view</s:param>
			            <s:param name="pagingVO.currentPage"><s:property value="pagingVO.currentPage" /></s:param>
			        </s:url>
			        <s:url var="boardEditAction"  action="editForm">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="pagingVO.currentPage"><s:property value="pagingVO.currentPage" /></s:param>
			           	<s:param name="boardVO.displayType">edit</s:param>
			        </s:url>
			        <s:url var="boardDeleteAction"  action="deleteAction">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="pagingVO.currentPage"><s:property value="pagingVO.currentPage" /></s:param>
			        </s:url>

					<td id="title" align = "center"><s:a class="titleLink" href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
		            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
		            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
		            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
		            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
		            <td id="action1" align = "center"><s:a class="actionSLink"  href="%{boardViewAction}">詳細</s:a></td>
					<s:if test="#session.SESSION_USER.userid != userid ">
		            	<td id="action2" align = "center"><s:a class="actionELinkFalse" href="%{boardEditAction}">編集</s:a></td>
		            	<td id="action3" align = "center"><s:a class="actionDLinkFalse" href="javascript:onClickDeleteList('%{boardDeleteAction}')" >削除</s:a></td>
		            </s:if>
		            <s:else>
			            <td id="action2" align = "center"><s:a class="actionELink" href="%{boardEditAction}">編集</s:a></td>
						<td id="action3" align = "center"><s:a class="actionDLink" href="javascript:onClickDeleteList('%{boardDeleteAction}')" >削除</s:a></td>
		            </s:else>
					<td id="boardcount" align = "center"><s:property value = "boardcount" /></td>
					<td id="authorityAccount" align = "center"><s:property value = "authorityAccount" /></td>

		      		<s:set var="commentCount"><s:property value = "commentCount" /></s:set>
					<s:if test="%{#commentCount!=0}">
			            <td id="commentCount" align = "center"><s:a href="%{boardViewAction}">[<s:property value = "commentCount" />]</s:a></td>
					</s:if>
					<s:else>
						<td id="commentCount" align = "center"><s:property value = "commentCount" /></td>
					</s:else>
		      </tr>
			</s:iterator>
		</table>
	</div>
	</section>
</main>
<div id="dialogDelete" style="display:none;">
   <p>削除しますか?</p>
</div>
<div id="dialogCommentDeleteList" style="display:none;">
   <p>コメントを削除しますか?</p>
</div>
</body>
</html>