<%@page import="service.WifiService"%>
<%@page import="domain.Wifi"%>
<%@page import="java.util.List"%>
<%@page import="service.DbService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="includes/header.jsp" %>


<h2 class="fw-bold">와이파이 정보 구하기</h2>
<%@ include file="includes/menu-bar.jsp" %>

<div class="location-container">
	<form action="insertHistory.jsp" method="POST">
		<label for="lat">LAT:</label>
		<input name="lat" id="lat-input" value="0.0"/>
		,
		<label for="lnt">LNT:</label>
		<input name="lnt" id="lnt-input" value="0.0"/>
		
		<button type="button" onClick="getMyLocation()">내 위치 가져오기</button>
		<button type="submit">근처 WIFI 정보 보기</button>
	</form>
</div>

<table>
<tr>
	<th>거리(Km)</th>
	<th>관리번호</th>
	<th>자치구</th>
	<th>와이파이명</th>
	<th>도로명주소</th>
	<th>상세주소</th>
	<th>설치위치(층)</th>
	<th>설치유형</th>
	<th>설치기준</th>
	<th>서비스구분</th>
	<th>망종류</th>
	<th>설치년도</th>
	<th>실내외구분</th>
	<th>WIFI접속환경</th>
	<th>X좌표</th>
	<th>Y좌표</th>
	<th>작업일자</th>
</tr>
<%
	String lat = request.getParameter("lat");
	String lnt = request.getParameter("lnt");
	
	if (lat == null || lnt == null) {
%>
	<tr class="no-data">
		<td colspan='17'>위치 정보를 입력한 후에 조회해 주세요.</td>
	</tr>
<%
	} else {
		WifiService wifiService = new WifiService();
		List<Wifi> wifiList = wifiService.getPublicWifi(
				Double.parseDouble(lat), Double.parseDouble(lnt)
				);
		for (int i = 0; i < wifiList.size(); i++) {
			Wifi wifi = wifiList.get(i);
%>
	<tr>
		<td><%=wifi.getDistance() %></td>
		<td><%=wifi.getMgrNo() %></td>
		<td><%=wifi.getWrdofc() %></td>
		<td><%=wifi.getMainNm() %></td>
		<td><%=wifi.getAdres1() %></td>
		<td><%=wifi.getAdres2() %></td>
		<td><%=wifi.getInstlFloor() %></td>
		<td><%=wifi.getInstlTy() %></td>
		<td><%=wifi.getInstlMBy() %></td>
		<td><%=wifi.getSvcSe() %></td>
		<td><%=wifi.getCmcwr() %></td>
		<td><%=wifi.getCnstcYear() %></td>
		<td><%=wifi.getInoutDoor() %></td>
		<td><%=wifi.getRemars3() %></td>
		<td><%=wifi.getLat() %></td>
		<td><%=wifi.getLnt() %></td>
		<td><%=wifi.getWorkDttm() %></td>
	</tr>
<%
		}
	}
%>
</table>

<script>
const latInput = document.getElementById('lat-input');
const lntInput = document.getElementById('lnt-input');

function getMyLocation(event) {
	console.log('[event] 내 위치 가져오기');
	
	navigator.geolocation.getCurrentPosition((position) => {
		latInput.value = position.coords.latitude;
		lntInput.value = position.coords.longitude;
	});
}
</script>

<%@ include file="includes/footer.jsp" %>