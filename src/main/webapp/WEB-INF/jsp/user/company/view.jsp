<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/user/template/header.jsp" flush="true">
		<jsp:param value="COMPANY ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
</head>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
	<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
		<div class="row">
		<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true"></jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">COMPANY ${fn:toUpperCase(mode)}</h2>
			<form id="companyFrm">
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<label for="companyName">상호명</label>
								<input type="text" id="companyName" name="companyName" class="form-control" value="${companyVO.companyName}" readonly>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="companyAddress">사업장 주소</label>
								<div class="form-inline">
									<input type="text" id="companyPostCode" placeholder="우편번호" name="companyPostCode" class="form-control" value="${companyVO.companyPostCode}" readonly>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="companyDetailAddress">상세 주소</label>
								<div class="form-inline">
									<input type="text" placeholder="도로명주소" id="companyAddress" name="companyAddress" class="form-control" required value="${companyVO.companyAddress}" style="width: 19.6%;margin-right: 0.3%;" readonly>
									<input type="text" placeholder="상세주소" id="companyDetailAddress" name="companyDetailAddress" class="form-control" required value="${companyVO.companyDetailAddress}" style="width: 25.9%;margin-right: 7.6%;" readonly>
									<label for="companyAddress" style="margin-right: 2.7%;">객실 층수</label>
									<input type="text" id=companyRoomFloor name="companyRoomFloor" class="form-control" required value="${companyVO.companyRoomFloor}" readonly>
								</div>
							</div>
						</div>	
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="ceoCellPhoneFst" style="margin-right: 1%;">사업장 연락처</label>
									<input type="text" id="companyCellPhoneFst" name="companyCellPhoneFst" class="form-control" value="${companyVO.companyCellPhoneFst}" readonly>-
									<input type="text" id="companyCellPhoneSnd" name="companyCellPhoneSnd" class="form-control" value="${companyVO.companyCellPhoneFst}" readonly>-
									<input type="text" id="companyCellPhoneTrd" name="companyCellPhoneTrd" class="form-control" value="${companyVO.companyCellPhoneTrd}" style="margin-right: 7.5%;" readonly>
									<label for="companyRegistrationNumberFst" style="margin-right: 0.1%;">사업자 등록번호</label>
									<input type="text" id="companyRegistrationNumberFst" name="companyRegistrationNumberFst" class="form-control" value="${companyVO.companyRegistrationNumberFst}" readonly>-
									<input type="text" id="companyRegistrationNumberSnd" name="companyRegistrationNumberSnd" class="form-control" value="${companyVO.companyRegistrationNumberSnd}" readonly>-
									<input type="text" id="companyRegistrationNumberTrd" name="companyRegistrationNumberTrd" class="form-control" value="${companyVO.companyRegistrationNumberTrd}" readonly>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="ceoName" style="margin-right: 1.8%;">대표자 이름</label>
									<input type="text" id="ceoName" name="ceoName" class="form-control" value="${companyVO.ceoName }" style="margin-right: 34%;width: 12.8%;" readonly>
									<label for="ceoCellPhoneFst" style="margin-right: 1%;">대표자 연락처</label>
									<input type="text" id="ceoCellPhoneFst" name="ceoCellPhoneFst" class="form-control" value="${companyVO.ceoCellPhoneFst}" readonly>-
									<input type="text" id="ceoCellPhoneSnd" name="ceoCellPhoneSnd" class="form-control" value="${companyVO.ceoCellPhoneSnd}" readonly>-
									<input type="text" id="ceoCellPhoneTrd" name="ceoCellPhoneTrd" class="form-control" value="${companyVO.ceoCellPhoneTrd}" readonly>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-inline">
								<label for="companyBankName" style="margin-right: 3.9%;">은행명</label>
								<select class="form-control" name="companyBankName" id="companyBankName" style="width: 8%;margin-right: 1%;" disabled >
									<option value="소분류">소분류 </option>
								</select>
								<label for="companyBankAccountNumber">계좌번호</label>
								<input type="text" id="companyBankAccountNumber" name="companyBankAccountNumber" class="form-control" value="${companyVO.companyBankAccountNumber}" style="margin-right: 0.5%;" readonly>
								<label for="companyBankAccountHolder">예금주</label>                                                           
								<input type="text" id="companyBankAccountHolder" name="companyBankAccountHolder" class="form-control" value="${companyVO.companyBankAccountHolder}" style="width: 9.7%;" readonly>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="form-group row">
				<div class="col-sm-12 text-right">
					<button type="button" class="btn btn-default" onclick="fn_room('${companyVO.companyUUID}')">객실 보기</button>
					<button type="button" class="btn btn-primary" id="btnList">목록</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/user/template/bottem.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#btnList").on("click",function(e){
				e.preventDefault();
				fn_list();
			});
		});
		function fn_room(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/list.do");
			comSubmit.addParam("companyUUID",uuid);
			comSubmit.submit();
		}
		
		function fn_list() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/company/list.do");
			comSubmit.submit();
		}
	</script>
</body>
</html>