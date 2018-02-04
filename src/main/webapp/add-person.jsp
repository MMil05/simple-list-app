<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Simple-list App [add-person.jsp]</title>
</head>
<body>
<div>
    <h1>Add a new person</h1>
</div>
<div>
    <form action="AddPerson" method="post">
        <table>
            <tr>
                <td><label>Name</label></td>
                <td><input name="name" value="" type="text" required></td>
            </tr>
            <tr>
                <td><label>Surname</label></td>
                <td><input name="surname" value="" type="text" required></td>
            </tr>
            <tr>
                <td><label>Birthdate</label></td>
                <td><input name="birthDate" value="" type="date" required></td>
            </tr>
            <tr>
                <td><label>E-mail</label></td>
                <td><input name="email" value="" type="email" required></td>
            </tr>
        </table>
        <input type="submit" name="add-person" value="Send">
    </form>
</div>
<%@ include file="go-back.jsp" %>
</body>
</html>
