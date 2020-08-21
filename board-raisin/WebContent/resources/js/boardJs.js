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

function onClickReplySaveChk(reply){
	const lengthVlReply = 400;
	if (isEmpty(reply)) {
		alert("本文を入力してください。");
		$('#reply').focus();
		return false;
	}
	if (lengthChk(reply,lengthVlReply)) {
		alert(lengthVlReply + "字以下で入力してください。");
		$('#reply').focus();
		return false;
	}
	return true;
}

function userKeyPress(flag){
	if (window.event.keyCode == 13) {
		LoadingWithMask();
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
	LoadingWithMask();
	if(!onClickCommentSaveChk()){
		return false;
	}
	document.boardView_form.action = "../comment/commentWriteAction";
	document.boardView_form.submit();
}

function onClickVote(voteKbn){
	LoadingWithMask();
	document.boardView_form.action = "../board/voteAction?boardVO.voteKbn="+voteKbn+"";
	document.boardView_form.submit();
}

function onClickBack(action){
	const boardid = $('#boardid').val();
	location.href=action + "?boardDto.boardid=" + boardid;
}

function replyOpen(elem,replyCount) {
	if ($(elem).parent().next()[0].children[0].children[0].style.display != "block") {
		$('.reply_div').css('display', 'none');
		$(elem).parent().next()[0].children[0].children[0].style.display="block";
		$(elem).parent().next()[0].children[0].children[0].style.marginBottom="2px";
		if (replyCount == 0) {
			$(elem).parent()[0].style.borderBottom="none"
			$(elem).parent().next()[0].style.borderBottom="1px solid #ddd";
			$(elem).parent().next()[0].children[0].children[0].style.marginBottom="10px"
		}
	} else {
		$('.reply_div').css('display', 'none');
	}
}
function userKeyPressReply(elem,commentid,flag){
	if (window.event.keyCode == 13) {
		LoadingWithMask();
		event.returnValue=false;
		if (flag == 'replyInsert') {
			onClickReplyInsert(elem, commentid);
		}
	} else {
		return;
	}
}
function onClickReplyInsert(elem, commentid){
	LoadingWithMask();
	var content = $(elem).parent().children()[0].value;
	if(!onClickReplySaveChk(content)){
		return false;
	}
	content = encodeURIComponent(content);
	var url = "../reply/replyWriteAction?replyDto.commentid="+commentid+"+ &replyDto.content=" + content+""
	document.boardView_form.action = url;
	document.boardView_form.submit();
}

//function voteClick(voteKbn) {
//		$.ajax({
//			url: "../board/voteAction?boardVO.voteKbn="+voteKbn+""",
//            type: "POST",
//            data: {
//                no: ${content.board_no},
//                id: '${id}'
//            },
//		})
//}

function LoadingWithMask() {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;

    //화면에 출력할 마스크를 설정해줍니다.
    var mask       ="<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg ='';

    loadingImg +="<div id='loadingImg' style='position: absolute;left: 50%;top: 50%; transform: translate(-50%, -50%);'>";
    loadingImg +=" <img src='../resources/css/images/loading.gif' style='position: relative; display: block; margin: 0px auto; width: 50px;'/>";
    loadingImg +="</div>";

    //화면에 레이어 추가
    $('.main_layout')
        .append(mask)
        .append(loadingImg)

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth
            ,'height': maskHeight
            ,'opacity' :'0.3'
    });

    //마스크 표시
    $('#mask').show();

    //로딩중 이미지 표시
    $('#loadingImg').show();
}

function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').remove();
}

