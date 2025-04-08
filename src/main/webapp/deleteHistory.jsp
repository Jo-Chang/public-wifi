<%@page import="service.HistoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	String historyId = request.getParameter("id");

	if (historyId != null && historyId.length() > 0) {
		HistoryService historyService = new HistoryService();	
		historyService.deleteHistory(Integer.parseInt(historyId));
	}
	
	response.sendRedirect("history.jsp");
%>