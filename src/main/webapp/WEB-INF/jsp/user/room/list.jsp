<%@page import="egovframework.com.reservation.room.model.RoomVO"%>
<%@page import="egovframework.com.reservation.company.model.CompanyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<c:set var="title" value=""/>
	<%-- <c:forEach begin="1" end="${gubun}" varStatus="floor">
		
	</c:forEach> --%>
	<c:choose>
		<c:when test="${gubun eq 1}">
			<c:set var="title" value="FIRST FLOOR ROOM"/>
		</c:when>
		<c:when test="${gubun eq 2}">
			<c:set var="title" value="SECOND FLOOR ROOM"/>
		</c:when>
		<c:when test="${gubun eq 3}">
			<c:set var="title" value="THIRD FLOOR ROOM"/>
		</c:when>
		<c:otherwise>
			<c:set var="title" value="FOURTH FLOOR ROOM"/>
		</c:otherwise>
	</c:choose>
	<jsp:include page="/WEB-INF/jsp/user/template/header.jsp" flush="true">
		<jsp:param value="ROOM ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true">
			<jsp:param value="${gubun}" name="gubun"/>
		</jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header form-group">${title} LIST</h2>
			<div class="form-inline text-center form-group">
				<div class="form-group col-sm6">	
					<input type="date" class="form-control" name="startDay" id="startDay" value="${searchContent}">
					<input type="date" class="form-control" name="endDay" id="endDay" value="${searchContent}">
				</div>	
				<div class="form-group col-sm1">	
					<button type="button" class="btn btn-default btn-primary" id="btnSearch">검색</button>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">상호명</th>
							<th class="text-center">방 이름</th>
							<th class="text-center">체크인 시간</th>
							<th class="text-center">체크아웃 시간</th>
							<th class="text-center">기준 인원(최대 인원)</th>
							<th class="text-center">기본 요금</th>
							<th class="text-center">선택</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${roomList != null }">
						<c:forEach var="item" items="${roomList}" varStatus="status">
						<tr>
							<td class="text-center">${status.count}</td>
							<td class="text-center">${item.companyVO.companyName}</td>
							<td class="text-center">
								<input type="hidden" value="${item.roomUUID}">
								<a href="#" onclick="fn_view('${item.roomUUID}'); return false;">${item.roomName}</a>
							</td>
							<td class="text-center">
								<c:set var="inTime" value="${item.roomCheckInTime}"/>
								${fn:substring(inTime,0,5)}
							</td>
							<td class="text-center">
								<c:set var="outTime" value="${item.roomCheckOutTime}"/>
								${fn:substring(outTime,0,5)}
							</td>
							<td class="text-center">${item.roomStandardPeople}명(${item.roomMaxPeople}명)</td>
							<td class="text-center">${item.roomPriceStandard}</td>
	              			<td class="text-center">
		              			<button type="button" class="btn btn-primary" onclick="fn_see('${item.roomUUID}')">객실보기</button>
		              			<button type="button" class="btn btn-danger" onclick="fn_reservation('${item.roomUUID}')">예약하기</button>
		              		</td>
						</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#btnSearch").click(function(e){
				e.preventDefault();
				fn_search();
			});
		})
		var gubun = '${gubun}';
		
		function fn_search(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/list.do");
			comSubmit.addParam("startDay",$("#startDay").val());
			comSubmit.addParam("endDay",$("#endDay").val());
			comSubmit.submit();
		}
		
		function fn_see(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/view.do");
			comSubmit.addParam("gubun",gubun);
			comSubmit.addParam("mode",'view');
			comSubmit.addParam("roomUUID",uuid);
			comSubmit.submit();
		}
		
		function fn_reservation(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/reservation/register.do");
			comSubmit.addParam("gubun",3);
			comSubmit.addParam("roomUUID",uuid);
			comSubmit.submit();
		}
	</script>		
</body>
</html>