$(function(){
	// ボタン生成
	$("button").button();
});

function onClickSaveChk(){
	const title = $("#title").val();
	const content = $("#content").val();
	if (isEmpty(title)) {
		alert("제목을 입력해주세요");
		return false;
	}
	if (isEmpty(content)) {
		alert("내용을 입력해주세요");
		return false;
	}
	onClickSave();
	return false;
}

function onClickCommentSaveChk(){
	const comment = $("#comment").val();
	if (isEmpty(comment)) {
		alert("내용을 입력해주세요");
		return false;
	} else {
		return true;
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

function isEmpty(str){
    if(typeof str == "undefined" || str == null || str == "") {
    	return true;
    } else {
    	return false;
    }
}

function onClickEdit(){
	document.boardView_form.action = "../board/editAction	";
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
	document.boardView_form.action = "../board/voteAction?voteKbn="+voteKbn+"";
	document.boardView_form.submit();
}
