<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String ctx = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath();
request.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="shortcut icon" href="${ctx }/favicon.ico">
	<link href="${ctx }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx }/adminLTE/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx }/adminLTE/css/ionicons.min.css">
	<link href="${ctx }/adminLTE/css/AdminLTE.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx }/plugins/iCheck/square/blue.css">
	<script type="text/javascript" src="${ctx }/jQuery/jQuery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx }/plugins/iCheck/icheck.min.js"></script>
	<title>Join Work Platform</title>
	<script>
	  $(function () {
	    $('input').iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass: 'iradio_square-blue',
	      increaseArea: '20%' // optional
	    });
	    if($('#uname').val()){
		    $('#upassword').focus();
		}else{
			$('#uname').focus();
		}
	  });
	</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>Join Work Platform</b>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your work</p>
			<c:if test="${not empty errorMsg }">
				<p class="login-box-msg" style="color: red;">${errorMsg }</p>
			</c:if>
			<form action="${ctx }/login" method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="uname" id="uname" value="${uname }" required="required" placeholder="Account/Mobile/Email">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="upassword" id="upassword" required="required" placeholder="Password">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input type="checkbox" name="remember" value="1">Remember Me</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>