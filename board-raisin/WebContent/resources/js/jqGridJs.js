$(window.document).ready(function(){
	// jqgrid初期設定
	const searchResultColNames =  ['No', '제목', '글쓴이', '작성일', '갱신자', '갱신일', '상세', '편집', '삭제', '조회'];
	const searchResultColModel =
	                [
	                	{name:'boardid', align:'center', width:'30'},
		                {name:'title', align:'left', width:'500'},
		                {name:'createuser', align:'center',width:'80'},
		                {name:'createdt', align:'center', width:'60'},
		                {name:'modiuser', align:'center', width:'80'},
		                {name:'modidt', align:'center', width:'60'},
		                {name:'action1', align:'center', width:'60'},
		                {name:'action2', align:'center', width:'60'},
		                {name:'action3', align:'center', width:'60'},
		                {name:'boardcount', align:'center', width:'60'}
	                ];
	$("#boardTable").jqGrid({
		datatype: 'local',
		data: getBoardData(),
		colNames: searchResultColNames,
		colModel: searchResultColModel,
		shrinkToFit:false,
		restoreAfterSelect: false,
		rowNum:10,
		rowList:[10,20,30],
		pagerpos : 'center',
		viewrecords: false,
		shrinkToFit : true,
		sortable : false,
		width:1051,
		height: "auto",
		caption: '게시판',
		cmTemplate: { sortable: false },
		shrinkToFit: false,
		beforeSelectRow: function(rowid, e) {
		    return false;
		}
	});
	$(".ui-jqgrid-titlebar").hide();

	// actionHeader設定
	actionHeader();
});

function getBoardData() {
	var boardDataTbl = $('#boardDataTbl tr');
	var rowData = new Array();
	var dataArray = new Array();

	var boardidVl = new String();
	var titleVl = new String();
	var createuserVl = new String();
	var createdtVl = new String();
	var modiuserVl = new String();
	var modidtVl = new String();
	var commentCountVl = new String();
	var action1Vl = new String();
	var action2Vl = new String();
	var action3Vl = new String();
	var boardcountVl = new String();

	for(var i=0;i<boardDataTbl.length;i++) {
		if (boardDataTbl[i].children.length != 1 ) {
			boardidVl = boardDataTbl[i].children.boardid.innerHTML;
			titleVl = boardDataTbl[i].children.title.innerHTML;
			commentCountVl = boardDataTbl[i].children.commentCount.innerHTML;
			if (commentCountVl != 0) {
				commentCountVl = commentCountVl.replace("href","class='comment_num' href");
				titleVl = titleVl + commentCountVl;
			}
			createuserVl = boardDataTbl[i].children.createuser.innerHTML;

			var createdtFm = creatDateFomat(boardDataTbl[i]);
			boardDataTbl[i].children.createdt.innerText = createdtFm;
			createdtVl = boardDataTbl[i].children.createdt.innerHTML;

			modiuserVl = boardDataTbl[i].children.modiuser.innerHTML;

			var modidtFm = modiDateFomat(boardDataTbl[i]);
			boardDataTbl[i].children.modidt.innerText = modidtFm;
			modidtVl = boardDataTbl[i].children.modidt.innerHTML;

			action1Vl = boardDataTbl[i].children.action1.innerHTML;
			action2Vl = boardDataTbl[i].children.action2.innerHTML;
			action3Vl = boardDataTbl[i].children.action3.innerHTML;

			boardcountVl = boardDataTbl[i].children.boardcount.innerHTML;
			rowData = {
							boardid: boardidVl,
							title : titleVl,
							createuser : createuserVl,
							createdt : createdtVl,
							modiuser : modiuserVl,
							modidt : modidtVl,
							action1 : action1Vl,
							action2 : action2Vl,
							action3 : action3Vl,
							boardcount : boardcountVl};
			dataArray.push(rowData);
		}
	}
	return dataArray;
}

function actionHeader(){
	// actionHeader設定
	var newWidth = $("#boardTable_action1").width() +$("#boardTable_action2").width()+ $("#boardTable_action1").width();
	$("#boardTable").jqGrid("setLabel", "action1", "엑션", "", {
	        style: "width: " + "180px;",
	        colspan: "3"
	    });
	$("#boardTable").jqGrid("setLabel", "action2", "", "", {style: "display: none"});
	$("#boardTable").jqGrid("setLabel", "action3", "", "", {style: "display: none"});
}

function creatDateFomat(boardDataTbl){
	const date = new Date();
	var sysDateFm = moment(date).format('YYYYMMDD');
	var createdtFm = moment(boardDataTbl.children.createdt.innerText).format('YYYYMMDD');
	if (sysDateFm == createdtFm) {
		createdtFm = moment(boardDataTbl.children.createdt.innerText).format('HH:mm');
	} else {
		createdtFm = moment(boardDataTbl.children.createdt.innerText).format('MM.DD');
	}
	return createdtFm;
}

function modiDateFomat(boardDataTbl){
	const date = new Date();
	var sysDateFm = moment(date).format('YYYYMMDD');
	var modidtFm = moment(boardDataTbl.children.modidt.innerText).format('YYYYMMDD');
	if (sysDateFm == modidtFm) {
		modidtFm = moment(boardDataTbl.children.modidt.innerText).format('HH:mm');
	} else {
		modidtFm = moment(boardDataTbl.children.modidt.innerText).format('MM.DD');
	}
	return modidtFm;
}
