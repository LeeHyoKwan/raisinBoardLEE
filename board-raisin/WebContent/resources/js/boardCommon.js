$(function(){
	// ボタン生成
	$("button").button();
});
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

