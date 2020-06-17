<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
<link href="../resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<h4>アプリ実行中、例外が発生しました。</h4>

<p>下記のログを解析してください。</p>

<p>例外名: <s:property value="exception" /> </p>

<p>例外内容: <s:property value="exceptionStack" /></p>
</body>
</html>