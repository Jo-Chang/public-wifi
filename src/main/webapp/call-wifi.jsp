<%@page import="service.DbService"%>
<%@page import="service.ApiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	DbService dbService = new DbService();
	dbService.initWifiTable();	

	ApiService apiService = new ApiService();
	int result = apiService.callAllOpenApi();
%>
    
<%@ include file="includes/header.jsp" %>
	
<div class="container">
	<h2><%=result %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
	<a href="/">홈 으로 가기</a>
</div>
	
<%@ include file="includes/footer.jsp" %>