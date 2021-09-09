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
	<jsp:include page="/WEB-INF/jsp/mngr/template/header.jsp" flush="true">
		<jsp:param value="ROOM ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/mngr/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/jsp/mngr/template/left.jsp" flush="true">
			<jsp:param value="${gubun}" name="gubun"/>
		</jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">${title} LIST</h2>
			<div class="form-inline text-right">
				<div class="form-group col-sm2">
					<select class="form-control" name="companyUUID" id="companyUUID">
						<c:forEach var="list" items="${companyList}">
						<option value="${list.companyUUID}" <c:if test="${companyUUID eq list.companyUUID}">selected</c:if>>${list.companyName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group col-sm6">	
					<input type="text" class="form-control" name="searchContent" id="searchContent" value="${searchContent}" />
				</div>	
				<div class="form-group col-sm1">	
					<button type="button" class="btn btn-default btn-primary" id="btnSearch">검색</button>
				</div>
				<div class="form-group col-sm1">	
					<button type="button" class="btn btn-default" id="btnRegister">객실 등록</button>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">상호명</th>
							<th class="text-center">방 번호</th>
							<th class="text-center">방 이름</th>
							<th class="text-center">체크인 시간</th>
							<th class="text-center">체크아웃 시간</th>
							<th class="text-center">기준 인원</th>
							<th class="text-center">최대 인원</th>
							<th class="text-center">기본 요금</th>
							<th class="text-center">관리</th>
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
								<a href="#" onclick="fn_view('${item.roomUUID}'); return false;">${item.roomNumber}</a>
							</td>
							<td class="text-center">${item.roomName}</td>
							<td class="text-center">
								<c:set var="inTime" value="${item.roomCheckInTime}"/>
								${fn:substring(inTime,0,5)}
							</td>
							<td class="text-center">
								<c:set var="outTime" value="${item.roomCheckOutTime}"/>
								${fn:substring(outTime,0,5)}
							</td>
							<td class="text-center">${item.roomStandardPeople}</td>
							<td class="text-center">${item.roomMaxPeople}</td>
							<td class="text-center">${item.roomPriceStandard}</td>
	              			<td class="text-center">
		              			<button type="button" class="btn btn-primary" onclick="fn_update('${item.roomUUID}')">수정</button>
		              			<button type="button" class="btn btn-danger" onclick="fn_delete('${item.roomUUID}')">삭제</button>
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
			$("#btnRegister").on("click",function(e){
				e.preventDefault();
				fn_write();
			});
		});
		var gubun = '${gubun}';
		
		function fn_search(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/room/list.do");
			comSubmit.addParam("companyUUID",$("#companyUUID").val());
			comSubmit.addParam("searchContent",'${searchContent}');
			comSubmit.submit();
		}
		
		function fn_write(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/room/register.do");
			comSubmit.addParam("gubun",gubun);
			comSubmit.addParam("mode",'write');
			comSubmit.addParam("companyUUID",'${companyUUID}');
			comSubmit.submit();
		}
		
		function fn_update(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/room/register.do");
			comSubmit.addParam("gubun",gubun);
			comSubmit.addParam("mode",'update');
			comSubmit.addParam("roomUUID",uuid);
			comSubmit.submit();
		}
		
		function fn_delete(uuid){
			if(confirm("카테고리를 삭제하시겠습니까?")){
				var comSubmit = new ComSubmit();
				comSubmit.setUrl("/mngr/room/delete.do");
				comSubmit.addParam("roomUUID",uuid);
				comSubmit.addParam("gubun",gubun);
				comSubmit.submit();
			}else return false;
		}
	</script>		
</body>
</html>