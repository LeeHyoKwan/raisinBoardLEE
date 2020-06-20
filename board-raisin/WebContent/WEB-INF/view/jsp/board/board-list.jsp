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
</script>
</head>
<body>
<main class="main_layout">
	<section>
		<h2>
			<a class="head_title" href="../board/index">이효관의 게시판 만들기</a>
		</h2>
		<div class="list_layout">
			<table id="boardTable"></table>
			<table class="page_table">
				<tr>
					<td colspan = "5">
						<s:property value = "pagingHtml"  escapeHtml = "false" />
					</td>
				</tr>
			</table>
			<div class="list_btn_div"'>
				<button class="btn_write"onclick="javascript:location.href='../board/writeForm'"
					type="button" id="btn_write">글쓰기</button>
			</div>
		</div>
		<br/>
		<div style="display:none;">
			<table id="boardDataTbl" >
				<s:iterator value="list" status = "stat">
					<tr align = "center">
						<s:url var="boardViewAction"  action="viewForm">
				            <s:param name="boardDto.boardid"><s:property value="boardid" /></s:param>
				            <s:param name="boardDto.displayType">view</s:param>
				            <s:param name="currentPage"><s:property value="currentPage" /></s:param>
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

			            <td id="boardid" align = "center"><s:property value = "boardid" /></td>
						<td id="title" align = "center"><s:a class="titleLink" href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
			            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
			            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
			            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
			            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
						<td id="action1" align = "center"><s:a class="actionSLink" href="%{boardViewAction}">상세</s:a></td>
						<td id="action2" align = "center"><s:a class="actionELink" href="%{boardEditAction}">편집</s:a></td>
						<td id="action3" align = "center"><s:a class="actionDLink" href="%{boardDeleteAction}">삭제</s:a></td>
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
</body>
</html>