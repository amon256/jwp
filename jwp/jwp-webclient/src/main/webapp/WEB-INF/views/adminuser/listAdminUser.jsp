<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common.jsp"></jsp:include>
</head>
<script type="text/javascript">
	$(function(){
		$('#adminUserDataTable').bootstrapTable({
			url: webCtx+"/adminuser/listData",
			toolbar: '#toolbar',
			columns: [[
				{title: "序号", checkbox: true, align: "center", width: 40},
				{title: "账号", field: "account", align: "left", width: 80, sortable: true},
				{title: "昵称", field: "name", align: "left", width: 80, sortable: true},
				{title: "手机", field: "mobile", align: "center", width: 120, sortable: true},
				{title: "邮箱", field: "email", align: "left", width: 120, sortable: true},
				{title: "状态", field: "status", align: "center", width: 60, sortable: true},
				{title: "上次登录时间", field: "lastLoginTime", align: "center", width: 100, sortable: true},
				{title: "", field: "operation", align: "center", width: 80, formatter: function(){
					return "删除咯";
				}}
			]]
		});
		$('#addData').click(function(){
			art.dialog.open(webCtx+"/adminuser/add",{
				title: "新建账户",
				lock: true,
				opacity: .3,
				close: function(){
					$('#adminUserDataTable').bootstrapTable('refresh');
				}
			});
		});
	});
</script>
<body>
	<div class="container">
		<div id="toolbar">
	        <button id="addData" class="btn btn-info">
	            <i class="glyphicon glyphicon-plus"></i>新建
	        </button>
	    </div>
		<table id="adminUserDataTable" />
	</div>
</body>
</html>