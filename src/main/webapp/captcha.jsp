<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <c:set var="ctx" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
输入验证码
<input id="captchainput" name = "captchainput"></input>
<img alt="" src="${ctx}/mecn/api/captcha/gencaptcha">
<button id="check">check</button>

<script>

$("#check").on("click",function()
		{
	var input  = $("#captchainput").val();
	$.ajax({
		type : "GET",
		url : "${ctx}/mecn/api/captcha/validatecaptcha/"+input,
		success : function(res)
		{
			console.log(res);
		}
	})
		})

</script>
</body>
</html>