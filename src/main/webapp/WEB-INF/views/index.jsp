<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Accident</title>
</head>
<body>
	<div class="container">
		<h2>Таблица</h2>
		<table class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>имя</th>
					<th>фамилия</th>
					<th>пол</th>
					<th>описание</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${table}" var="t">
				<tr>
					<td><c:out value="${t.id}" /></td>
					<td><c:out value="${t.firstname}" /></td>
					<td><c:out value="${t.lastname}" /></td>
					<td><c:out value="${t.gender}" /></td>
					<td><c:out value="${t.description}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>