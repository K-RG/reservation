<%@page import="egovframework.com.reservation.custom.helper.CompanyCustomHelper"%>
<%@page import="egovframework.com.reservation.company.model.CompanyVO"%>
<%@page import="egovframework.com.reservation.company.service.CompanyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	CompanyService cService = CompanyCustomHelper.getCompanyService(application);
%>
		<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}"/>  
		<div class="col-sm-3 col-md-2 sidebar">
		<c:choose>
		
			<c:when test="${fn:indexOf(path, '/user/company/') ne -1}">
			<ul class="nav nav-sidebar">
	            <li class="active">
	            	<a href="/user/company/list.do">사업장 보기 <c:if test="${path eq '/user/company/list.do'}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
          	</ul>	
			</c:when>
			
			<c:when test="${(fn:indexOf(path, '/user/room/') ne -1)}">
			<ul class="nav nav-sidebar">
				<c:forEach var="roomFloor" begin="1" end="${maxRoomFloor}" varStatus="floor">
				<li <c:if test="${gubun eq floor.current}">class="active"</c:if>>
	            	<a href="/user/room/list.do?companyUUID=${companyVO.companyUUID}&gubun=${floor.current}">${floor.current}층 <c:if test="${gubun eq floor.current}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
				</c:forEach>
          	</ul>
			</c:when>
			
			<c:when test="${fn:indexOf(path, '/user/reservation/') ne -1}">
			<ul class="nav nav-sidebar">
				<%-- <li <c:if test="${gubun eq 1}">class="active"</c:if> >
	            	<a href="/user/reservation/list.do?gubun=1">예약 가능 객실  <c:if test="${gubun eq 1}"><span class="sr-only">(current)</span></c:if></a>
	            </li> --%>
				<li <c:if test="${gubun eq 1}">class="active"</c:if> >
	            	<a href="/user/reservation/select.do?gubun=1">예약 조회 <c:if test="${gubun eq 1}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
          	</ul>	
			</c:when>
		</c:choose>
        </div>