<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="URL" value="${pageContext.request.requestURL}" />
<c:set var="URI" value="${pageContext.request.requestURI}" />
<c:set var="baseURL" value="${fn:replace(URL, fn:substring(URI, 0, fn:length(URI)), pageContext.request.contextPath)}/" />

<c:choose>
	<c:when test="${ prevUrl eq null || prevUrl eq baseURL }">
		<c:set var="returnUrl" value="/" />
	</c:when>
	
	<c:when test="${ fn:indexOf(prevUrl, '?') eq -1 }">
		<c:choose>
			<c:when test="${fn:indexOf(prevUrl, queryString) ne -1 }">
				<c:set var="returnUrl" value="${prevUrl}"/>
			</c:when>
			<c:otherwise>
				<c:set var="returnUrl" value="${prevUrl}?${queryString}"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="returnUrl" value="${prevUrl}"/>
	</c:otherwise>
</c:choose>
<c:if test="${ prevUrl eq url }">
	<c:set var="returnUrl" value="/index.do"/>
</c:if>

<script type="text/javascript">

	<c:forEach items="${checkMap[0]}" var="item">
		alert('${item.value}');
	</c:forEach>
	if(self.opener === null || self.opener === 'null' || self.opener === undefined){
		location.href='${returnUrl}';	
	} else {
		self.close();
	}

</script>
<%-- 
[path : ${pageContext.request.contextPath}]<br/>
[URL : ${requestScope['javax.servlet.forward.request_uri']}]<br/>
[URL : ${URL}] <br/>
[URI : ${URI}] <br/>
[baseURL : ${baseURL}] <br/>
[null chk : ${ prevUrl eq null }] <br/>
[prevUrl : ${prevUrl}] <br/>
[queryString : ${queryString}] <br/>
[returnUrl : ${returnUrl}] <br/>
 --%>
