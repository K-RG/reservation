<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button type="button" onclick="mngrPage()">관리자 페이지가기</button>
<button type="button" onclick="userPage()">사용자 페이지가기</button>
<script type="text/javascript">
	function mngrPage(){
		location.href="/mngr/index.do";
	}
	function userPage(){
		location.href="/user/index.do";
	}
</script>
</body>
</html>