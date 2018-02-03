<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Simple-list App [persons-list.jsp] #2</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/sorttable.js" type="text/javascript"></script>
</head>
<body>

<%--mini formularz--%>
<table border="1" class="sortable">
    <tr>
        <th>ID</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Data urodzenia</th>
        <th>Adres e-mail</th>
    </tr>
    <c:forEach var="item" items="${personsList}">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.surname}"/></td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<%@ include file="go-back.jsp" %>
</body>
</html>
