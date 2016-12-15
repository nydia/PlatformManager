<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>用户</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="<%=new Date()%>">
</head>
<body>
	<form action="${ctx }/login">
		<table>
			<tr>
				<td colspan="2">用户登陆：</td>
			</tr>
			<tr>
				<td>用户名称：</td>
				<td><input type="text" name="loginName" value="" /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password" value="" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>