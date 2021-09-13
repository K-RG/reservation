<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/user/template/header.jsp" flush="true">
		<jsp:param value="RESERVATION ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
	<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true"></jsp:include>
	<script type="text/javascript" src="<c:url value='/resources/ckEditor/ckeditor.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h2 class="sub-header">ROOM RESERVATION ${fn:toUpperCase(mode)}</h2>
			<form id="roomReservationInfo" enctype="multipart/form-data">
				<input type="hidden" id="mode" name="mode" value="${mode}">
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<label for="companyName" style="margin-right: 4%;">예약 번호</label>
							<div class="form-group">	
									<input type="text" class="input-group-text" id="roomReservationUUID" name="roomReservationUUID" class="form-control" placeholder="예약번호를 입력해주세요.">
							</div>
						</div>
						<div class="text-right">
	            			<button type="button" class="btn btn-primary" id="btnSelect">${fn:toUpperCase(mode)}</button>
	            			<button type="button" class="btn btn-default" id="btnCancle">취소</button>
	            		</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		var roomReservationUUID = '${roomReservationUUID}';	
		var company = '${companyBank}';
		var depositor = '${depositor}';
		
		if(roomReservationUUID.trim() != null && company.trim() != null ){
			Swal.fire({
	            icon: 'success',
	            title: '예약 완료',
	            html: '<table><tr><th>예약 번호</th><td colspan="3">'+roomReservationUUID+"</td></tr>"
	            		+"<tr><th>은행명</th><td>${fn:split(companyBank,'-')[0]}</td><th>예금주</th><td>${fn:split(companyBank,'-')[2]}</td></tr>"
	            		+"<tr><th>계좌번호</th><td> ${fn:split(companyBank,'-')[1]}</td><th>입금자명</th><td>"+depositor+"</td></tr>"
	            		+"</table>"
	        })
		}else if(roomReservationUUID.trim() != null || companyBank.trim() != null ){
			Swal.fire({
	            icon: 'success',
	            title: '예약 완료',
	            text: '예약 번호 : '+roomReservationUUID
	        })
		}
		$("#btnSelect").click(function(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/reservation/view.do");
			comSubmit.addParam("roomReservationUUID",$("#roomReservationUUID").val());
			comSubmit.addParam("mode","view");
			comSubmit.submit();			
		});
	</script>
</body>
</html>