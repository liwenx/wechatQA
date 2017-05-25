$(function(){

	GetArgsFromHref(sHref, sArgName);
	
	

})

function GetArgsFromHref(sHref, sArgName)
{
      var args    = sHref.split("?");
      var retval = "";
    
      if(args[0] == sHref) /*参数为空*/
      {
           return retval; /*无需做任何处理*/
      }  
      var str = args[1];
      args = str.split("&");
      for(var i = 0; i < args.length; i ++)
      {
          str = args[i];
          var arg = str.split("=");
          if(arg.length <= 1) continue;
          if(arg[0] == sArgName) retval = arg[1]; 
      }
      return retval;
}

function searchQuestion(searchContent){
	$.ajax({
		type:"GET",
		url:"http://16752730ii.iask.in/wechatQA/wechatapp/home/searchQuestion",
		data:searchContent,
		async:false,
		dataType:"json",
		timeout:2000,
		success:function(result){
			if(result.retflag==0){
				//result.data.length 取 3个
				if(result.data.length > 0){
				for(var i=0;i<(result.data.length>=2?3:result.data.length);i++){
					$(".tianjiaRe").append('<a href='+"http://16752730ii.iask.in/wechatQA/wechatapp/home/getQuestionUrl?questionid="+result.data[i].questionid+' class="weui-media-box weui-media-box_appmsg">'
						      +'<div class="weui-media-box__hd">'
						       + '<img class="weui-media-box__thumb" src='+result.data[i].headimgurl+'>'
						     +'</div>'
						      +'<div class="weui-media-box__bd">'
						       +'<h4 class="weui-media-box__title">'+result.data[i].questionTitle+'</h4>'
						        +'<p class="weui-media-box__desc">'+"已有"+result.data[i].answerNum+"人解答，"+result.data[i].pageviews+"人围观"+'</p>'
						      +'</div>'
						    +'</a>')
				}
				}else{
					$(".tianjia").append('<div><h4>暂无推荐</h4></div>')
				}
			}
			if(result.retflag==1){
			}
		},
	});
}