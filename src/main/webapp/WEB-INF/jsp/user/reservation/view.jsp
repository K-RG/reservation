<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
	<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true"></jsp:include>
	<script type="text/javascript" src="<c:url value='/resources/ckEditor/ckeditor.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h2 class="sub-header">ROOM ${fn:toUpperCase(mode)}</h2>
			<form id="roomReservationInfo" enctype="multipart/form-data">
				<input type="hidden" id="mode" name="mode" value="${mode}">
				<input type="text" id="roomReservationUUID" name="roomReservationUUID" value="${roomReservationVO.roomReservationUUID}">
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 4%;">상호명</label>
									<input type="text" id="companyName" name="companyName" class="form-control" value="${roomReservationVO.roomVO.companyVO.companyName}" readonly style="margin-right: 10%">
									<label for="companyAddress" style="margin-right: 2.5%;">사업장 주소</label>
									<input type="text" id="companyAddress" name="companyAddress" class="form-control" value="${roomReservationVO.roomVO.companyVO.strCompanyAddress}" readonly style="width: 25%;margin-right: 5.7%;">
									<label for="ceoName">사업장 전화번호</label>
								<input type="text" id="companyCellPhone" name="companyCellPhone" class="form-control" value="${roomReservationVO.roomVO.companyVO.strCompanyCellPhone}" readonly style="float: right;">
								</div>
							</div>
						</div>
						<div class="form-row" >
							<div class="form-group" id="d1">
								<div class="form-inline" >
									<label for="roomCheckInTime" style="margin-right:1.9%">체크인 시간</label>
									<input type="text" id="roomCheckInTime" name="roomCheckInTime" class="form-control"  value="${roomReservationVO.roomVO.roomCheckInTime}" style="margin-right: 16%" readonly>
									<label for="roomCheckOutTime" style="margin-right:1.5%">체크아웃 시간</label>
									<input type="text" id="roomCheckOutTime" name="roomCheckOutTime" class="form-control" value="${roomReservationVO.roomVO.roomCheckOutTime}" style="margin-right: 12%" readonly>
									<label for="roomFloor" style="margin-right:2.8%">예약 날짜</label>
									<input type="text" id="reservationDay" name="reservationDay" class="form-control" style="float:right; width: 12.7%;" value="${roomReservationVO.registDt}" readonly>
									
								</div>
							</div>
						</div>
						<hr/>
						<h3 class="sub-header">고객 예약 정보</h3>
						<br>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomFloor" style="margin-right:1.9%">예약자 이름</label>
									<input type="text" id="roomReservationName" name="roomReservationName" class="form-control" value="${roomReservationVO.roomReservationName}"  style="margin-right: 16%">
									<label for="phoneCheck" style="margin-right: 1.5%;">예약자 연락처</label>
									<input type="text" id="roomReservationCellPhone" name="roomReservationCellPhone" class="form-control" value="${roomReservationVO.strRoomReservationCellPhone}" style="margin-right: 12%">
									<label for="" style="margin-right:2.8%">예약 인원</label>
									<input type="number" id="roomReservationPeople" name="roomReservationPeople" class="form-control" value="${roomReservationVO.roomReservationPeople}" style="width: 12.7%;float: right;" min="1" max="10" maxlength="2">
									
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomFloor" style="margin-right:2.8%">입실 날짜</label>
									<input type="text" id="startDay" name="startDay" class="form-control" value="${fn:substring(roomReservationVO.startDay,0,10)}" style="width: 12.7%;margin-right: 16%;">
									<label for="phoneCheck" style="margin-right: 3.3%;">퇴실 날짜</label>
									<input type="text" id="endDay" name="endDay" class="form-control" value="${fn:substring(roomReservationVO.endDay,0,10)}" style="width: 12.7%;margin-right: 12%">
									<label for="roomStandardPeople" style="margin-right:1%">결제 상태</label>
									<input type="text" id="endDay" name="endDay" class="form-control" value="${roomReservationVO.strRoomReservationPaymentStatus}" style="width: 12.7%;float: right;">
								</div>
							</div>
						</div>
						<br>
						<div class="form-row">
							<div class="form-group">
								<label for="roomIntroduce">예약자 요청 사항</label>
								<br><br>
								<textarea rows="20" cols="100" id="roomReservationRequest" name="roomReservationRequest">${roomReservationVO.roomReservationRequest}</textarea>
							</div>
						</div>	
						<div class="text-right">
	            			<button type="button" class="btn btn-primary" id="btnList">목록</button>
	            			<button type="button" class="btn btn-default" id="btnSave">${fn:toUpperCase(mode)}</button>
	            		</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			CKEDITOR.replace('roomReservationRequest', {
				filebrowserUploadUrl:'/ckeditor/fileupload.do'
			});
			$("#btnList").on("click",function(e){
				e.preventDefault();
				fn_list();
			});
			$("#btnSave").on("click",function(e){	
				e.preventDefault();
				fn_save();			
			});
			
			function fn_list() {
				var comSubmit = new ComSubmit();
				comSubmit.setUrl("/user/room/1.firstfloor/list.do");
				comSubmit.submit();
			}
			
			function fn_save() {
				
				if(!$("#roomName").val()){
					alert('방 이름을 입력해주십시오.');
					$("#roomName").focus();
					return false;
				}
				var mode = '${mode}';
				var gubun = "${gubun}";
				
				var comSubmit = new ComSubmit("roomReservationInfo");
				comSubmit.addParam("roomReservationCellPhone",$("#roomReservationCellPhone").val());
				comSubmit.addParam("gubun",gubun);
				if(mode == 'update'){
					comSubmit.setUrl("/user/reservation/update.do");
					Swal.fire({
	                    icon: 'success',
	                    title: '예약 완료',
	                    text: '예약이 정상적으로 완료되었습니다.'
	                }).then(result => {
	                	comSubmit.submit();
					});
				} else if (mode == 'reservation'){
					comSubmit.setUrl("/user/reservation/save.do");
					Swal.fire({
	                    icon: 'success',
	                    title: '예약 완료',
	                    text: '예약이 정상적으로 완료되었습니다.'
	                }).then(result => {
	                	comSubmit.submit();
					});
				}
				
				
			}
		});
	</script>
</body>
</html>