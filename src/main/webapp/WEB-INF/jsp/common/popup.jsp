<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<script type="text/javascript" src="/resources/jquery/jquery-1.11.1.js"></script>
<title>${bannerPopupVO.bannerPopupTitle}</title>
<script type="text/javascript">

	function setCookie( name, value, expiredays ) {
	    var todayDate = new Date();
	    todayDate.setDate( todayDate.getDate() + expiredays );
	    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}
	
	function closeWin(recnum) {
		if ( document.pop.check.checked )
		    setCookie( "POPUP_"+recnum, "OK" , 1);  // 오른쪽 숫자는 쿠키를 유지할 기간을 설정합니다
		
		    self.close();
	}
	function goURL(t,v) {
	    if(v == '')return false;
	    
	    if(t=="opener") {
	        eval(t+".document.location.href='"+v+"'");
	        self.close();
	    } else if(t=="_blank") {
	        window.open(v);
	    } else if(t=="_self") {
	        location.href=v;
	    }else{
	        opener.document.location.href=v;
	        self.close();
	    }
	
	}
	
	$(document).ready(function(){
		$("body").attr("style","margin:0");  
	});
</script>
</head>
<body>
	<div style="overflow:hidden;">
		<table style="width:100%;height:${bannerPopupVO.bannerPopupHeight}px;" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="height:100%;">
					<c:if test="${fn:length(bannerPopupVO.fileList) ne 0}">
					<a href="#" onclick="goURL('${bannerPopupVO.getStrTarget()}','${bannerPopupVO.bannerPopupLink}');" style="display:block; width:${bannerPopupVO.bannerPopupWidth}px; height:${bannerPopupVO.bannerPopupHeight}px;">
						<img alt="${bannerPopupVO.bannerPopupTitle}" width="${bannerPopupVO.bannerPopupWidth}px" height="${bannerPopupVO.bannerPopupHeight}px" src="/upload/bannerpopup/original/${bannerPopupVO.fileList[0].bannerPopupFileMask}">
					</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="height:25px;">
					<form name="pop" style="background:#2b3b4f;height:40px;line-height:40px;">
						<table style="width:100%;" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td style="font-size:11px; background:#2b3b4f; color:#fff; padding-left:10px">&nbsp;하루동안열지 않기
									<input type="checkbox" name="checkbox" id="check" onClick="closeWin('${bannerPopupVO.bannerPopupUUID}')" style="vertical-align:middle;margin-top:-2px;"></td>
								<td align="right" style="background:#2b3b4f; margin-top:5px;font-size:12px; padding-right:10px"><a href="javascript:window.close()" style="color:#fff;">닫기</a>&nbsp; </td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>