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
    $(".ui-dialog-buttonpane button:contains('확인')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');

    $('.wrap').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.wrap').find( 'textarea' ).keyup();

    $('.contentDiv').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.contentDiv').find( 'textarea' ).keyup();
});
</script>
</head>
  <style>
  </style>
    <script>

  </script>
<body >
<main id="container"  style="width: 1051px; margin:0 auto;">
	<section>
		<form name="boardView_form"method="post" action="../board/writeAction">
				<h2 style="color: #3c4790; font-size: 24px; font-family: 'Nanum Gothic', sans-serif; letter-spacing: -1px; margin-top: 15px; font-weight: bolder;">
					<a style="text-decoration: none; color: #3c4790;" href="../board/index">이효관의 게시판 만들기
					</a>
				</h2>
				<hr align="left" style="background-color: #3c4790; height:1px; width: 1051px">
				<article id="write_wrap">
					<div >
						<div>
							<s:textfield id="title"  type="text" name="boardDto.title"  maxlength="40"
								style="width: 1051px; height: 33px; line-height: 35px;
								border-style: none; border-bottom: 1px solid #eee; background-color: #ffffff; font-weight: bolder;" disabled="true"/>
						</div>
						<div class="contentDiv" style="padding-top: 30px; padding-bottom: 30px;">
							<s:textarea id="content" type="text" name="boardDto.content"
								style="width: 1051px; line-height: 35px;
								border-style: none; background-color: #ffffff; resize: none;" disabled="true"/>
						</div>
						<div style="font-family: '굴림',Gulim;font-size: 13px;color: #333;font-weight: bold;margin-bottom: 5px;">
							<div style="float:left; margin-right: 5px;">댓글</div><div style=" color: #d31900;float:left">${commentSize}</div><div>개</div>
						</div>
						<div>
							<table id="commentTbl" style="width:1051px; margin-top: 5px; border-top: 2px solid #525eaa;">
								<s:iterator value="commentList" status = "stat">
									<tr align = "center" style="border-bottom: 1px solid #ddd;">
							            <td id="createuser" width="132px " align = "left" style="font-family: '굴림',Gulim; font-size: 12px; color: #777;"><s:property value = "createuser" /></td>
							            <td id="content" align = "left" style="padding: 10px; font-family: '굴림',Gulim; font-size: 13px; color: #333;"><s:property value = "content" /></td>
							      </tr>
								</s:iterator>
							</table>
						</div>
						<s:set var="commentSize">${commentSize}</s:set>
						<s:if test="%{#commentSize!=0}">
							<div style="height:69px">
							</div>
						</s:if>
						<!-- コメント入力欄 -->
						<div style="padding:10px; background-color:#fafafa; border-top: 2px solid #525eaa; border-bottom: 2px solid #525eaa;">
							<s:textarea id="comment" type="text" name="commentDto.content" maxlength="400" style="width: 1010px; height: 78px; border: 1px solid #cecdce;
								background-color: #ffffff; resize: none; margin: 10px;"></s:textarea>
							<div style="text-align-last: right;">
								<button onclick = "onClickCommentInsert()"
									type="button" id="btn_comment_insert" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">등록</button>
							</div>
						</div>
						<div style='float: right; margin-top: 5px;'>
							<button onclick="javascript:location.href='../board/writeForm'"
								type="button" id="btn_edit" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">글작성</button>
						</div>
						<div style='float: right; margin-top: 5px; margin-right: 10px'>
							<button onclick = "onClickEdit()"
								type="button" id="btn_edit" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">수정</button>
						</div>
						<div style='float: right; margin-top: 5px; margin-right: 10px'>
							<button onclick="onClickDelete()"
								type="button" id="btn_delete" style="color:#fff;border-style:solid; background-color: #666; border-radius: 4px">삭제</button>
						</div>
					</div>
				</article>
			<!-- 現在掲示板番号 -->
			<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
			<!-- 現在ページ -->
			<s:hidden id="currentPage"  type="text" name="currentPage" />
			<!-- 掲示板更新用パラメータ -->
			<s:hidden id="displayType"  type="text" name="boardDto.displayType" value="edit"/>
		</form>
	</section>
</main>
<main id="container"  style="width: 1051px; margin:0 auto; padding-top:50px">
	<section>
	<div style='width:1051px; display: block'>
		<table id="boardTable"></table>
		<table style="margin-right: auto;margin-left: auto;">
			<tr>
				<td colspan = "5"><s:property value = "pagingHtml"  escapeHtml = "false" /></td>
			</tr>
		</table>
	</div>
	<br/>
	<div style="display:none;">
		<table id="boardDataTbl" >
			<s:iterator value="list" status = "stat">
				<tr align = "center">
		            <td id="boardid" align = "center"><s:property value = "boardid" /></td>
					<s:url var="boardViewAction"  action="viewForm">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			        </s:url>
			        <s:url var="boardEditAction"  action="editAction">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
			           	<s:param name="boardDto.displayType">edit</s:param>
			        </s:url>
			        <s:url var="boardDeleteAction"  action="deleteAction">
			            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
			            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
			        </s:url>

					<td id="title" align = "center"><s:a href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
		            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
		            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
		            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
		            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
		            <td id="action1" align = "center"><s:a class="actionLink"  href="%{boardViewAction}">상세</s:a></td>
					<td id="action2" align = "center"><s:a class="actionLink" href="%{boardEditAction}">편집</s:a></td>
					<td id="action3" align = "center"><s:a class="actionLink" href="%{boardDeleteAction}">삭제</s:a></td>
					<td id="boardcount" align = "center"><s:property value = "boardcount" /></td>

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
</body>
</html>