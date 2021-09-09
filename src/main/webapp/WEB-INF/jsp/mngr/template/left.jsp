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
	            	<a href="/mngr/user/list.do">사용자관리 <c:if test="${path eq '/user/company/list.do'}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
          	</ul>	
			</c:when>
			
			<c:when test="${(fn:indexOf(path, '/user/room/') ne -1)}">
			<ul class="nav nav-sidebar">
				<c:forEach var="roomFloor" begin="1" end="${companyVO.companyRoomFloor}" varStatus="floor">
				<li <c:if test="${gubun eq floor.current}">class="active"</c:if>>
	            	<a href="/mngr/room/list.do?companyUUID=${companyVO.companyUUID}&gubun=${floor.current}">${floor.current}층 <c:if test="${gubun eq floor.current}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
				</c:forEach>
          	</ul>
			</c:when>
			
			<c:when test="${fn:indexOf(path, '/mngr/company/') ne -1}">
			<ul class="nav nav-sidebar">
	            <li class="active">
	            	<a href="/mngr/company/list.do">사업장 관리 <c:if test="${path eq '/mngr/company/list.do'}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
          	</ul>
			</c:when>
			
			<c:when test="${(fn:indexOf(path, '/mngr/room/') ne -1)}">
			<ul class="nav nav-sidebar">
				<c:forEach var="roomFloor" begin="1" end="${companyVO.companyRoomFloor}" varStatus="floor">
				<li <c:if test="${gubun eq floor.current}">class="active"</c:if>>
	            	<a href="/mngr/room/list.do?companyUUID=${companyVO.companyUUID}&gubun=${floor.current}">${floor.current}층 <c:if test="${gubun eq floor.current}"><span class="sr-only">(current)</span></c:if></a>
	            </li>
				</c:forEach>
          	</ul>
			</c:when>
		</c:choose>
        </div>