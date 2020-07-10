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
        $("#dialogCancel").dialog({
            autoOpen:false,
            resizable:false,
            title: '戻る',
            buttons:{
                "キャンセル":function(){
                    $(this).dialog("close");
                },"確認":function(){
                	const boardid = $('#boardid').val();
                	location.href='../board/viewForm' + "?boardDto.boardid=" + boardid;
                }
            }
        });

        $("#dialogSave").dialog({
            autoOpen:false,
            resizable:false,
            title: '保存',
            buttons:{
                "キャンセル":function(){
                    $(this).dialog("close");
                },"確認":function(){
                	const cheResult = onClickCommentEditChk();
                	if(cheResult == null || cheResult == "" ){
	                	document.commentView_form.submit();
                	} else {
                		$(this).dialog("close");
                		$('#'+ cheResult + '').focus();
                	}
                }
            }
        });
        $(".ui-dialog-buttonpane button:contains('確認')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');
    });
</script>
</head>
<body >
<main class="main_layout">
	<section>
		<form name="commentView_form"method="post" action="../comment/commentEditAction">
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
									<label class="title_subject" id="title" style="text-align-last: center; font-size: smaller;">コメント編集</label>
								</h3>
								<div class="writer">
									<div class="writer_lf">
										<span>
											<s:label name="commentDto.createuser" />
										</span>
										<span class="writer_dt">
											<s:label name="commentDto.createdt" />
										</span>
									</div>
								</div>
							</div>
						</header>
						<div class="content_div" style="border-bottom: 1px solid #ded7d79c !important;">
							<s:textarea class="cmt_txta_edi" id="comment" type="text" name="commentDto.content" maxlength="400" onkeypress="userKeyPress('edit')"/>
						</div>
						<div style='float:left; margin-top: 5px;'>
							<button type="button" onclick="onClickCancel()"
								id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">戻る</button>
						</div>
						<div style='float:right; margin-top: 5px;'>
							<button type="button" onclick='$("#dialogSave").dialog("open");'
								id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">確定</button>
						</div>
					</div>
				</article>
				<!-- 現在掲示板番号 -->
				<s:hidden id="boardid"  type="text" name="commentDto.boardid" />
				<!-- 現在掲示板のコメント番号 -->
				<s:hidden id="commentid"  type="text" name="commentDto.commentid" />
		</form>
	</section>
</main>
</body>
<div id="dialogCancel" style="display:none;">
   <p>記事詳細に戻りますか。</p>
</div>
<div id="dialogSave" style="display:none;">
   <p>保存しますか?</p>
</div>
</html>