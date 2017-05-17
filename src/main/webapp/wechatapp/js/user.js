window.onload = function(){
	//跳转
	$('#userquestion').on('click',function(){
		window.location.href=getUrl()+"/wechatapp/page/41.html";
	})
	getUserInfo();
	
}

/**
 * 获取当前登录用户openid
 * @returns {String}
 */
function getOpenId(){
	var userid="";
	$.ajax({
		type:"GET",
		url:getUrl()+"/wechatapp/getSession",
		async:false,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				userid = result.userid;
			}
			if(result.retflag==1){
				window.location.href=getUrl()+"/wechatapp/index";
			}
		},
		error:function(msg){
		}
	});
	return userid;
}

function getUserInfo(){
	var openid = getOpenId();
	var data = {
			openid:openid
	}
	$.ajax({
		type:"GET",
		url:getUrl()+"/wechatapp/getUserInfo",
		dataType:"json",
		data:data,
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				$('#username').text(result.username);
				$('#userimg').attr("src",getUrl()+"/wechatapp/resources/userimg/"+openid+".jpg");
			}
			if(result.retflag==1){
			}
		},
		error:function(msg){
		}
	});
}

var url = "http://16752730ii.iask.in/wechatQA";
var getUrl = function(){
	return url;
}
