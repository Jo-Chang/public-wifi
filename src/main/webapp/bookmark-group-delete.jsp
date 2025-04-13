<%@page import="service.BookmarkService"%>
<%@page import="domain.Bookmark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>북마크 그룹 삭제</h2>
<%@ include file="includes/menu-bar.jsp" %>

<%
	request.setCharacterEncoding("UTF-8");

	BookmarkService bookmarkService = new BookmarkService();
	Bookmark bookmark = null;
	Integer id = null;

	try {
		id = Integer.parseInt(request.getParameter("id"));
		bookmark = bookmarkService.getById(id);
		
		if (request.getMethod().equals("POST")) {
			if (id != null) bookmarkService.deleteById(id);
			
			%>
				<script>
					alert('북마크 그룹 정보를 삭제하였습니다.');
					location.href = "/bookmark-group.jsp";
				</script>
			<%
		}
	} catch (NumberFormatException e) {
		%>
		<script>
			alert('잘못된 접근입니다.');
			location.href = "/bookmark-group.jsp";
		</script>
		<%
	}
%>


<form method="post"
	action="/bookmark-group-delete.jsp?id=<%= bookmark.getId() %>" >
	<table>
		<tr>
			<th>북마크 이름</th>
			<td>
				<input name="name" value=<%= bookmark.getName() %> readonly />
			</td>
		</tr>
		<tr>
			<th>순서</th>
			<td>
				<input name="order" value=<%= bookmark.getBmOrder() %> readonly />
			</td>
		</tr>
		<tr><td class="centered-data" colspan="2">
			<a href="/bookmark-group.jsp">돌아가기</a>
			|
			<button type="submit">삭제</button>
		</td></tr>
	</table>
	
</form>

<%@ include file="includes/footer.jsp" %>