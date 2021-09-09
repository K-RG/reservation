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
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 4%;">상호명</label>
									<input type="text" id="companyName" name="companyName" class="form-control" value="${roomReservationVO.roomVO.companyVO.strCompanyBank}" readonly style="margin-right: 10%">
									<label for="companyAddress" style="margin-right: 2.5%;">사업장 주소</label>
									<input type="text" id="companyAddress" name="companyAddress" class="form-control"style="width: 25%;margin-right: 5.7%;" value="${roomReservationVO.roomVO.companyVO.strCompanyAddress}" readonly>
									<label for="ceoName">사업장 전화번호</label>
								<input type="text" id="companyCellPhone" name="companyCellPhone" class="form-control" style="float: right;" value="${roomReservationVO.roomVO.companyVO.strCompanyCellPhone}" readonly>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 1.9%;">사업장 검색</label>
									<select class="form-control" name="companyUUID" id="companyUUID" style="width: 12.7%;margin-right: 9.9%;" disabled>
										<option value="${roomReservationVO.roomVO.companyVO.companyUUID}">${roomReservationVO.roomVO.companyVO.companyName}</option>
									</select>
									<label for="companyAddress" style="margin-right: 4.4%;">방 번호</label>
									<select class="form-control" name="roomUUID" id="roomUUID" style="width: 25.1%;margin-right: 5.5%;" disabled>
										<option value="${roomReservationVO.roomVO.roomUUID}">${roomReservationVO.roomVO.roomNumber}</option>
									</select>
									<label for="roomName">방 이름</label>
									<input type="text" id="roomName" name="roomName" class="form-control" style="float: right;" value="${roomReservationVO.roomVO.roomName}" readonly>
								</div> 
							</div>
						</div>
						<hr/>
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
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomFloor" style="margin-right:1.9%">예약자 이름</label>
									<input type="text" id="roomReservationName" name="roomReservationName" class="form-control" value="${roomReservationVO.roomReservationName}" style="margin-right: 16%" readonly>
									<label for="phoneCheck" style="margin-right: 1.5%;">예약자 연락처</label>
									<input type="text" id="roomReservationCellPhone" name="roomReservationCellPhone" class="form-control" value="${roomReservationVO.strRoomReservationCellPhone}" readonly style="margin-right: 12%">
									<label for="roomStandardPeople" style="margin-right:1%">결제 유형</label>
									<select class="form-control" id="roomReservationPaymentType" name="roomReservationPaymentType" style="float: right;width: 12.7%;" disabled>
										<option value="${roomReservationVO.roomReservationPaymentType}">${roomReservationVO.strRoomReservationPaymentType}</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="" style="margin-right:2.8%">예약 인원</label>
									<input type="text" id="roomReservationPeople" name="roomReservationPeople" class="form-control" value="${roomReservationVO.roomReservationPeople}명" style="width: 12.7%;margin-right: 16%;" readonly>
									<label for="roomFloor" style="margin-right:3.3%">입실 날짜</label>
									<input type="text" id="startDay" name="startDay" class="form-control" style="width: 12.7%;margin-right: 12%;" value="${fn:substring(roomReservationVO.startDay,0,10)}" readonly>
									<label for="phoneCheck" style="margin-right: 3.3%;">퇴실 날짜</label>
									<input type="text" id="endDay" name="endDay" class="form-control" style="width: 12.7%;float: right;" value="${fn:substring(roomReservationVO.endDay,0,10)}" readonly>
								</div>
							</div>
						</div>
						<br>
						<div class="form-row">
							<div class="form-group">
								<label for="roomIntroduce">예약자 요청 사항</label>
								<textarea rows="20" cols="100" id="roomReservationRequest" name="roomReservationRequest" readonly>${roomReservationVO.roomReservationRequest}</textarea>
							</div>
						</div>	
						<br><br>
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