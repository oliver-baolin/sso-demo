<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>

<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/signin.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.4.js"></script>
<script>
$(function(){
	$("#loginsubmit").click(function(){
		//执行程序   提交表单
		$("#loginForm").submit();
	});
});
</script>
</head>
<body>
    ${error }
	<div class="signin">
		<div class="signin-head">
			<img src="<%=request.getContextPath() %>/images/test/head_120.png" alt="" class="img-circle">
		</div>
		<form id="loginForm" class="form-signin" role="form" action="/sso/login.aspx" method="post">
			<input type="text" class="form-control" name="username" placeholder="用户名" required autofocus /> 
			<input type="password" class="form-control" name="password" placeholder="密码" required />
			<button id="loginsubmit" class="btn btn-lg btn-warning btn-block">登录</button>
			<label class="checkbox"> <input type="checkbox" value="remember-me"> 记住我
			</label>
		</form>
	</div>
</body>
</html>