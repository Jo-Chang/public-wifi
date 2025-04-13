<%@page import="service.BookmarkService"%>
<%@page import="domain.Bookmark"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>북마크 그룹</h2>
<%@ include file="includes/menu-bar.jsp" %>
<button type="button" 
	onclick="location.href='/bookmark-group-add.jsp'"
	class="bookmark-group-add-btn">
	북마크 그룹 이름 추가
</button>

<table>
	<tr>
		<th>ID</th>
		<th>북마크 이름</th>
		<th>순서</th>
		<th>등록일자</th>
		<th>수정일자</th>
		<th>비고</th>
	</tr>
<%
	BookmarkService bookmarkService = new BookmarkService();
	List<Bookmark> bookmarkList = bookmarkService.getAll();
	if (bookmarkList == null || bookmarkList.size() == 0) {
%>
	<tr class="no-data">
		<td colspan='6'>등록된 북마크가 없습니다.</td>
	</tr>
<% 
	} else { 
		for (int i = 0; i < bookmarkList.size(); i++) {
			Bookmark bookmark = bookmarkList.get(i);
%>
	<tr>
		<td><%= bookmark.getId() %></td>
		<td><%= bookmark.getName() %></td>
		<td><%= bookmark.getBmOrder() %></td>
		<td><%= bookmark.getCreateTs() %></td>
		<td>
		<% if (bookmark.getUpdateTs() != null) { %>
			<%= bookmark.getUpdateTs() %>
		<% } %>
		</td>
		<td class="centered-data">
			<a href="bookmark-group-edit.jsp?id=<%= bookmark.getId() %>" >수정</a>
			<a href="bookmark-group-delete.jsp?id=<%= bookmark.getId() %>" >삭제</a>
		</td>
	</tr>
<%
		} 
	}
%>
</table>

<%@ include file="includes/footer.jsp" %>