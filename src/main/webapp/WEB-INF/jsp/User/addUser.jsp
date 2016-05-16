<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/basic.css">
<script type="text/javascript" src="js/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.min.js"></script>
<title>注册</title>
</head>
<script type="text/javascript">
	$(function(){
		$('#add').click(function(){
			var data = $('#form').serialize();
			var url = "user/add";
			$.post(url,data,function(msg){
				alert(msg.status);
			},'json');
		});
	});
</script>
<body>
	<div class="top_margin"></div>
	<div class="col-xs-offset-5">LOG</div>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-4">
				<div class="col-xs-12 col-md-6">
					<form id="form">
						<p>
							<div class="input-group ">
								<span class="input-group-addon">用户名　</span> 
								<input type="text" class="form-control" name="username">
							</div>
						</p>
						<p>
							<div class="input-group ">
								<span class="input-group-addon">输入密码</span> 
								<input type="text" class="form-control" name="password">
							</div>
						</p>
						<p>
							<div class="input-group ">
								<span class="input-group-addon">确认密码</span> 
								<input type="text" class="form-control">
							</div>
						</p>
						<p>
							<div class="input-group ">
								<span class="input-group-addon">联系邮箱</span> 
								<input type="text" class="form-control" name="email">
							</div>
						</p>
						<p>
							<div class="input-group ">
								<span class="input-group-addon">　昵称　</span> 
								<input type="text" class="form-control" name="name">
							</div>
						</p>
						<p>
							<div class="input-group ">
								<span class="input-group-addon">联系电话</span> 
								<input type="text" class="form-control" name="mobile">
							</div>
						</p>
						<!-- 选择头像 -->
						<p>
							<button id="add" type="button" class="btn btn-primary col-xs-12">注　册</button>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>