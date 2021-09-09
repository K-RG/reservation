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
		<h2 class="sub-header">예약 객실 정보</h2>
		<br>
			<form id="roomReservationInfo" enctype="multipart/form-data">
				<input type="hidden" id="mode" name="mode" value="${mode}">
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 4%;">상호명</label>
									<input type="text" id="companyName" name="companyName" class="form-control" value="${roomVO.companyVO.companyName}" readonly style="margin-right: 10%">
									<label for="companyAddress" style="margin-right: 2.5%;">사업장 주소</label>
									<input type="text" id="companyAddress" name="companyAddress" class="form-control" value="${roomVO.companyVO.strCompanyAddress}" readonly style="width: 25%;margin-right: 5.7%;">
									<label for="ceoName">사업장 전화번호</label>
								<input type="text" id="companyCellPhone" name="companyCellPhone" class="form-control" value="${roomVO.companyVO.strCompanyCellPhone}" readonly style="float: right;">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 1.9%;">사업장 검색</label>
									<select class="form-control" name="company" id="company" style="width: 12.7%;">
										<c:forEach var="item" items="${companyList}">
											<option value="${item.companyUUID}" <c:if test="${item.companyUUID eq roomVO.companyVO.companyUUID }">selected</c:if>>${item.companyName}</option>
										</c:forEach>
									</select>
									<div class="form-group col-sm1" style="margin-right: 6.2%;">	
										<button type="button" class="btn btn-default btn-primary" id="btnCompanySearch">검색</button>
									</div>
									<label for="companyAddress" style="margin-right: 4.4%;">방 번호</label>
									<select class="form-control" name="roomUUID" id="roomUUID" style="width: 17.5%;">
										<option>방 번호 선택</option>
										<c:if test="${roomVO != null}">
										<option value="${roomVO.roomUUID}" selected>${roomVO.roomNumber }</option>
										</c:if>
									</select>
									<div class="form-group col-sm1" style="margin-right: 5.7%;">	
										<button type="button" class="btn btn-default btn-primary" id="btnRoomPick">선택</button>
										<button type="button" class="btn btn-default btn-primary" id="btnRoomCancle">취소</button>
									</div>
									<label for="roomName">방 이름</label>
									<input type="text" id="roomName" name="roomName" class="form-control" value="${roomVO.roomName}" readonly style="float: right;">
								</div> 
							</div>
						</div>
						<div class="form-row" >
							<div class="form-group" id="d1">
								<div class="form-inline" >
									<label for="roomCheckInTime" style="margin-right:1.9%">체크인 시간</label>
									<input type="text" id="roomCheckInTime" name="roomCheckInTime" class="form-control"  value="${roomVO.roomCheckInTime}" style="margin-right: 16%" readonly>
									<label for="roomCheckOutTime" style="margin-right:1.5%">체크아웃 시간</label>
									<input type="text" id="roomCheckOutTime" name="roomCheckOutTime" class="form-control" value="${roomVO.roomCheckOutTime}" style="margin-right: 12%" readonly>
									<label for="roomFloor" style="margin-right:2.8%">예약 날짜</label>
									<input type="text" id="reservationDay" name="reservationDay" class="form-control" style="float:right; width: 12.7%;" readonly>
									
								</div>
							</div>
						</div>
						<hr/>
						<h2 class="sub-header">고객 정보 작성</h2>
						<br>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomFloor" style="margin-right:1.9%">예약자 이름</label>
									<input type="text" id="roomReservationName" name="roomReservationName" class="form-control" value=""  style="margin-right: 16%">
									<label for="phoneCheck" style="margin-right: 1.5%;">예약자 연락처</label>
									<input type="text" id="roomReservationCellPhone" name="roomReservationCellPhone" class="form-control" readonly style="margin-right: 12%">
									<label for="roomStandardPeople" style="margin-right:1%">결제 유형</label>
									<select class="form-control" id="roomReservationPaymentType" name="roomReservationPaymentType" style="float: right;width: 12.7%;">
										<option value="0">직접 결제</option>
										<option value="1">무통장 입금</option>
										<option value="2">카드 결제</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="" style="margin-right:2.8%">예약 인원</label>
									<input type="number" id="roomReservationPeople" name="roomReservationPeople" class="form-control" value="" style="width: 12.7%;margin-right: 16%;" min="1" max="10" maxlength="2">
									<label for="roomFloor" style="margin-right:3.3%">입실 날짜</label>
									<input type="date" id="startDay" name="startDay" class="form-control" style="width: 12.7%;margin-right: 12%;">
									<label for="phoneCheck" style="margin-right: 3.3%;">퇴실 날짜</label>
									<input type="date" id="endDay" name="endDay" class="form-control" style="width: 12.7%;float: right;">
								</div>
							</div>
						</div>
						<br>
						<div class="form-row">
							<div class="form-group">
								<label for="roomIntroduce">예약자 요청 사항</label>
								<br><br>
								<textarea rows="20" cols="100" id="roomReservationRequest" name="roomReservationRequest"></textarea>
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
			$("#btnCompanySearch").click(function(){
				var companyUUID = $("#companyUUID").val();
				$.ajax({
					url:"/user/reservation/companySearch.do",
					data: $("#company"),
					dataType:"json",
					success:function(data){
						var roomUUID = "";
						var company = new Array();
						if(data.childRoomList.length > 0){
							company[0] = data.company.companyName;
							company[1] = "("+data.company.companyPostCode+")"+data.company.companyAddress+data.company.companyDetailAddress;
							company[2] = data.company.companyCellPhoneFst+"-"+data.company.companyCellPhoneSnd+"-"+data.company.companyCellPhoneTrd;
							roomUUID += "<option selected>방 번호 선택</option>";
							for(var i=0; i<data.childRoomList.length; i++){
								roomUUID += "<option value='"+data.childRoomList[i].roomUUID+"'>";
								roomUUID += data.childRoomList[i].roomNumber+"</option>";
							}
						}else{
							alert("해당 정보가 존재하지 않습니다. 관리자에게 문의 해주세요.");
							company[0] = "정보 없음";
							company[1] = "정보 없음";
							company[2] = "정보 없음";
							roomUUID += "<option selected>정보 없음</option>";
						}
						$("#companyName").attr("value",company[0]);
						$("#companyAddress").attr("value",company[1]);
						$("#companyCellPhone").attr("value",company[2]);
						$("#roomUUID").html(roomUUID);
					},
					error:function(){
						alert("해당 정보가 존재하지 않습니다. 관리자에게 문의 해주세요.");
					}
				});
			});
			$("#roomUUID").on("change",function(){
				var roomUUID = $("#roomUUID").val();
				$.ajax({
					url:"/user/reservation/roomSearch.do",
					data: $("#roomUUID"),
					dataType:"json",
					success:function(roomInfo){
						var room = new Array();
						if(roomInfo != null){
							room[0] = roomInfo.roomName;
							room[1] = roomInfo.roomCheckInTime.split(':');
							room[2] = roomInfo.roomCheckOutTime.split(':');
						}else{
							alert("해당 방에 대한 정보가 없습니다. 관리자에게 문의 해주세요.");
						}
						$("#roomName").attr("value",room[0]);
						$("#roomCheckInTime").attr("value",room[1][0]+":"+room[1][1]+room[1][2].substring(3));
						$("#roomCheckOutTime").attr("value",room[2][0]+":"+room[2][1]+room[2][2].substring(3));
					},
					error:function(){
						alert("해당 방에 대한 정보가 없습니다. 관리자에게 문의 해주세요.");
					}
				});
			});
			CKEDITOR.replace('roomReservationRequest', {
				filebrowserUploadUrl:'/ckeditor/fileupload.do'
			});
			let [today] = new Date().toISOString().split("T");
			$("#startDay").attr("min",today);
			$("#endDay").attr("min",today);
			$("#reservationDay").attr("value",today);
			$("#btnList").on("click",function(e){
				e.preventDefault();
				fn_list();
			});
			$("#btnSave").on("click",function(e){	
				e.preventDefault();
				fn_save();			
			});
			$("#roomReservationCellPhone").on("click",function(e){
				e.preventDefault();
				fn_check();
			});
			$("#btnRoomPick").on("click",function(){
				$("#roomUUID").attr("disabled",'true');
			});
			$("#btnRoomCancle").on("click",function(){
				$("#roomUUID").removeAttr("disabled");
			});

			function fn_check(){
				window.open("/user/reservation/check.do","_blank","width=500, height=300");
			}
			
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
				comSubmit.addParam("roomUUID",$("#roomUUID").val());
				if(mode == 'update'){
					comSubmit.setUrl("/user/reservation/update.do");
	                comSubmit.submit();
				} else if (mode == 'reservation'){
					comSubmit.setUrl("/user/reservation/save.do");
	                comSubmit.submit();
				}
				
				
			}
		});
	</script>
</body>
</html>