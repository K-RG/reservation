<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>관리자 로그인 페이지</title>
	
	<link href="/resources/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="/resources/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
	<link href="/resources/css/mngr/login.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="/resources/js/common.js"></script>
</head>
<body>
	<div class="container" id="login-container">
		<div class="row">
			<h2>ADMIN LOGIN</h2>
			<form id="loginFrm">
				<div class="justify-content-center align-items-center" id="loginForm">
					<div class="form-group">        
						<input type="text" class="form-control" name="userId" id="userId" placeholder="ID">      
					</div>      
					
					<div class="form-group">        
						<input type="password" class="form-control" name="userPassword" id="userPassword" placeholder="PASSWORD">      
					</div>
		
					<div class="form-group">        
						<button type="button" class="btn btn-primary btn-block" id="btnLogin">LOGIN</button>      
					</div> 	
				</div>		
			</form>		
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#btnLogin").on("click", function(e){
				e.preventDefault();
				fn_login();
			});
		});
		
		function fn_login(){
			
			if(!$("#userId").val()){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			
			if(!$("#userPassword").val()){
				alert("비밀번호를 입력해주세요.");
				$("#userPassword").focus();
				return false;
			}
			
			var comSubmit = new ComSubmit("loginFrm");
			comSubmit.setUrl("/mngr/login/action.do");
			comSubmit.submit();
			
		}
	</script>
</body>
</html>