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
        title: '글쓰기',
        buttons:{
            "취소":function(){
                $(this).dialog("close");
            },"확인":function(){
            	document.boardView_form.action = "../board/deleteAction";
            	document.boardView_form.submit();
            }
        }
    });

    $("#dialogCommentDeleteList").dialog({
        autoOpen:false,
        resizable:false,
        title: '댓글',
        buttons:{
            "취소":function(){
                $(this).dialog("close");
            },"확인":function(){
            	document.boardView_form.submit();
            }
        }
    });
    $(".ui-dialog-buttonpane button:contains('확인')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');

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
				<h2>
					<a class="head_title" href="../board/index">게시판 만들기</a>
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
										<span class="view_count">조회 <s:label name="boardDto.boardcount" /></span>
										<span class="view_rec_num">추천 <s:label name="boardDto.voteCountUp" /></span>
										<span class="view_comment_count">댓글 <s:label name="boardDto.commentCount" /></span>
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
									<i class="xi-thumbs-up" style="color:#F94966"></i> 추천 수
								</span>
								<span class="num" id="vm_v_count"><s:label name="boardDto.voteCountUp" /></span>
							</button>
							<button type="button" class="vb-btn vb-white" onclick="onClickVote('down');">
								<span class="lang">
									<i class="xi-thumbs-down" style="color:#336699"></i> 비추천 수
								</span>
								<span class="num" id="vm_b_count"><s:label name="boardDto.voteCountDown" /></span>
							</button>
						</div>

						<div class="comment_div">
							<div class="comment_lf">댓글</div><div class="comment_rt"><s:label name="boardDto.commentCount"/></div><div>개</div>
						</div>
						<div>
							<table class="cmt_tbl" id="commentTbl">
								<s:iterator value="commentList" status = "stat">
									<tr class="cmt_tr" align="center">
							            <td class="cmt_cu" id="createuser" width="132px " align = "left"><s:property value = "createuser" /></td>
							            <td class="cmt_cnt" id="content" align = "left"><s:property value = "content" /></td>
							            <td class="date_time" id="createdt" align = "left"><s:property value = "createdt" /></td>
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
									        	<i class="cmic xi-pen">수정&nbsp</i>
									        </s:a>
									         <s:a  href="javascript:onClickCommentDeleteList('%{deleteAction}')">
								            	<i class="cmic xi-eraser">삭제</i>
									        </s:a>
									    </s:if>
							            </td>
							      </tr>
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
							<s:textarea class="cmt_txta" id="comment" type="text" name="commentDto.content" maxlength="400"></s:textarea>
							<div class="cmt_in_div">
								<button class="view_btn" onclick = "onClickCommentInsert()"
									type="button" id="btn_comment_insert">등록</button>
							</div>
						</div>
						<div class="view_ibtn_div">
							<button class="view_btn" onclick="javascript:location.href='../board/writeForm'" type="button" id="btn_edit">글작성</button>
						</div>
						<div class="view_edbtn_div">
							<button class="view_btn" onclick = "onClickEdit()" type="button" id="btn_edit">수정</button>
						</div>
						<div class="view_edbtn_div">
							<button class="view_btn" onclick="onClickDelete()" type="button" id="btn_delete">삭제</button>
						</div>
					</div>
				</article>
			<!-- 現在掲示板番号 -->
			<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
			<!-- 現在ページ -->
			<s:hidden id="currentPage"  type="text" name="currentPage" />
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
						<s:property value = "pagingHtml"  escapeHtml = "false" />
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
			            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
			        </s:url>
			        <s:url var="boardEditAction"  action="editAction">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
			           	<s:param name="boardVO.displayType">edit</s:param>
			        </s:url>
			        <s:url var="boardDeleteAction"  action="deleteAction">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
			        </s:url>

					<td id="title" align = "center"><s:a class="titleLink" href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
		            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
		            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
		            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
		            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
		            <td id="action1" align = "center"><s:a class="actionSLink"  href="%{boardViewAction}">상세</s:a></td>
					<s:if test="#session.SESSION_USER.userid != userid ">
			            	<td id="action2" align = "center"><s:a class="actionELinkFalse" href="%{boardEditAction}">편집</s:a></td>
			            	<td id="action3" align = "center"><s:a class="actionDLinkFalse" href="javascript:onClickDeleteList('%{boardDeleteAction}')" >삭제</s:a></td>
			            </s:if>
			            <s:else>
				            <td id="action2" align = "center"><s:a class="actionELink" href="%{boardEditAction}">편집</s:a></td>
							<td id="action3" align = "center"><s:a class="actionDLink" href="javascript:onClickDeleteList('%{boardDeleteAction}')" >삭제</s:a></td>
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
   <p>글을 삭제하시겠습니까?</p>
</div>
<div id="dialogCommentDeleteList" style="display:none;">
   <p>댓글을 삭제하시겠습니까?</p>
</div>
</body>
</html>