<%@page import="service.HistoryService"%>
<%@page import="java.util.List"%>
<%@page import="domain.History"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="includes/header.jsp" %>
	
<h2>위치 히스토리 목록</h2>
<%@ include file="includes/menu-bar.jsp" %>

<table>
	<tr>
		<th>ID</th>
		<th>X좌표</th>
		<th>Y좌표</th>
		<th>조회일자</th>
		<th>비고</th>
	</tr>
<%
	HistoryService historyService = new HistoryService();
	List<History> historyList = historyService.getAllHistory();
	int id = 0;
	if (historyList == null || historyList.size() == 0) {
%>
	<tr class="no-data">
		<td colspan='5'>위치 정보 기록이 없습니다.</td>
	</tr>
<% 
	} else { 
		for (int i = 0; i < historyList.size(); i++) {
			History history = historyList.get(i);
%>
	<tr>
		<td><%=history.getId() %></td>
		<td><%=history.getLat() %></td>
		<td><%=history.getLnt() %></td>
		<td><%=history.getViewTs() %></td>
		<td style="text-align: center;">
			<button onClick="confirmDelete(<%=history.getId()%>)">삭제</button>
		</td>
	</tr>
<%
		} 
	}
%>
</table>

<script>
function confirmDelete(id) {
	if (confirm("정말 삭제하시겠습니까?")) {
		fetch('deleteHistory.jsp', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: 'id=' + encodeURIComponent(id)
		})
		.then(response => {
			if (response.redirected) {
				location.href = response.url; 
			} else {
				alert('삭제 실패!');
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('에러 발생!');
		});
	} 
}
</script>
	
<%@ include file="includes/footer.jsp" %>