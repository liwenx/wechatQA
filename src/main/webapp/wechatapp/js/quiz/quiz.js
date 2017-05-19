$(function(){
	//提交事务
	$('#quizquestion').on('click',function(){
		submitQuestion();
	})
})

/**
 * 提交问题
 * @returns json
 */
function submitQuestion(){
	var data = {
			questionTitle:$('#questiontitle').val(),
			questionContent:$('#questioncontent').val(),
			questionType:$('#questiontype').val()
	}
	$.ajax({
		type:"GET",
		url:"http://16752730ii.iask.in/wechatQA/wechatapp/quiz/submitQuestion",
		data:data,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				window.location.href="http://16752730ii.iask.in/wechatQA/wechatapp/page/quiz/quizsuccess.html"
			}
			if(result.retflag==1){
			}
		},
		error:function(msg){
		}
	});
}