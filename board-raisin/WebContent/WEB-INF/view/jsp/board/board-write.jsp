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
<script src="../resources/js/boardJs.js" type="text/javascript"></script>
<script>
    $(function(){
        $("#dialogCancel").dialog({
            autoOpen:false,
            resizable:false,
            title: '글쓰기',
            buttons:{
                "취소":function(){
                    $(this).dialog("close");
                },"확인":function(){
                	javascript:location.href='../board/index';
                }
            }
        });

        $("#dialogSave").dialog({
            autoOpen:false,
            resizable:false,
            title: '글쓰기',
            buttons:{
                "취소":function(){
                    $(this).dialog("close");
                },"확인":function(){
                	document.boardWrite_form.submit();
                }
            }
        });
        $(".ui-dialog-buttonpane button:contains('확인')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');
    });
</script>
</head>
<body>
<form name="boardWrite_form" method="post" action="../board/writeAction">
	<main id="container"  style="width: 1051px; margin:0 auto;">
		<section style="padding-bottom: 30px;">
		<h2 style="color: #3c4790; font-size: 24px; font-family: 'Nanum Gothic', sans-serif; letter-spacing: -1px; margin-top: 15px; font-weight: bolder;">
			<a style="text-decoration: none; color: #3c4790;" href="../board/index">게시판 만들기
			</a>
		</h2>
		<hr align="left" style="background-color: #3c4790; height:1px; width: 1051px">
		<article id="write_wrap" style="border: 2px solid #d5d5d5">
			<div style="height:700px">
				<div style="padding: 30px;">
					<s:textfield id="title"  type="text" name="boardDto.title" maxlength="40"
						style="width: 701px; height: 33px; padding: 0 12px;
						border: 1px solid #cecdce; color: #333;" placeholder='제목을 입력해 주세요.'/>
				</div>
				<div style="margin-left: 30px;">
					<s:textarea id="content"  name="boardDto.content"
						style="width: 987px; height: 450px; padding: 0 12px;
						border: 1px solid #cecdce; color: #333; resize: none;" placeholder='본문을 입력해 주세요.'/>
				</div>
				<div style='float: right; margin-top: 5px; margin-right: 30px'>
					<button onsubmit="" onclick="return onClickSaveChk()"
						id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">등록</button>
				</div>

				<div style='float: right; margin-top: 5px; margin-right: 10px'>
					<button onclick="onClickCancel()"
						type="button" id="btn_cancel" style="color:#fff;border-style:solid; background-color: #666; border-radius: 4px">취소</button>
				</div>
			</div>
		</article>
		</section>
	</main>
	<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
	<s:hidden id="displayType"  type="text" name="boardVO.displayType"/>
</form>
</body>

<div id="dialogCancel" style="display:none;">
   <p>글 작성을 그만두시겠습니까?</p>
</div>
<div id="dialogSave" style="display:none;">
   <p>글을 등록하시겠습니까?</p>
</div>
</html>