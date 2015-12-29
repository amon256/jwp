<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common.jsp"></jsp:include>
	<script type="text/javascript" src="${ctx }/plugins/jQueryForm/jquery.form.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#dataForm').ajaxForm({
				success: function(res){
					if(res.status == 'success'){
						art.dialog.close();
					}else if(res.status == 'fail'){
						if(res.validateErrors && res.validateErrors.length > 0){
							for(var i = 0; i < res.validateErrors.length; i++){
								var err = res.validateErrors[i];
								var label = $('label[for='+err.field+']');
								if(label.length != 0){
									if($('span[for='+err.field+']').length == 0){
										$('<span for="'+err.field+'" style="color:red;"/>').text(err.message).insertAfter(label);
									}else{
										$('span[for='+err.field+']').text(err.message);
									}
								}
							}
						}else{
							art.dialog.alert(res.msg);
						}
					}else{
						art.dialog.alert(res);
					}
				}
			});
			$('#exitBtn').click(function(){
				art.dialog.close();
			});
		});
	</script>
</head>
<body style="min-height: 320px;min-width: 400px;padding: 15px;">
	<form id="dataForm" action="${ctx }/adminuser/saveAdd" method="post" role="form">
		<div class="form-group">
		  <label for="account" class="control-label">账号：</label>
		  	<input type="text" class="form-control" id="account" name="account" placeholder="账号" required="required">
		</div>
		<div class="form-group">
		  <label for="password" class="control-label">密码：</label>
		  	<input type="password" class="form-control" id="password" name="password" placeholder="初始密码" required="required">
		</div>
		<div class="form-group">
		  <label for="name" class="control-label">昵称：</label>
		  	<input type="text" class="form-control" id="name" name="name" placeholder="昵称" required="required">
		</div>
		<div class="form-group">
		  <label for="mobile" class="control-label">电话：</label>
		  	<input type="text" class="form-control" id="mobile" name="mobile" placeholder="电话号码">
		</div>
		<div class="form-group">
		  <label for="email" class="control-label">邮箱：</label>
		  	<input type="email" class="form-control" id="email" name="email" placeholder="电子邮箱">
		</div>
		<div class="form-group">
			  <button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-ok"></i>保存</button>
			<button id="exitBtn" type="button" class="btn btn-default"><i class="glyphicon glyphicon-repeat"></i>取消</button>
		</div>
	</form>
</body>
</html>