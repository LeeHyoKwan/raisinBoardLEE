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

<!-- <script src="../resources/js/bootstrap.js" type="text/javascript"></script> -->
<script src="../resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../resources/js/jquery-ui.js"></script>
<script>
	jQuery.browser = {};
	(function () {
	    jQuery.browser.msie = false;
	    jQuery.browser.version = 0;
	    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
	        jQuery.browser.msie = true;
	        jQuery.browser.version = RegExp.$1;
	    }
	})();
</script>
<script src="../resources/js/i18n/grid.locale-ja.js" type="text/javascript"></script>
<script src="../resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<script>
	// jqgrid初期設定
	var searchResultColNames =  ['No', '제목', '글쓴이', '작성일', '갱신자', '갱신일'];
	var searchResultColModel =
	                [
	                	{name:'boardid', align:'center', width:'30'},
		                {name:'title', align:'left', width:'600'},
		                {name:'createuser', align:'center',width:'80'},
		                {name:'createdt', align:'center', width:'130'},
		                {name:'modiuser', align:'center', width:'80'},
		                {name:'modidt', align:'center', width:'130'}
	                ];

	$(window.document).ready(function(){
		$("#boardTable").jqGrid({
			datatype: 'local',
			data: getBoardData(),
			colNames: searchResultColNames,
			colModel: searchResultColModel,
			shrinkToFit:false,
			restoreAfterSelect: false,
			rowNum:10,
			rowList:[10,20,30],
			pager: '#pager',
			pagerpos : 'center',
			viewrecords: false,
			shrinkToFit : true,
			sortable : false,
			width:1051,
			height: "auto",
			caption: '게시판',
			cmTemplate: { sortable: false },
			shrinkToFit: false
		});
		$("#btn_write").button();
	});

	// boardテーブルデータを配列に設定
	function getBoardData() {
		var boardDataTbl = $('#boardDataTbl tr');
		var rowData = new Array();
		var dataArray = new Array();

		var boardid = new String();
		var title = new String();
		var createuser = new String();
		var createdt = new String();
		var modiuser = new String();
		var modidt = new String();

		for(var i=0;i<boardDataTbl.length;i++) {
			if (boardDataTbl[i].children.length != 1 ) {
				boardidVl = boardDataTbl[i].children.boardid.innerHTML;
				titleVl = boardDataTbl[i].children.title.innerHTML;
				createuserVl = boardDataTbl[i].children.createuser.innerHTML;
				createdtVl = boardDataTbl[i].children.createdt.innerHTML;
				modiuserVl = boardDataTbl[i].children.modiuser.innerHTML;
				modidtVl = boardDataTbl[i].children.modidt.innerHTML;
				rowData = {
								boardid: boardidVl,
								title : titleVl,
								createuser : createuserVl,
								createdt : createdtVl,
								modiuser : modiuserVl,
								modidt : modidtVl};
				dataArray.push(rowData);
			}
		}
		return dataArray;
	}
</script>
</head>
<body>
<main id="container"  style="width: 1051px; margin:0 auto;">
	<section>
	<h2 style="color: #3c4790; font-size: 24px; font-family: 'Nanum Gothic', sans-serif; letter-spacing: -1px; margin-left: 20px; margin-top: 15px; font-weight: bolder;">
		<a style="text-decoration: none; color: #3c4790;" href="../board/index">이효관의 게시판  만들기
		</a>
	</h2>
	<hr align="left" style="background-color: #3c4790; height:1px; width: 1051px">
	<div style='width:1051px; display: block'>
		<table id="boardTable"></table>
		<div id="pager"></div>
		<div style='float: right; margin-top: 5px;'>
			<button onclick="javascript:location.href='../board/writeForm'"
				type="button" id="btn_write" style="color:#fff;border-style:solid; background-color: #3c4790; font-weight: bold; border-radius: 4px">글쓰기</button>
		</div>
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
					<td id="title" align = "center"><s:a href="%{boardViewAction}"><s:property value = "title" /></s:a></td>
		            <td id="createuser" align = "center"><s:property value = "createuser" /></td>
		            <td id="createdt" align = "center"><s:property value = "createdt" /></td>
		            <td id="modiuser" align = "center"><s:property value = "modiuser" /></td>
		            <td id="modidt" align = "center"><s:property value = "modidt" /></td>
		      </tr>
			</s:iterator>
		</table>
	</div>
	</section>
</main>
</body>
</html>