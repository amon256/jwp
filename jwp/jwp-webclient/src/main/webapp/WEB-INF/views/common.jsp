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
<link href="${ctx }/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ctx }/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx }/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${ctx }/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="${ctx }/artDialog/artDialog.js?skin=simple"></script>
<script type="text/javascript" src="${ctx }/artDialog//plugins/iframeTools.js"></script>
<script type="text/javascript" src="${ctx }/commons/common.js"></script>
<script type="text/javascript">
	var webCtx = '${ctx }';
</script>
