<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="/resources/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
	<script type="text/javascript"> 
		var message = '${message}'; 
		var returnUrl = '${returnUrl}';
		var mode = '${mode}';
		
		alert(message);
		var comSubmit = new ComSubmit();
		comSubmit.setUrl(returnUrl);
		comSubmit.addParam("mode",mode);
		comSubmit.submit();
	</script>
</body>
</html>