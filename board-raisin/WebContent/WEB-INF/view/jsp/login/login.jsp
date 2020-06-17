<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<script src="../resources/js/jquery-3.5.1.min.js"></script>
<link href="../resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<form class="form-signin" method="post" action="../login/login">
       <div class="text-center col-12" style="margin-top: 200px;">
	       <div class="col-4 bg-primary text-white" style="margin:auto;">
	           <h1>Login</h1>
	       </div>
	       <div class="form-label-group">
	           <s:textfield name="loginVO.userid" id="userId" class="form-control col-4" placeholder="UserID" style="margin:auto;" />
	       </div>
	       <div class="form-label-group" style="margin-top: 10px;">
	           <s:password name="loginVO.password" id="userPassword" class="form-control col-4" placeholder="Password" style="margin:auto;" />
	       </div>
	       <s:if test="loginVO.errMessage != null">
		       <div class="text-danger" style="margin-top: 10px;">
		            <h6 class="col-4" style="margin:auto">
		                <s:property value="loginVO.errMessage"/>
					</h6>
		       </div>
	       </s:if>
	       <button class="btn btn-lg btn-primary col-4" style="margin-top: 10px;" type="submit">ログイン</button>
       </div>
	</form>
</body>
</html>