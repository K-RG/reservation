<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="/resources/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<jsp:include page="/WEB-INF/jsp/common/commonForm.jsp" flush="true"></jsp:include>
<script type="text/javascript">
$(function(){	
	var message = "";
	<c:forEach var="i" items="${errMap}">
	message += "${i.key} : ${i.value} \n";
	</c:forEach>
	
	alert(message);
	
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("${returnUrl}");
	comSubmit.addParam("mode",'${mode}');
	comSubmit.addParam("searchType",${searchType});
	comSubmit.addParam("searchContent",'${searchContent}');
	comSubmit.addParam("gubun",${gubun});
	
	comSubmit.addParam("page",${page});
	comSubmit.addParam("rowCount",${rowCount});
	
	<c:if test="${boardVO ne null}">
	comSubmit.addParam("boardVO",'${boardVO}');
	</c:if>
	
	<c:if test="${boardArticleVO ne null}">
	comSubmit.addParam("boardArticleVO",'${boardArticleVO}');
	</c:if>
	
	<c:if test="${menuVO ne null}">
	comSubmit.addParam("menuVO",${menuVO});
	comSubmit.addParam("menuGroup",${menuGroup});
	comSubmit.addParam("parentMenuUUID",'${parentMenuUUID}');
	</c:if>
	
	<c:if test="${categoryVO ne null}">
	comSubmit.addParam("categoryVO",'${categoryVO}');
	comSubmit.addParam("categoryGroup",${categoryGroup});
	comSubmit.addParam("parentCategoryUUID",'${parentCategoryUUID}');
	</c:if>
	
	comSubmit.submit();
});

</script>

 

