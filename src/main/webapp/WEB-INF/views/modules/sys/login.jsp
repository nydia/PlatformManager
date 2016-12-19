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

<!--Import materialize.css-->
<link type="text/css" rel="stylesheet"
	href="${context }/static/js/materialize/css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--Import jQuery before materialize.js-->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="${context }/static/js/materialize/js/materialize.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<td>登陆信息：</td>
			<td>${msg}</td>
		</tr>
	</table>
	<hr />
	<div class="row">
		<div class="col s4">
			<form action="${ctx }/login" method="post">
				<table>
					<tr>
						<td colspan="2">用户登陆：</td>
					</tr>
					<tr>
						<td>用户名称：</td>
						<td><div class="input-field">
								<input type="text" name="loginName" value="" />
							</div></td>
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
		</div>
	</div>
	<hr />
	<table>
		<tr>
			<td colspan="2"><a href="${ctx }/logout">退出登陆</a></td>
		</tr>
	</table>
</body>