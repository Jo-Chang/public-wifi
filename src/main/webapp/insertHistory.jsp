<%@page import="service.HistoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	String lat = request.getParameter("lat");
	String lnt = request.getParameter("lnt");

	if (lat != null && lnt != null && lat.length() > 0 && lnt.length() > 0) {
		HistoryService historyService = new HistoryService();
		try {
			historyService.insertHistory(
					Double.parseDouble(lat), Double.parseDouble(lnt)
					);
		} catch (NumberFormatException e) {}
	}
	
	response.sendRedirect("/index.jsp?lat=" + lat + "&lnt=" + lnt);
%>