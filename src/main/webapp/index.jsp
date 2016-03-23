<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:set var="ctx" value="${pageContext.request.contextPath }"/>   
<html>
<head>
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
welcome to mecn.
username<input name="username" id="username"/>
email<input name="email" id="email"/>
password<input name="password" id="password"/>
<button id="register">注册</button>

<br >
注册
identifier:<input name="identifier" id="lg_identifier">
password<input name="password" id="lg_password">
<button id="login">登录</button>
<script>

$("#login").on("click",function()
		{
			var name = $("#lg_identifier").val();
			var pass = $("#lg_password").val();
			
			var data = {
					"identifier" : name,
					"password" : pass
			};
			
			
			$.ajax({
				type : "POST",
				url : "${ctx}/api/user/login",
				data : data,
				success : function(res)
				{
					console.log(res);
				}
			})
		});
$("#register").on("click",function()
		{
			var name = $("#username").val();
			var email = $("#email").val();
			var pass = $("#password").val();
			
			var data = {
					"username" : name,
					"email" : email,
					"password" : pass
			};
			
			
			$.ajax({
				type : "POST",
				url : "${ctx}/api/user/register",
				data : data,
				success : function(res)
				{
					console.log(res);
				}
				
			})
		});

</script>
</body>
</html>