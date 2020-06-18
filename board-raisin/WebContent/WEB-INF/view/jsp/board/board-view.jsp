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

<!-- <script src="../resources/js/bootstrap.js" type="text/javascript"></script> -->
<script src="../resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../resources/js/boardJquery.js" type="text/javascript"></script>
<script src="../resources/js/jquery-ui.js"></script>
<script src="../resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../resources/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="../resources/js/i18n/grid.locale-ja.js" type="text/javascript"></script>
<script src="../resources/js/boardCommon.js" type="text/javascript"></script>
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
});

// jqgrid初期設定
const searchResultColNames =  ['No', '제목', '글쓴이', '작성일', '갱신자', '갱신일'];
const searchResultColModel =
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
});
</script>
</head>
  <style>
  </style>
    <script>
    $(document).ready(function() {
      $('.contentDiv').on( 'keyup', 'textarea', function (e){
        $(this).css('height', 'auto' );
        $(this).height( this.scrollHeight );
      });
      $('.contentDiv').find( 'textarea' ).keyup();
    });
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
						<div class="contentDiv" style="padding-top: 20px; padding-bottom: 20px;">
							<s:textarea id="content" type="text" name="boardDto.content"
								style="width: 1051px; line-height: 35px;
								border-style: none; background-color: #ffffff; resize: none;" disabled="true"/>
						</div>
						<div style="border-top: 2px solid #525eaa;">
							<table id="commentTbl" style="width:1051px; margin-top: 5px;">
								<s:iterator value="commentList" status = "stat">
									<tr align = "center" style="border-bottom: 1px solid #ddd;">
							            <td id="createuser" width="132px " align = "left" style="font-family: '굴림',Gulim; font-size: 12px; color: #777;"><s:property value = "createuser" /></td>
							            <td id="content" align = "left" style="padding: 10px; font-family: '굴림',Gulim; font-size: 13px; color: #333;"><s:property value = "content" /></td>
							      </tr>
								</s:iterator>
							</table>
						</div>
						<!-- コメント入力欄 -->
						<div style="padding:10px; background-color:#fafafa; border-top: 2px solid #525eaa; border-bottom: 2px solid #525eaa; margin-top: 30px;">
							<s:textarea id="comment" type="text" name="commentDto.content" maxlength="400" style="width: 1010px; height: 78px; border: 1px solid #cecdce;
								background-color: #ffffff; resize: none; margin: 10px;"></s:textarea>
							<div style="text-align-last: right;">
								<button onclick = "onClickCommentInsert()"
									type="button" id="btn_comment_insert" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">등록</button>
							</div>
						</div>
						<div style='float: right; margin-top: 5px;'>
							<button onclick = "onClickEdit()"
								type="button" id="btn_edit" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">수정</button>
						</div>
						<div style='float: right; margin-top: 5px; margin-right: 10px'>
							<button onclick="onClickDelete()"
								type="button" id="btn_delete" style="color:#fff;border-style:solid; background-color: #666; border-radius: 4px">삭제</button>
						</div>
					</div>
				</article>
			<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
			<s:hidden id="displayType"  type="text" name="boardDto.displayType" value="edit"/>
		</form>
	</section>
</main>
<main id="container"  style="width: 1051px; margin:0 auto; padding-top:50px">
	<section>
	<div style='width:1051px; display: block'>
		<table id="boardTable"></table>
		<div id="pager"></div>
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
<div id="dialogDelete" style="display:none;">
   <p>글을 삭제하시겠습니까?</p>
</div>
</body>
</html>