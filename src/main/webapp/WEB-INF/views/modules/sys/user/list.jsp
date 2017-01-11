<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>

<!--begin content-->
<div class="animated fadeInDown">
	<!-- 下面为每个页面替换的内容 -->
	<div class="row">
		<div class="panel panel-blue">
			<div class="panel-heading clearfix">
				<span class="mts pull-left"></span>
				<div class="toolbars">
					<div class="btn-group">
						<button type="button"
							onclick="satisfy.openDialog('','${ctx}/sys/user/create','${_dia_2col }');"
							onclick="satisfy.openDialog('','${ctx}/sys/user/create','${_dia_2col }');"
							class="btn btn-orange">
							<i class="fa fa-plus"></i>&nbsp;
						</button>
					</div>
				</div>
			</div>
			<div class="panel-body" ng-app="myApp" ng-controller="userController">
				<form action="${ctx}/sys/user/list" method="post"
					onsubmit="return query_form();" id="table_user_query"
					class="form-horizontal">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-md-5 control-label">姓名</label>
									<div class="input-group col-md-7">
										<input type="text" name="name" placeholder=""
											class="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4"></div>
							<div class="col-md-4">
								<div class="form-group pull-right">
									<button type="submit" class="btn btn-green">
										<i class="fa fa-search"></i>&nbsp;
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="table_user"
								class="table table-hover table-striped table-bordered table-advanced tablesorter"
								width="100%" role="grid">
								<thead>
									<tr>
										<th width="3%"><input type='checkbox' class='checkall' /></th>
										<th width="10%">姓名</th>
										<th width="10%">登陆名称</th>
										<th width="10%">手机号</th>
										<th width="9%">创建日期</th>
										<th width="12%" /></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<!-- col-md-12 -->
				</div>
			</div>
			<!-- panel-body -->
		</div>
	</div>
</div>
<!--end content-->
<!-- 该部分填写页面内容 end -->
<!-- script模块 start -->
<script src="${ctxStatic }/vendors/angular-1.6.1/angular.min.js"></script>
<script
	src="${ctxStatic }/vendors/angular-1.6.1/angular-resource.min.js"></script>
<script src="${ctxStatic }/vendors/angular-1.6.1/angular-route.min.js"></script>

<script type="text/javascript">
	var app = angular.module('myApp', []);
	app.controller('userController', function($scope, $http) {
		$scope.login = function() {
			$http({
				url : ctx + '/sys/user/list',
				method : 'POST',
				data : {}
			}).success(function() {
				console.log("success!");
			}).error(function() {
				console.log("error");
			})
		};
	});
</script>
