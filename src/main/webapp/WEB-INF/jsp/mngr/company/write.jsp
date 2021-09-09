<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/mngr/template/header.jsp" flush="true">
		<jsp:param value="COMPANY ${fn:toUpperCase(mode)}" name="headerTitle"/>
	</jsp:include>
</head>
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function addressSearch() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("companyAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("companyAddress").value = '';
	            }
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('companyPostCode').value = data.zonecode;
	            document.getElementById("companyAddress").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("companyDetailAddress").focus();
	        }
	    }).open();
	}
</script>
<body>
	<jsp:include page="/WEB-INF/jsp/mngr/template/top.jsp" flush="true"></jsp:include>
		<div class="row">
		<jsp:include page="/WEB-INF/jsp/mngr/template/left.jsp" flush="true"></jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">COMPANY ${fn:toUpperCase(mode)}</h2>
			<form id="companyFrm">
				<input type="hidden" id="mode" name="mode" value="${mode}">
				<c:if test="${mode eq 'update' }">
				<input type="text" id="companyUUID" name="companyUUID" value="${companyVO.companyUUID }">
				</c:if> 
				<div class="row">
					<div class="col-sm-12 pt-3">
						<div class="form-row">
							<div class="form-group">
								<label for="companyName">상호명</label>
								<input type="text" id="companyName" name="companyName" class="form-control" value="${companyVO.companyName}">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="companyAddress">사업장 주소</label>
								<div class="form-inline">
									<input type="text" id="companyPostCode" placeholder="우편번호" name="companyPostCode" class="form-control" value="${companyVO.companyPostCode}">
									<input type="button" class="btn btn-primary btn-sm" onclick="addressSearch()" value="주소 검색">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="companyDetailAddress">상세 주소</label>
								<div class="form-inline">
									<input type="text" placeholder="도로명주소" id="companyAddress" name="companyAddress" class="form-control" required value="${companyVO.companyAddress}" style="width: 19.6%;margin-right: 0.3%;">
									<input type="text" placeholder="상세주소" id="companyDetailAddress" name="companyDetailAddress" class="form-control" required value="${companyVO.companyDetailAddress}" style="width: 25.9%;margin-right: 7.6%;">
									<label for="companyAddress" style="margin-right: 2.7%;">객실 층수</label>
									<input type="text" id=companyRoomFloor name="companyRoomFloor" class="form-control" required value="${companyVO.companyRoomFloor}">
								</div>
							</div>
						</div>	
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="ceoCellPhoneFst" style="margin-right: 1%;">사업장 연락처</label>
									<input type="text" id="companyCellPhoneFst" name="companyCellPhoneFst" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyCellPhoneFst}"</c:if> maxlength="3" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="companyCellPhoneSnd" name="companyCellPhoneSnd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyCellPhoneFst}"</c:if> maxlength="4" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="companyCellPhoneTrd" name="companyCellPhoneTrd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyCellPhoneTrd}"</c:if> maxlength="4" onkeyup="checkRegexpValue('n',this)" style="margin-right: 7.5%;">
									<label for="companyRegistrationNumberFst" style="margin-right: 0.1%;">사업자 등록번호</label>
									<input type="text" id="companyRegistrationNumberFst" name="companyRegistrationNumberFst" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyRegistrationNumberFst}"</c:if> maxlength="3" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="companyRegistrationNumberSnd" name="companyRegistrationNumberSnd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyRegistrationNumberSnd}"</c:if> maxlength="2" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="companyRegistrationNumberTrd" name="companyRegistrationNumberTrd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.companyRegistrationNumberTrd}"</c:if> maxlength="4" onkeyup="checkRegexpValue('n',this)">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<div class="form-inline">
									<label for="ceoName" style="margin-right: 1.8%;">대표자 이름</label>
									<input type="text" id="ceoName" name="ceoName" class="form-control" value="${companyVO.ceoName }" style="margin-right: 34%;width: 12.8%;">
									<label for="ceoCellPhoneFst" style="margin-right: 1%;">대표자 연락처</label>
									<input type="text" id="ceoCellPhoneFst" name="ceoCellPhoneFst" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.ceoCellPhoneFst}"</c:if> maxlength="3" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="ceoCellPhoneSnd" name="ceoCellPhoneSnd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.ceoCellPhoneSnd}"</c:if> maxlength="4" onkeyup="checkRegexpValue('n',this)">-
									<input type="text" id="ceoCellPhoneTrd" name="ceoCellPhoneTrd" class="form-control" <c:if test="${mode eq 'update'}">value="${companyVO.ceoCellPhoneTrd}"</c:if> maxlength="4" onkeyup="checkRegexpValue('n',this)">
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-inline">
								<label for="companyBankName" style="margin-right: 3.9%;">은행명</label>
								<select class="form-control" name="companyBankName" id="companyBankName" style="width: 8%;margin-right: 1%;">
									<option value="1">한국은행 </option>
									<option value="2">KB국민은행 </option>
									<option value="3">신한은행</option>
									<option value="4">우리은행</option>
									<option value="5">하나은행</option>
									<option value="6">카카오뱅크</option>
									<option value="7">KDB산업은행</option>
									<option value="8">IBK기업은행</option>
									<option value="9">NH농협은행</option>
									<option value="10">수협은행</option>
									<option value="11">대구은행</option>
									<option value="12">부산은행</option>
									<option value="13">경남은행</option>
									<option value="14">광주은행</option>
									<option value="15">전북은행</option>
									<option value="16">제주은행</option>
								</select>
								<label for="companyBankAccountNumber">계좌번호</label>
								<input type="text" id="companyBankAccountNumber" name="companyBankAccountNumber" class="form-control" value="${companyVO.companyBankAccountNumber}" style="margin-right: 0.5%;">
								<label for="companyBankAccountHolder">예금주</label>                                                           
								<input type="text" id="companyBankAccountHolder" name="companyBankAccountHolder" class="form-control" value="${companyVO.companyBankAccountHolder}" style="width: 9.7%;">
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="form-group row">
				<div class="col-sm-12 text-right">
					<button type="button" class="btn btn-primary" id="btnList">목록</button>
					<button type="button" class="btn btn-default" id="btnSave">${fn:toUpperCase(mode)}</button>
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
			$("#btnSave").on("click",function(e){	
				e.preventDefault();
				fn_save();			
			});
		});
		
		function fn_list() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/mngr/company/list.do");
			comSubmit.submit();
		}
		function fn_save() {
			if(!$("#companyName").val()){
				alert('상호명을 입력해주십시오.');
				$("#companyName").focus();
				return false;
			}
			
			var mode = '${mode}';
			var comSubmit = new ComSubmit("companyFrm");
			if(mode == 'update'){
				comSubmit.setUrl("/mngr/company/update.do");
			} else if (mode == 'write'){
				comSubmit.setUrl("/mngr/company/save.do");
			}
			comSubmit.submit();
			
		}
	</script>
</body>
</html>