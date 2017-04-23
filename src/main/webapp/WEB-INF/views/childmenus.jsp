<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<title>Title</title>
</head>
<body>
	<div class="menu_title layout_style_menu_title font_word_style">
		<i class="fa ${message.icon}"></i> ${message.name}管理
	</div>
	<div class="menu_line layout_style_menu_line"></div>
	<div id="menu_content_id"
		class="menu_content layout_style_nav_menu font_word_style">
		<ul>
			<c:forEach items="${menu}" var="user">
				<li url="${user.url }"><i class="fa ${user.icon}"></i>${user.name}</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#menu_content_id ul li').click(
					function() {
						var nav_two = $(this).parent().parent().parent()
								.children(".menu_title").first().html();
						var nav_one = $(this).html();
						showBreadCrumbMenu(nav_one, nav_two);
						var address = $(this).attr('url');
						$('.admin_content').load(address);

					});
		});
		function showBreadCrumbMenu(one, two) {
			var lis = $(".bread_crumb_menu li");
			$(".bc_list1").html(two);
			$(".bc_list2").html(one);
			/* $(lis[2]).html(two);
			$(lis[4]).html(one); */
		}
	</script>
</body>
</html>