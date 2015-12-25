<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String port = request.getServerPort()==80?"":":"+request.getServerPort();
String ctx = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath();
request.setAttribute("ctx", ctx);
%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${ctx }/favicon.ico">
<link href="${ctx }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/AdminLTE.min.css" rel="stylesheet">
<link href="${ctx }/adminLTE/css/skins/skin-blue.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/adminLTE/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx }/adminLTE/css/ionicons.min.css">
<script type="text/javascript" src="${ctx }/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/adminLTE/js/app.min.js"></script>
<script type="text/javascript">
	var ctx = '${ctx }';
</script>
