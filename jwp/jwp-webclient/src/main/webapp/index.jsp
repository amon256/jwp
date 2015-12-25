<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();
	request.getRequestDispatcher("/index").forward(request, response);
%>