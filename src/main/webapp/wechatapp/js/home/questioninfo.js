$(function(){
	//展示信息
	getQuestionInfo();
})

function getQuestionInfo(){
	$.ajax({
		type:"GET",
		url:"http://16752730ii.iask.in/wechatQA/wechatapp/home/getQuestionInfo",
		async:false,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				$('#questionTitle').text(result.questioninfo.questionTitle);
				$('#questionContent').text(result.questioninfo.questionContent);
				$('#pageviews').text("浏览量："+result.questioninfo.pageviews);
				$('#answerNum').text(result.questioninfo.answerNum+"人回答");
			}
			if(result.retflag==1){
			}
		},
	});
}