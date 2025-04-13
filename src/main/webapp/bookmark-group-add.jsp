<%@page import="service.BookmarkService"%>
<%@page import="domain.Bookmark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>북마크 그룹 추가</h2>
<%@ include file="includes/menu-bar.jsp" %>

<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	Integer order = null;
	try {
		order = Integer.parseInt(request.getParameter("order"));
		
		if (request.getMethod().equals("POST") && 
				!(name == null || name.equals("") || order == null)) {
			BookmarkService bookmarkService = new BookmarkService();
			bookmarkService.insert(name, order);
		}
		
		%>
		<script>
			alert('북마크 그룹 정보를 추가하였습니다.');
			location.href = "/bookmark-group.jsp";
		</script>
		<%
	} catch (NumberFormatException e) {
		if (name != null || order != null) {
		%>
		<script>
			alert('올바른 형식의 이름과 순서를 입력하세요.');
		</script>
		<%
		}
	}
	
%>


<form action="/bookmark-group-add.jsp" method="post">
	<table>
		<tr>
			<th>북마크 이름</th>
			<td>
				<input name="name" />
			</td>
		</tr>
		<tr>
			<th>순서</th>
			<td>
				<input name="order" />
			</td>
		</tr>
		<tr><td class="centered-data" colspan="2">
			<button type="submit">추가</button>
		</td></tr>
	</table>
	
</form>

<script>
	
</script>

<%@ include file="includes/footer.jsp" %>