<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Simple-list app [index.jsp]</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div>
    <h1>Welcome in <i>Simple-list</i> application!</h1>
</div>

<c:if test="${errorMsg != null}">
    <div class="errorMessage">${errorMsg}</div>
</c:if>
<c:set var="enteredKeyword" scope="session" value=""/>

<%@ include file="menu.jsp" %>
<br>
<hr>
</body>
</html>
