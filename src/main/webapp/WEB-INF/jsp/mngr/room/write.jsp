<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/mngr/template/header.jsp" flush="true">
		<jsp:param value="FIRST FLOOR ROOM ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
	
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/mngr/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
	<jsp:include page="/WEB-INF/jsp/mngr/template/left.jsp" flush="true"></jsp:include>
	<script type="text/javascript" src="<c:url value='/resources/ckEditor/ckeditor.js'/>"></script>
	
	
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h2 class="sub-header">COMPANY ROOM ${fn:toUpperCase(mode)}</h2>
			<form id="roomWriteFrm">
				<input type="hidden" id="mode" name="mode" value="${mode}">
				<input type="hidden" id="companyUUID" name="companyUUID" value="${companyUUID}">
				<c:if test="${mode eq 'update' }">
				<input type="hidden" id="roomUUID" name="roomUUID" value="${roomVO.roomUUID}">
				</c:if> 
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">	
									<label for="companyName" style="margin-right: 4%;">상호명</label>
									<input type="text" id="companyName" name="companyName" class="form-control" value="${companyVO.companyName}" readonly style="margin-right: 6%">
									<label for="companyAddress" style="margin-right: 2.5%;">사업장 주소</label>
									<input type="text" id="companyAddress" name="companyPostCode" class="form-control" value="${companyVO.strCompanyAddress}" readonly style="width: 25%;margin-right: 5.7%;">
									<label for="ceoName">대표자 이름</label>
								<input type="text" id="ceoName" name="ceoName" class="form-control" value="${companyVO.ceoName}" readonly style="float: right;">
								</div>
							</div>
						</div>
						<hr/>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomFloor" style="margin-right:1.7%">방 해당 층수</label>
									<select id="roomFloor" name="roomFloor" class="form-control" style="width: 12.6%;margin-right: 12%;">
										<c:forEach begin="1" end="${companyVO.companyRoomFloor}" varStatus="floor">
											<option value="${floor.current}" <c:if test="${floor.current eq roomVO.roomFloor}">selected</c:if>>${floor.current} 층</option>
										</c:forEach>
									</select>
									<label for="roomNumber" style="margin-right:4.2%">방 번호</label>
									<input type="text" id="roomNumber" name="roomNumber" class="form-control" value="${roomVO.roomNumber}"  maxlength="3" onkeyup="checkRegexpValue('n',this)" style="margin-right: 12%" placeholder="첫">
									<label for="roomName" style="margin-right:1%">방 이름</label>
									<input type="text" id="roomName" name="roomName" class="form-control" value="${roomVO.roomName}" style="float: right;">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomCheckInTime" style="margin-right:1.9%">체크인 시간</label>
									<input type="text" id="roomCheckInTime" name="roomCheckInTime" class="form-control"  value="${roomVO.roomCheckInTime}" style="margin-right: 12%">
									
									<label for="roomCheckOutTime" style="margin-right:1.5%">체크아웃 시간</label>
									<input type="text" id="roomCheckOutTime" name="roomCheckOutTime" class="form-control" value="${roomVO.roomCheckOutTime}" style="margin-right: 12%">
									
									<label for="roomStandardPeople" style="margin-right:1%">기준 인원</label>
									<input type="text" id="roomStandardPeople" name="roomStandardPeople" class="form-control" value="${roomVO.roomStandardPeople}" onkeyup="checkRegexpValue('n',this)" style="float: right;">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomStandardPeople" style="margin-right:2.8%">기준 인원</label>
									<input type="text" id="roomStandardPeople" name="roomStandardPeople" class="form-control" value="${roomVO.roomStandardPeople}" onkeyup="checkRegexpValue('n',this)" style="margin-right: 12%">
									
									<label for="roomMaxPeople" style="margin-right:3.3%">최대 인원</label>
									<input type="text" id="roomMaxPeople" name="roomMaxPeople" class="form-control" value="${roomVO.roomMaxPeople}" onkeyup="checkRegexpValue('n',this)" style="margin-right: 12%">
									
									<label for="roomPeopleExcessPrice" style="margin-right:1%">최대 인원 초과시 부과 요금(초과 인원당)</label>
									<input type="text" id="roomPeopleExcessPrice" name="roomPeopleExcessPrice" class="form-control" value="${roomVO.roomPeopleExcessPrice}" onkeyup="checkRegexpValue('n',this)" style="float: right;">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="roomPriceStandard" style="margin-right:2.8%">기본 요금</label>
									<input type="text" id="roomPriceStandard" name="roomPriceStandard" class="form-control" value="${roomVO.roomPriceStandard}" onkeyup="checkRegexpValue('n',this)" style="margin-right: 12%">
									<label for="roomPricePeakSeason" style="margin-right:2.4%">성수기 요금</label>
									<input type="text" id="roomPricePeakSeason" name="roomPricePeakSeason" class="form-control" value="${roomVO.roomPricePeakSeason}" onkeyup="checkRegexpValue('n',this)"  style="margin-right: 12%">
									<label for="roomPriceLowSeason" style="margin-right:1%">비수기 요금</label>
									<input type="text" id="roomPriceLowSeason" name="roomPriceLowSeason" class="form-control" value="${roomVO.roomPriceLowSeason}" onkeyup="checkRegexpValue('n',this)" style="float: right;">
								</div>
							</div>
						</div>
						<br><br>
						<div class="form-row">
							<div class="form-group">
								<label for="roomIntroduce">객실 소개</label>
								<input type="file" name="roomPhotoFile" id="roomPhotoFile">
								<textarea rows="20" cols="100" id="roomIntroduce" name="roomIntroduce">${roomVO.roomIntroduce }</textarea>
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
	<script type="text/javascript">
		$(function(){
			 $(function () {
	             $('#roomCheckInTime').datetimepicker({
	                 format: 'HH:mm:ss'
	             });
	             $('#roomCheckOutTime').datetimepicker({
	                 format: 'HH:mm:ss'
	             });
	         });
			$("#roomFloor").change(function(){
				var roomFloor = $(this).find('option:selected');
				var roomNumber = roomFloor.attr('value');
				$("#roomNumber").val(roomNumber);
			})
			CKEDITOR.replace('roomIntroduce', {
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
				comSubmit.setUrl("/mngr/room/1.firstfloor/list.do");
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
				
				var comSubmit = new ComSubmit("roomWriteFrm");
				comSubmit.addParam("gubun",gubun);
				if(mode == 'update'){
					comSubmit.setUrl("/mngr/room/update.do");
				} else if (mode == 'write'){
					comSubmit.setUrl("/mngr/room/save.do");
				}
				comSubmit.submit();
				
			}
		});
	</script>
</body>
</html>