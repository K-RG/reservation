<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Room reservation</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="/user/company/list.do">사업장 보기</a></li>
						<li><a href="/user/room/list.do">객실 보기</a></li>
						<li><a href="/user/reservation/select.do">나의 예약</a></li>
						<li><a href="/mngr/company/list.do">관리자 페이지 이동</a></li>
					</ul>
				  
				  	<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${loginUser.userName}</a></li>
						<li><a href="/logout.do">logout</a></li>
				  	</ul>
				</div>
			</div>
		</nav>