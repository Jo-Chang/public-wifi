<%@page import="service.ApiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ApiService apiService = new ApiService();

	int result = apiService.callAllOpenApi();
	
	// int result = apiService.callOpenApi(1, 20); // Test Code
	// apiService.close(); // Test Code
%>
    
<%@ include file="includes/header.jsp" %>
	
<div class="container">
	<h2><%=result %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
	<a href="/">홈 으로 가기</a>
</div>
	
<%@ include file="includes/footer.jsp" %>