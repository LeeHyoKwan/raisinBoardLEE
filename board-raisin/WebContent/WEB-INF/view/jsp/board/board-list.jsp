<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeBoardlist</title>

<link href="../resources/css/bootstrap.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="../resources/css/board-ui.css"/>

<script src="../resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../resources/js/bootstrap.js" type="text/javascript"></script>
<script src="../resources/js/boardJquery.js" type="text/javascript"></script>
<script src="../resources/js/jquery-ui.js"></script>
<script src="../resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>


<script src="../resources/js/i18n/grid.locale-ja.js" type="text/javascript"></script>
<script src="../resources/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../resources/js/moment.min.js" type="text/javascript"></script>
<script src="../resources/js/boardJs.js" type="text/javascript"></script>
<script src="../resources/js/jqGridJs.js" type="text/javascript"></script>
<script>
$(function(){
    $("#dialogDeleteList").dialog({
        autoOpen:false,
        resizable:false,
        title: '削除',
        buttons:{
            "キャンセル":function(){
                $(this).dialog("close");
            },"確認":function(){
            	document.boardList_form.submit();
            }
        }
    });
    $(".ui-dialog-buttonpane button:contains('確認')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');
});
</script>
</head>
<body>
<main class="main_layout">
<section></section>
	<section>
		<form name="boardList_form"method="post">
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
			<div class="list_layout">
				<table id="boardTable"></table>
				<table class="page_table">
					<tr>
						<td>
							<s:property value="pagingVO.pagingHtml"  escapeHtml = "false" />
						</td>
					</tr>
				</table>
				<div class="list_btn_div">
					<button class="btn_write"onclick="javascript:location.href='../board/writeForm'"
						type="button" id="btn_write">記事登録</button>
				</div>
			</div>
			<br/>
			<div style="display:none;">
				<table id="boardDataTbl" >
					<s:iterator value="list" status = "stat">
						<tr align = "center">
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

				            <td id="boardid" align = "center"><s:property value = "boardid" /></td>
							<td id="title" align = "center"><s:a class="titleLink" href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
				            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
				            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
				            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
				            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
			            	<td id="action1" align = "center"><s:a class="actionSLink" href="%{boardViewAction}">詳細</s:a></td>
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
		</form>
	</section>

	<s:bean name="org.apache.struts2.util.Counter" var="testCounter">
		<s:param name="first">0</s:param>
		<s:param name="last"><s:property value='pagingCountTest'/></s:param>
	</s:bean>
	<s:iterator value="testCounter" status="stat">
	<s:if test="#testCounter.current > 5">
	</s:if>
	<s:else>
		<s:if test="pagingVO.currentPage == #testCounter.current ">
			<font color="red">
				<span>
					<s:property value="#testCounter.current" />
				</span>
			</font>
		</s:if>
		<s:else>
			<span>
				<a href="#"><s:property value="#testCounter.current" /></a>
			</span>
		</s:else>
	</s:else>
	</s:iterator>
</main>
<div id="dialogDeleteList" style="display:none;">
   <p>削除しますか?</p>
</div>
</body>
</html>