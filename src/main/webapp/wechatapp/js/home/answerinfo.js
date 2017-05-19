$(function(){
	//展示信息
	getAnswerInfo();
})

function getAnswerInfo(){
	$.ajax({
		type:"GET",
		url:"http://16752730ii.iask.in/wechatQA/wechatapp/home/getAnswerInfo",
		async:false,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				$('#questionTitle').text(result.answerinfo.questionTitle);
				$('#answerContent').text(result.answerinfo.answerContent);
				$('#pageviews').text("浏览量："+result.answerinfo.pageviews);
				$('#nickname').text(result.answerinfo.nickname+"的回答:");
			}
			if(result.retflag==1){
			}
		},
	});
}