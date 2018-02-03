<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddPerson" method="post">
    <label>Imię</label><input name="name" value="${sessionScope.edited_person.name}" type="text"><br/><br/>
    <label>Nazwisko</label><input name="surname" value="${sessionScope.edited_person.surname}"
                                        type="text"><br/><br/>

    <label>Data urodzenia</label><input name="birthdate" value="${sessionScope.edited_person.birthDate}" type="date"><br/><br/>
    <label>Adres e-mail</label><input name="email" value="${sessionScope.edited_person.email}" type="email"><br/><br/>
    <input type="submit" name="add-person" value="Wyślij">
</form>
