<%@page import="service.WifiBookmarkService"%>
<%@page import="domain.WifiBookmarkDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>북마크 제거</h2>
<%@ include file="includes/menu-bar.jsp" %>

<%
	String idString = request.getParameter("id");
	int id = Integer.parseInt(idString);

	WifiBookmarkService wbService = new WifiBookmarkService();
	WifiBookmarkDto wb = wbService.findById(id);
	
	if (request.getMethod().equals("post")) {
		wbService.deleteById(id);
		%>
		<script>
			alert('북마크 그룹 정보를 삭제하였습니다.');
			location.href = "/bookmark-list.jsp";
		</script>
		<%
	}
%>

<p>북마크를 삭제하시겠습니까?</p>

<form method="post"
	action="/bookmark-delete.jsp" >
	
	<table>
		<tr>
			<th>북마크 이름</th>
			<td><%= wb.getBookmarkName() %></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td><%= wb.getWifiName() %></td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td><%= wb.getCreateTs() %></td>
		</tr>
		<tr class="centered-data">
			<td colspan="2">
				<a href="/bookmark-list.jsp">돌아가기</a>
				|
				<button type="submit">삭제</button>
			</td>
		</tr>
	</table>

</form>

<%@ include file="includes/footer.jsp" %>