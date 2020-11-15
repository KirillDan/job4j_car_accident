<%@page import="ru.job4j.accident.repository.AccidentMem"%>
<%@page import="ru.job4j.accident.model.Accident"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<%
	Integer id = (Integer) request.getAttribute("id");
	AccidentMem repository = (AccidentMem) request.getAttribute("repository");
	Accident accident = new Accident(0, "", "", "");
	if (id != null) {
		accident = repository.findById(id);
	}
	%>
	<form action="<c:url value='/save'/>" method='POST'>
		<input type="hidden" name="id" value="<%= accident.getId()%>">
		<table>
			<tr>
				<td>Название:</td>
				<td><input type='text' name='name'
					value="<%=accident.getName()%>"></td>
			</tr>
			<tr>
				<td>Текст:</td>
				<td><input type='text' name='text'
					value="<%=accident.getText()%>"></td>
			</tr>
			<tr>
				<td>Адрес:</td>
				<td><input type='text' name='address'
					value="<%=accident.getAddress()%>"></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Сохранить" /></td>
			</tr>
		</table>
	</form>
</body>
</html>