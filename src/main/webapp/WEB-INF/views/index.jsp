<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Accident</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/create'/>">Добавить инцидент</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/logout'/>">Выйти</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
		<div>Login as : ${user.username}</div>
	</div>
	<div class="container">
		<h2>Таблица</h2>
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>название</th>
					<th>тип</th>
					<th>статьи</th>
					<th>текст</th>
					<th>адрес</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${table}" var="t">
					<tr>
						<td><a href='<c:url value="/update?id=${t.id}"/>'> <i
								class="fa fa-edit mr-3"></i>
						</a> <c:out value="${t.id}" /></td>
						<td><c:out value="${t.name}" /></td>
						<td><c:out value="${t.type.name}" /></td>
						<td><c:forEach items="${t.rules}" var="rule">
								<c:out value="${rule.name}" />
								</br>
							</c:forEach></td>
						<td><c:out value="${t.text}" /></td>
						<td><c:out value="${t.address}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>