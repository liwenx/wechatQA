;(function($) {
	$("#ajaxForm").on("submit", function(event) {
		event.preventDefault();
		var data = $(this).serialize();
		$.ajax({
			url: '/mobile/admin/addNewGroup',
			type: 'POST',
			dataType: 'json',
			data: data
			/*
			data: {
				'groupname': $(this).find('#groupname').val(),
				'public': $(this).find('#public').val(),
				'maxusers': $(this).find('#maxusers').val(),
				'approval': $(this).find('#approval').val(),
				'owner': $(this).find('#owner').val(),
				'members': $(this).find('#members').val()
			},
			*/
		})
		.done(function(respond) {
			console.log(respond);
			if(respond.status == 0) {
				$('#ajaxForm').prepend('<div class="alert alert-success" role="alert">提交成功！</div>');
			} else {
				$('#ajaxForm').prepend('<div class="alert alert-danger" role="alert">提交错误！</div>');
			}
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});
})(jQuery);