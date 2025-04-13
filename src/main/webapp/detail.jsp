<%@page import="domain.Bookmark"%>
<%@page import="java.util.List"%>
<%@page import="service.BookmarkService"%>
<%@page import="domain.Wifi"%>
<%@page import="service.WifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>와이파이 정보 구하기</h2>
<%@ include file="includes/menu-bar.jsp" %>

<%
	String mgrNo = request.getParameter("mgrNo");

	WifiService wifiService = new WifiService();
	Wifi wifi = wifiService.getPublicWifiById(mgrNo);
	
	BookmarkService bookmarkService = new BookmarkService();
	List<Bookmark> bookmarkList = bookmarkService.getAll();
%>

<form>
	<input id="mgr-no-value" value="<%= mgrNo %>" hidden>
	<select id="bookmarks" >
		<option hidden>북마크 그룹 이름 선택</option>
		<% for (Bookmark bookmark : bookmarkList) { %>
			<option value=<%= bookmark.getId() %> >
				<%= bookmark.getName() %>
			</option>
		<% } %>
	</select>
	<button type="button"
		onclick="addBookmark(event)">
		북마크 추가하기
	</button>
</form>

<table>
	<tr>
		<th>관리번호</th>
		<td><%= wifi.getMgrNo() %></td>
	</tr>
	<tr>
		<th>자치구</th>
		<td><%= wifi.getWrdofc() %></td>
	</tr>
	<tr>
		<th>와이파이명</th>
		<td><%= wifi.getMainNm() %></td>
	</tr>
	<tr>
		<th>도로명주소</th>
		<td><%= wifi.getAdres1() %></td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td><%= wifi.getAdres2() %></td>
	</tr>
	<tr>
		<th>설치위치(층)</th>
		<td><%= wifi.getInstlFloor() %></td>
	</tr>
	<tr>
		<th>설치유형</th>
		<td><%= wifi.getInstlTy() %></td>
	</tr>
	<tr>
		<th>설치기준</th>
		<td><%= wifi.getInstlMBy() %></td>
	</tr>
	<tr>
		<th>서비스구분</th>
		<td><%= wifi.getSvcSe() %></td>
	</tr>
	<tr>
		<th>망종류</th>
		<td><%= wifi.getCmcwr() %></td>
	</tr>
	<tr>
		<th>설치년도</th>
		<td><%=wifi.getCnstcYear() %></td>
	</tr>
	<tr>
		<th>실내외구분</th>
		<td><%=wifi.getInoutDoor() %></td>
	</tr>
	<tr>
		<th>WIFI접속환경</th>
		<td><%=wifi.getRemars3() %></td>
	</tr>
	<tr>
		<th>X좌표</th>
		<td><%=wifi.getLat() %></td>
	</tr>
	<tr>
		<th>Y좌표</th>
		<td><%=wifi.getLnt() %></td>
	</tr>
	<tr>
		<th>작업일자</th>
		<td><%=wifi.getWorkDttm() %></td>
	</tr>
</table>

<script>
function addBookmark(event) {
	event.preventDefault();
	
	const mgrNoInput = document.getElementById('mgr-no-value');
	const mgrNo = mgrNoInput.value;
	
	const bmSelect = document.getElementById('bookmarks');
	const selectedBookmarkId = bmSelect
		.options[bmSelect.selectedIndex]
		.value;
	
	location.href = '/insertWifiBookmark.jsp'
			+ '?mgrNo=' + mgrNo
			+ '&bookmarkId=' + selectedBookmarkId;
}
</script>
	
<%@ include file="includes/footer.jsp" %>