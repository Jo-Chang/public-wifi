<%@page import="service.WifiBookmarkService"%>
<%@page import="domain.WifiBookmarkDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>북마크 목록</h2>
<%@ include file="includes/menu-bar.jsp" %>

<table>
	<tr>
		<th>ID</th>
		<th>북마크 이름</th>
		<th>와이파이명</th>
		<th>등록일자</th>
		<th>비고</th>
	</tr>
<%
	WifiBookmarkService wbService = new WifiBookmarkService();
	List<WifiBookmarkDto> wbList = wbService.findAll();
	if (wbList == null || wbList.size() == 0) {
%>
	<tr class="no-data">
		<td colspan='6'>북마크된 와이파이가 없습니다.</td>
	</tr>
<% 
	} else { 
		for (int i = 0; i < wbList.size(); i++) {
			WifiBookmarkDto wbDto = wbList.get(i);
%>
	<tr>
		<td><%= wbDto.getId() %></td>
		<td><%= wbDto.getBookmarkName() %></td>
		<td><a href="/detail.jsp?mgrNo=<%= wbDto.getWifiMgrNo() %>">
				<%= wbDto.getWifiName() %>
		</a></td>
		<td><%= wbDto.getCreateTs() %></td>
		<td class="centered-data">
			<a href="/bookmark-delete.jsp?id=<%= wbDto.getId() %>" >삭제</a>
		</td>
	</tr>
<%
		} 
	}
%>
</table>

<%@ include file="includes/footer.jsp" %>