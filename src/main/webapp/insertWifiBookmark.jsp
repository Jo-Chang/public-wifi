<%@page import="service.WifiBookmarkService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	
	String bookmarkIdString = request.getParameter("bookmarkId");
	String mgrNo = request.getParameter("mgrNo");
	
	try {
		Integer bookmarkId = Integer.parseInt(bookmarkIdString);
		WifiBookmarkService wbService = new WifiBookmarkService();
		wbService.insert(bookmarkId, mgrNo);
	} catch (NumberFormatException e) {}

	response.sendRedirect("/bookmark-list.jsp");
%>