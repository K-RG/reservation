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