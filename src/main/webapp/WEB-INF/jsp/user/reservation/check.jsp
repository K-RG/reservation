<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<link href="/resources/datepicker/css/datepicker.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/resources/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/resources/datepicker/locales/bootstrap-datepicker.kr.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js" type="text/javascript" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<link href="/resources/css/mngr/login.css" type="text/css" rel="stylesheet"/>
<link href="/resources/css/mngr/layout.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
	<hr/>
	<input type="text" id="phoneCheck" name="phoneCheck" maxlength="13" onkeyup="autoHypenPhone(this)">
	<button type="button" id="btnCheck" >인증 요청</button>
	<hr/>
	<div id="d1"></div>
	<script type="text/javascript">
		$(function(){
			$("#btnCheck").click(function(){
				opener.document.getElementById("roomReservationCellPhone").value = document.getElementById("phoneCheck").value;
				/* $("#d1").append("<input type='text' id='checkNum' name='checkNum'>");
				$("#d1").append("<button type='button' id='btnOk'>인증 확인</button>");
				
				let phoneCheck = $("#phoneCheck").val();
				Swal.fire('인증번호가 발송되었습니다.');
				
				$.ajax({
					url:"/user/reservation/checkPhone.do",
					data : { 
						"phoneCheck" : phoneCheck 
					},
					dataType:"json",
					success:function(numStr){
						$("#btnOk").click(function(){
							if($.trim(numStr) == $("#checkNum").val()){
								Swal.fire({
	                                icon: 'success',
	                                title: '인증성공',
	                                text: '휴대폰 인증이 정상적으로 완료되었습니다.'
	                            }).then(result => {
	                            	opener.document.getElementById("roomReservationCellPhone").value = document.getElenmentById("phoneCheck").value
									window.close();	
								});
							}else{
								Swal.fire({
	                                icon: 'error',
	                                title: '인증오류',
	                                text: '인증번호가 올바르지 않습니다!',
	                                footer: '<a href="/home">다음에 인증하기</a>'
	                            });
							}
						});
					},
				}); */
			});
		});
		var autoHypenPhone = function(str){
		    str = str.replace(/[^0-9]/g, '');
		    var tmp = '';
		    if( str.length < 4){
		        return str;
		    }else if(str.length < 7){
		        tmp += str.substr(0, 3);
		        tmp += '-';
		        tmp += str.substr(3);
		        return tmp;
		    }else if(str.length < 11){
		        tmp += str.substr(0, 3);
		        tmp += '-';
		        tmp += str.substr(3, 3);
		        tmp += '-';
		        tmp += str.substr(6);
		        return tmp;
		    }else{              
		        tmp += str.substr(0, 3);
		        tmp += '-';
		        tmp += str.substr(3, 4);
		        tmp += '-';
		        tmp += str.substr(7);
		        return tmp;
		    }
		
		    return str;
		}
		var phoneCheck = document.getElementById('phoneCheck');

		phoneCheck.onkeyup = function(){
		  console.log(this.value);
		  this.value = autoHypenPhone( this.value ) ;  
		}
	</script>
</body>
</html>