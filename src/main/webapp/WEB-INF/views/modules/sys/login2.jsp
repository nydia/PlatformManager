<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <title>登录</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="Thu, 19 Nov 1900 08:52:00 GMT">
    <!--Loading bootstrap css-->
    <link type="text/css" href="http://fonts.useso.com/css?family=Open+Sans:400italic,700italic,800italic,400,700,800">
    <link type="text/css" rel="stylesheet" href="http://fonts.useso.com/css?family=Oswald:400,700,300">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/jquery-ui-1.10.3.custom/css/ui-lightness/jquery-ui-1.10.3.custom.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/font-awesome/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/bootstrap/css/bootstrap.min.css">
    <!--Loading style vendors-->
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/animate.css/animate.css">
	<link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/jquery-pace/pace2.css">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/vendors/iCheck/skins/all.css">
    <!--Loading style-->
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/themes/style1/pink-blue.css" class="default-style">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/themes/style1/pink-blue.css" id="theme-change" class="style-change color-change">
    <link type="text/css" rel="stylesheet" href="${ctxStatic }/css/style-responsive.css">
    <link rel="shortcut icon" href="images/favicon.ico">
	<style type="text/css">

		.page-form input[type='text'],
		.page-form input[type='password'],
		.page-form select {
			height: 40px;
			border-color: #e5e5e5;
			-webkit-box-shadow: none !important;
			box-shadow: none !important;
			color: #330066;
			font-size:18px;
		}

		.page-form input[type='password'],
		.page-form select {
			padding-top:12px;
		}

		.input-icon i {
			color: #FFFF00;
		}

		
	</style>
</head>

<body id="signin-page">
    <div class="page-form">
        <form class="form" action="${ctx }/login" method="post">
            <div class="body-content"  data-value="flipInY">
				<div style="text-align: center;">
					<h1>${fns:getConfig('productName')}</h1>
				</div>
				<div style="text-align: center;color:red;">${msg}</div>
                <div class="form-group">
                    <div class="input-icon right"><i class="fa fa-user"></i>
                        <input type="text" placeholder="用户名" name="username" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon right"><i class="fa fa-key"></i>
                        <input type="password" placeholder="密码" name="password" class="form-control">
                    </div>
                </div>
                <div class="form-group pull-left">
                    <div class="checkbox-list">
                        <label><input type="checkbox"><span style="padding-top:10px;">&nbsp; 记住我</span></label>
                    </div>
                </div>
                <div class="form-group pull-right">
                    <button type="submit" class="btn btn-success">登录 &nbsp;
                        <i class="fa fa-chevron-circle-right"></i>
                    </button>
                </div>
                <div class="clearfix"></div>
                <div class="forget-password">
                    <h4>忘记密码？</h4>
                    <p><a href='#' class='btn-forgot-pwd'>点击</a>找回密码。</p>
                </div>
                <hr>
                <p>还没有账号？<a id="btn-register" href="extra-signup.html">现在注册</a>
                </p>
            </div>
        </form>
    </div>
    <script src="${ctxStatic }/js/jquery-1.10.2.min.js"></script>
    <script src="${ctxStatic }/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${ctxStatic }/js/jquery-ui.js"></script>
    <!--loading bootstrap js-->
    <script src="${ctxStatic }/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="${ctxStatic }/vendors/bootstrap-hover-dropdown/bootstrap-hover-dropdown.js"></script>
	<script src="${ctxStatic }/vendors/jquery-pace/pace.min.js"></script>
    <script src="${ctxStatic }/js/html5shiv.js"></script>
    <script src="${ctxStatic }/js/respond.min.js"></script>
    <script src="${ctxStatic }/vendors/iCheck/icheck.min.js"></script>
    <script src="${ctxStatic }/vendors/iCheck/custom.min.js"></script>

</body>

</html>