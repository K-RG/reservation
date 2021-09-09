<%@page import="egovframework.com.reservation.room.model.RoomVO"%>
<%@page import="egovframework.com.reservation.company.model.CompanyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/user/template/header.jsp" flush="true">
		<jsp:param value="ROOM ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true">
			<jsp:param value="${gubun}" name="gubun"/>
		</jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header form-group">RESERVATION POSSIBLE LIST</h2>
			<div class="form-inline text-right form-group">
				<div class="row col-md-1">
				    <button type="button" class="btn btn-outline-info" >전체 사업장 객실보기</button>	
				</div>
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
		              			<button type="button" class="btn btn-primary" onclick="fn_see('${item.roomUUID}','${item.companyUUID}')">객실보기</button>
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
		var roomReservationUUID = '${roomReservationUUID}';	
		var company = '${companyBank}';
		var depositor = '${depositor}';
		
		if(roomReservationUUID.trim() != "" && company.trim() != "" ){
			Swal.fire({
                icon: 'success',
                title: '예약 완료',
                html: '<table><tr><th>예약 번호</th><td colspan="3">'+roomReservationUUID+"</td></tr>"
                		+"<tr><th>은행명</th><td>${fn:split(companyBank,'-')[0]}</td><th>예금주</th><td>${fn:split(companyBank,'-')[2]}</td></tr>"
                		+"<tr><th>계좌번호</th><td> ${fn:split(companyBank,'-')[1]}</td><th>입금자명</th><td>"+depositor+"</td></tr>"
                		+"</table>"
            })
		}else if(roomReservationUUID.trim() != "" || companyBank.trim() != "" ){
			Swal.fire({
                icon: 'success',
                title: '예약 완료',
                text: '예약 번호 : '+roomReservationUUID
            })
		}
		
		
		var gubun = '${gubun}';
		function fn_see(roomUUID,companyUUID){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/view.do");
			comSubmit.addParam("gubun",gubun);
			comSubmit.addParam("mode",'view');
			comSubmit.addParam("roomUUID",roomUUID);
			comSubmit.addParam("companyUUID",companyUUID);
			comSubmit.submit();
		}
		
		function fn_update(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/register.do");
			comSubmit.addParam("gubun",gubun);
			comSubmit.addParam("mode",'update');
			comSubmit.addParam("roomUUID",uuid);
			comSubmit.submit();
		}
		
		function fn_delete(uuid){
			if(confirm("카테고리를 삭제하시겠습니까?")){
				var comSubmit = new ComSubmit();
				comSubmit.setUrl("/user/room/delete.do");
				comSubmit.addParam("roomUUID",uuid);
				comSubmit.addParam("gubun",gubun);
				comSubmit.submit();
			}else return false;
		}
	</script>		
</body>
</html>