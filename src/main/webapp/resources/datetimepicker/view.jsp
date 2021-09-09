<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/mngr/template/header.jsp" flush="true">
		<jsp:param value="COMPANY VIEW" name="headerTitle"/>
	</jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/mngr/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/jsp/mngr/template/left.jsp" flush="true"></jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">COMPANY ${mode}</h2>
			<div class="form-inline text-right">
				<div class="form-group col-sm1">
					<c:if test="${companyVO eq null }">
					<button type="button" class="btn btn-default" id="btnRegister">사업장 등록</button>
					</c:if>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 pt-3">
					<div class="form-row">
						<div class="form-group">
							<label for="companyName">상호명</label>
							<input type="text" id="companyName" name="companyName" class="form-control" value="${companyVO.companyName}" readonly>
						</div>
					</div>
					<br>
					<div class="form-row">
						<div class="form-group">
							<label for="companyAddress">사업장 주소</label>
							<div class="form-group">
								<input type="text" id="companyAddress" name="companyPostCode" class="form-control" value="${companyVO.strCompanyAddress}" readonly>
							</div>
						</div>
					</div>	
					<br>
					<div class="form-row">
						<div class="form-group">
							<label for="ceoName">대표자 이름</label>
							<input type="text" id="ceoName" name="ceoName" class="form-control" value="${companyVO.ceoName}" readonly>
						</div>
					</div>
					<br><br>
					<div class="form-row">
						<div class="form-group">
							<div class="form-inline">
								<label for="ceoCellPhone" style="margin-right:1%">대표자 연락처</label>
								<input type="text" id="ceoCellPhone" name="ceoCellPhone" class="form-control" value="${companyVO.strCeoCellPhoneNumber}" readonly style="margin-right: 12%">
								<label for="companyRegistrationNumber" style="margin-right:1%">사업자등록번호</label>
								<input type="text" id="companyRegistrationNumber" name="companyRegistrationNumber" class="form-control" value="${companyVO.strCompanyRegistrationNumber}" readonly  style="margin-right: 12%">
								<label for="companyCellPhone" style="margin-right:1%">사업장 연락처</label>
								<input type="text" id="companyCellPhone" name="companyCellPhone" class="form-control" value="${companyVO.strCompanyCellPhone}" readonly style="float: right;">
							</div>
						</div>
					</div>
					<br>
					<div class="form-row">
						<div class="form-group">
							<div class="form-inline">
								<label for="companyBankName" style="margin-right:3.9%">은행명</label>
								<input type="text" id="companyBankName" name="companyBankName" class="form-control" value="${companyVO.companyBankName}" readonly style="margin-right: 12%">
								<label for="companyBankAccountNumber" style="margin-right:3.7%">계좌번호</label>
								<input type="text" id="companyBankAccountNumber" name="companyBankAccountNumber" class="form-control" value="${companyVO.companyBankAccountNumber}" readonly style="margin-right: 12%">
								<label for="companyBankAccountHolder" style="margin-right:1%">예금주</label>
								<input type="text" id="companyBankAccountHolder" name="companyBankAccountHolder" class="form-control" value="${companyVO.companyBankAccountHolder}" readonly style="float: right;">
							</div>
						</div>
					</div>
					<br><br>
					<div class="text-right">
            			<button type="button" class="btn btn-primary" id="btnList">목록</button>
            			<button type="button" class="btn btn-default" onclick="fn_update('${companyVO.companyUUID}')">수정</button>
            		</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/mngr/template/bottem.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#btnList").on("click",function(e){
				e.preventDefault();
				fn_list();
			});
			$("#btnRegister").on("click",function(e){
				e.preventDefault();
				fn_write();
			});
		});
		function fn_list() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/company/list.do");
			comSubmit.submit();
		}
		function fn_write(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/company/register.do");
			comSubmit.addParam("mode",'write');
			comSubmit.submit();
		}
		function fn_update(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/company/register.do");
			comSubmit.addParam("mode",'update');
			comSubmit.addParam("companyUUID",uuid);
			comSubmit.submit();
		}
	</script>
</body>
</html>