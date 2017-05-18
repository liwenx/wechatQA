$(function(){
	//展示信息
	getQuestionInfo();
	
	$('#submitanswer').on('click',function(){
		submitAnswer();
		
	});
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
				
				if(result.answer.length > 0){
					for (var i =  0; i < result.answer.length; i ++){
						$(".tianjialist").append('<a href='+"http://16752730ii.iask.in/wechatQA/wechatapp/home/getAnswerUrl?answerid="+result.answer[i].answerid+' class="weui-media-box weui-media-box_appmsg">'
							      +'<div class="weui-media-box__hd">'
							       + '<img class="weui-media-box__thumb" src='+result.answer[i].headimgurl+'>'
							     +'</div>'
							      +'<div class="weui-media-box__bd">'
							       +'<h4 class="weui-media-box__title">'+result.answer[i].nickname+"的回答&nbsp&nbsp"+result.answer[i].pageviews+"人看过"+'</h4>'
							        +'<p class="weui-media-box__desc">'+result.answer[i].answerContent+'</p>'
							      +'</div>'
							    +'</a>')
					}
					
				}else{
					$(".tianjialist").append('<div><h4>暂无回答</h4></div>')
				}
			}
			if(result.retflag==1){
			}
		},
	});
}

function submitAnswer(){
	var data = {
			answerContent:$('#answertext').val()
	}
	$.ajax({
		type:"GET",
		url:"http://16752730ii.iask.in/wechatQA/wechatapp/home/submitAnswer",
		data:data,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				window.location.href="http://16752730ii.iask.in/wechatQA/wechatapp/page/home/questioninfo.html";
			}
			if(result.retflag==1){
			}
		},
	});
}