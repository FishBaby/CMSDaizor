<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<div class="tab_bar font_word_style">
			<ul>
				<li class="tab_index font_word_style"><h5>菜单管理</h5></li>
			</ul>
			<button class="btn_tabbar" action="menu/add.do"
				onclick="refreshContentByElement(this);">添加菜单</button>
		</div>
		<div class="tab_content">
			<div class="tab_item tab_show">
				<table class="table_for_list">
					<thead>
						<tr>
							<th width="120">菜单名称</th>
							<th>URL地址</th>
							<th>描述</th>
							<th width="80">操作</th>
						</tr>
					</thead>
					<c:forEach items="${names}" var="user">
						<tr>
							<th><c:choose>
									<c:when test="${user.pid eq 0} && ">
										<i class="fa ${user.icon}"></i> ${user.name}
								    </c:when>
									<c:otherwise>
								|-<i class="fa ${user.icon}"></i>${user.name}
								</c:otherwise>
								</c:choose></th>
							<th>${user.url }</th>
							<th>${user.description }</th>
							<th>11</th>

						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>