<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>${fns:getConfig('productName')}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    <!-- Icon 图标 -->
	<link rel="shortcut icon" href="${ctxStatic}/images/icon/favicon.ico">

    <link rel="apple-touch-icon" href="images/icons/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/icons/favicon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/icons/favicon-114x114.png">
    <!--Loading bootstrap css-->
    <link type="text/css" rel="stylesheet" href="http://fonts.useso.com/css?family=Open+Sans:400italic,400,300,700">
    <link type="text/css" rel="stylesheet" href="http://fonts.useso.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/font-awesome/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/bootstrap/css/bootstrap.min.css">
    <!--LOADING STYLESHEET FOR PAGE-->
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/intro.js/introjs.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/calendar/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/sco.message/sco.message.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/intro.js/introjs.css">
    <!--Loading style vendors-->
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/animate.css/animate.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/jquery-pace/pace.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/iCheck/skins/all.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/jquery-notific8/jquery.notific8.min.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/bootstrap-daterangepicker/daterangepicker-bs3.css">
    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/themes/style1/orange-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/themes/style1/orange-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/style-responsive.css">
    
    <script src="${ctxStatic }/js/jquery-1.10.2.min.js"></script>
	<script src="${ctxStatic }/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${ctxStatic }/js/jquery-ui.js"></script>
    <script type="text/javascript">
	//设置全局变量
	var ctx = '${ctx}', ctxStatic='${ctxStatic}', js_context='${ctxStatic}';
	//关闭Ajax Load页面时缓存
	$.ajaxSetup({
		cache : false
	});
</script>
</head>