$(function(){
	// ボタン生成
	$("button").button();
});

function onClickSaveChk(){
	const title = $("#title").val();
	const content = $("#content").val();
	const lengthVlTitle = 40;
	const lengthVlContent = 400;

	if (isEmpty(title)) {
		alert("タイトルを入力してください。");
		return "title";
	}
	if (lengthChk(title,lengthVlTitle)) {
		alert(lengthVlTitle + "字以下で入力してください。");
		return "title";
	}

	if (isEmpty(content)) {
		alert("本文を入力してください。");
		return "content";
	}
	if (lengthChk(content,lengthVlContent)) {
		alert(lengthVlContent + "字以下で入力してください。");
		return "content";
	}
	return null;
}

function onClickCommentEditChk(){
	const comment = $("#comment").val();
	const lengthVlComment = 400;
	if (isEmpty(comment)) {
		alert("本文を入力してください。");
		return "comment";
	}
	if (lengthChk(comment,lengthVlComment)) {
		alert(lengthVlComment + "字以下で入力してください。");
		return "comment";
	}

	return null;
}

function onClickCommentSaveChk(){
	const comment = $("#comment").val();
	const lengthVlComment = 400;
	if (isEmpty(comment)) {
		alert("本文を入力してください。");
		$('#comment').focus();
		return false;
	}
	if (lengthChk(comment,lengthVlComment)) {
		alert(lengthVlComment + "字以下で入力してください。");
		$('#comment').focus();
		return false;
	}
	return true;
}

function userKeyPress(flag){
	if (window.event.keyCode == 13) {
		event.returnValue=false;
		if (flag == 'insert') {
			onClickCommentInsert();
		}
	} else {
		return;
	}
}

function onClickSave(){
	$("#dialogSave").dialog("open");
}

function onClickCancel(){
	$("#dialogCancel").dialog("open");
}

function onClickDelete(){
	$("#dialogDelete").dialog("open");
}

function onClickDeleteList(action){
	document.boardList_form.action = action;
    $("#dialogDeleteList").dialog("open");
}

function onClickCommentDeleteList(action){
	document.boardView_form.action = action;
    $("#dialogCommentDeleteList").dialog("open");
}

function isEmpty(str){
    if(typeof str == "undefined" || str == null || str == "") {
    	return true;
    } else {
    	return false;
    }
}

function lengthChk(str,length){
	if (str.length > length) {
		return true;
	} else {
		return false;
	}
}

function onClickEdit(){
	document.boardView_form.action = "../board/editForm";
	document.boardView_form.submit();
}

function onClickCommentInsert(){
	if(!onClickCommentSaveChk()){
		return false;
	}
	document.boardView_form.action = "../comment/commentWriteAction";
	document.boardView_form.submit();
}

function onClickVote(voteKbn){
	document.boardView_form.action = "../board/voteAction?boardVO.voteKbn="+voteKbn+"";
	document.boardView_form.submit();
}

function onClickBack(action){
	const boardid = $('#boardid').val();
	location.href=action + "?boardDto.boardid=" + boardid;
}

function replyOpen(elem) {
	$('.reply_div').css('display', 'none');
	$(elem).parent().next()[0].children[1].children[0].style.display="block";
	$(elem).parent().next()[0].children[1].children[0].style.marginBottom="10px";
	$(elem).parent().next()[0].style.borderTop="none";
	$(elem).parent().next()[0].style.borderBottom="1px solid #ddd";
}

