<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Simple-list App [persons-list.jsp]</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div>
    <h1>List of persons</h1>
</div>
<div>
    <form action="PrintPersonsList" method="get">
        <label>Name or surname: </label>
        <input type="text" name="keyword" value="${sessionScope.enteredKeyword}">
        <input type="submit" name="filter-submit" value="Filter">
    </form>
</div>

<display:table name="${sessionScope.personsList}" pagesize="10">
    <display:column property="id" title="ID" sortable="true"/>
    <display:column property="name" title="Name" sortable="true"/>
    <display:column property="surname" title="Surname" sortable="true"/>
    <display:column property="birthDate" title="Birthdate" sortable="true"/>
    <display:column property="email" title="E-mail" sortable="true"/>
</display:table>

<%@ include file="go-back.jsp" %>
</body>
</html>
