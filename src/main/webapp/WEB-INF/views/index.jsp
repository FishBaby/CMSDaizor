<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<link rel="stylesheet" type="text/css" href="/static/css/default.css" />
<link rel="stylesheet" type="text/css" href="/static/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/static/css/core.css" />
<link rel="stylesheet" type="text/css" href="/static/css/hover.css" />
<link rel="stylesheet" type="text/css" href="/static/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css" />
<script type="text/javascript" src="/static/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/static/js/showtime.js"></script>
<script type="text/javascript" src="/static/js/core.js"></script>

<title>Title</title>
</head>
<body>
	<div id="admin_top" class="layout_style_top">
		<div id="logo" class="fl layout_style_logo">
			<img src="/static/images/logo.png" />
		</div>
		<div id="admin_nav"
			class="fl layout_style_nav font_word_style hvr-bob">
			<ul>
				<c:forEach var="user" items="${menu}">
					<li url="childMenus/${user.id }">${user.name}</li>
				</c:forEach>
			</ul>
		</div>
		<div id="index_time"
			class="fr layout_style_nav_time font_word_style layout_info"></div>
	</div>
	<div id="admin_left" class="pa layout_style_left">
		<div class="admin_menu layout_nav_menu"></div>
	</div>
	<div id="bread_crumb" class="pa">
		<ul class="bread_crumb_menu">
			<li><i class="fa fa-home"></i> Home</li>
			<li class="bc_line"><i class="fa fa-chevron-right"></i></li>
			<li class="bc_list1"></li>
			<li class="bc_line"><i class="fa fa-chevron-right"></i></li>
			<li class="bc_list2"></li>
			
		</ul>
	</div>
	<!-- 内容区 -->
	<div id="admin_right" class="pa">
		<div class="admin_content"></div>
	</div>
</body>
<script type="text/javascript">
	/*$(function() {
		$('.admin_menu').load('childMenus/1');
		$('.admin_content').load("aboutsystem");
	});*/
</script>
</html>