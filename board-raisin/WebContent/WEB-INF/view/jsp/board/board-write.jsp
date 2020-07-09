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
            title: '戻る',
            buttons:{
                "キャンセル":function(){
                    $(this).dialog("close");
                },"確認":function(){
                	javascript:location.href='../board/index';
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
                	document.boardWrite_form.submit();
                }
            }
        });
        $(".ui-dialog-buttonpane button:contains('確認')").attr('style','border-color:"#3c4790"; background: #4a57a8;color: #fff');
    });
</script>
</head>
<body>
<form name="boardWrite_form" method="post" action="../board/writeAction">
	<main id="container"  style="width: 1051px; margin:0 auto;">
		<section>
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
						<header>
							<div class="header_div">
								<h3>
									<s:if test="boardVO.displayType == 'edit' ">
										<label class="title_subject" style="height: 30px; text-align-last: center; font-size: smaller;">記事編集</label>
						            </s:if>
						            <s:else>
						            	<label class="title_subject" style="height: 50px;text-align-last: center; font-size: smaller;border-bottom: 1px solid #ded7d79c !important;">記事登録</label>
						            </s:else>
								</h3>
								<s:if test="boardVO.displayType == 'edit' ">
									<div class="writer">
										<div class="writer_lf">
											<span>
												<s:label name="boardDto.createuser" />
											</span>
											<span class="writer_dt">
												<s:label name="boardDto.createdt" />
											</span>
										</div>
									</div>
								</s:if>
							</div>
						</header>
						<s:if test="boardVO.displayType == 'edit' ">
							<div class="content_div" style="padding-top: 10px; border-bottom: 1px solid #ded7d79c !important;">
								<div>
									<div>
										<s:textfield id="title"  type="text" name="boardDto.title" maxlength="40"
											style="width: 1050px; height: 33px; padding: 0 12px;
											border: 1px solid #cecdce; color: #333;" placeholder='タイトルを入力してください。'/>
									</div>
									<div style="padding-top: 10px;">
										<s:textarea id="content"  name="boardDto.content"
											style="width: 1050px; height: 450px; padding: 0 12px;
											border: 1px solid #cecdce; color: #333; resize: none;" placeholder='本文を入力してください。'/>
									</div>
								</div>
							</div>
						</s:if>
						<s:else>
							<div class="content_div" style="border-bottom: 1px solid #ded7d79c !important;">
								<div>
									<div>
										<s:textfield id="title"  type="text" name="boardDto.title" maxlength="40"
											style="width: 1050px; height: 33px; padding: 0 12px;
											border: 1px solid #cecdce; color: #333;" placeholder='タイトルを入力してください。'/>
									</div>
									<div style="padding-top: 10px;">
										<s:textarea id="content"  name="boardDto.content"
											style="width: 1050px; height: 450px; padding: 0 12px;
											border: 1px solid #cecdce; color: #333; resize: none;" placeholder='本文を入力してください。'/>
									</div>
								</div>
							</div>
						</s:else>
							<div style='float:left; margin-top: 5px;'>
								<button type="button" onclick="onClickCancel()"
									id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">記事一覧へ</button>
							</div>
							<div style='float: right; margin-top: 5px;'>
								<s:if test="boardVO.displayType == 'edit' ">
									<button onsubmit="" onclick="return onClickSaveChk()"
									id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">編集確定</button>
					            </s:if>
					            <s:else>
					            	<button onsubmit="" onclick="return onClickSaveChk()"
									id="btn_save" style="color:#fff;border-style:solid; background-color: #3c4790; border-radius: 4px">記事投稿</button>
					            </s:else>
							</div>
				</article>
				<s:if test="boardVO.errMessage != null">
			       <div class="text-danger" style="margin-top: 10px;">
			            <h6 class="col-4" style="margin:auto">
			                <s:property value="boardVO.errMessage"/>
						</h6>
			       </div>
		       </s:if>
				<s:hidden id="boardid"  type="text" name="boardDto.boardid" />
				<s:hidden id="displayType"  type="text" name="boardVO.displayType"/>
	</section>
	</main>
</form>
</body>

<div id="dialogCancel" style="display:none;">
   <p>記事一覧に戻りますか。</p>
</div>
<div id="dialogSave" style="display:none;">
   <p>保存しますか?</p>
</div>
</html>