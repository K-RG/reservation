<%@page import="egovframework.com.reservation.company.model.CompanyVO"%>
<%@page import="java.util.List"%>
<%@page import="org.stringtemplate.v4.compiler.CodeGenerator.list_return"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/user/template/header.jsp" flush="true">
		<jsp:param value="COMPANY VIEW" name="headerTitle"/>
	</jsp:include>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.10.25/b-1.7.1/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.10.25/b-1.7.1/datatables.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/user/template/top.jsp" flush="true"></jsp:include>
	<div class="row">
		<jsp:include page="/WEB-INF/jsp/user/template/left.jsp" flush="true">
			<jsp:param value="${gubun}" name="gubun"/>
		</jsp:include>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h2 class="sub-header">COMPANY ${fn:toUpperCase(mode)}</h2>
			<div class="table-responsive">
				<table class="table table-striped" id="myTable">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">상호명</th>
							<th class="text-center">(우편번호) 사업장 주소</th>
							<th class="text-center">사업장 연락처</th>
							<th class="text-center">동작</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td class="text-center">${status.count }</td>
							<td class="text-center">
								<a href="#" onclick="fn_view('${item.companyUUID}'); return false;">${item.companyName}</a>
								<input type="hidden" id="companyRoomFloor" name="companyRoomFloor" value="${item.companyRoomFloor}">
							</td>
							<td class="text-center">${item.strCompanyAddress}</td>
							<td class="text-center">${item.strCompanyCellPhone}</td>
	              			<td class="text-center">
	              				<button type="button" class="btn btn-default" onclick="fn_room('${item.companyUUID}')">객실 리스트</button>
		              		</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/user/template/bottem.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$(document).ready(function () {
			    $("#myTable").DataTable({
			    	dom: '<"col-sm-1"l><"col-sm-13"f>rti<"col-sm-6"p>',
			    	buttons:[{text:"사업장 등록", className:"btn btn-default btnRegister"}],
			    	language: lang_kor
			    });
				$(".btnRegister").on("click",function(e){
					e.preventDefault();
					fn_write();
				});
			});
		});
		
		var lang_kor = {
		        "decimal" : "",
		        "emptyTable" : "데이터가 없습니다.",
		        "info" : "_START_ - _END_ (총 _TOTAL_ 개)",
		        "infoEmpty" : "0개",
		        "infoFiltered" : "(전체 _MAX_ 개 중 검색결과)",
		        "infoPostFix" : "",
		        "thousands" : ",",
		        "lengthMenu" : "_MENU_ 개씩 보기",
		        "loadingRecords" : "로딩중...",
		        "processing" : "처리중...",
		        "search" : "검색 : ",
		        "zeroRecords" : "검색된 데이터가 없습니다.",
		        "paginate" : {
		            "first" : "첫 페이지",
		            "last" : "마지막 페이지",
		            "next" : "다음",
		            "previous" : "이전"
		        },
		        "aria" : {
		            "sortAscending" : " :  오름차순 정렬",
		            "sortDescending" : " :  내림차순 정렬"
		        }
	    };
		
		function fn_view(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/company/view.do");
			comSubmit.addParam("mode",'view');
			comSubmit.addParam("companyUUID",uuid);
			comSubmit.submit();
		}
		function fn_room(uuid){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("/user/room/list.do");
			comSubmit.addParam("companyUUID",uuid);
			comSubmit.submit();
		}
	</script>
</body>
</html>