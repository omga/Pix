<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 12.05.14
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
    <title>Create a Pix account</title>
    <link rel="stylesheet" type="text/css" href="/resources/styles/main.css"/>
</head>
<body>
<h1>Create a Pix account</h1>
<sf:form method="POST" modelAttribute="pixUser">
    <fieldset>
        <table cellspasing="0">
            <tr>
                <th><label for="user_name">Username: </label> </th>
                <td><sf:input path="userName" size="15" id="user_name" maxlength="15"/>
                    <small id="username_msg">No spaces please.</small>
                </td>
            </tr>
            <tr>
                <th><label for="first_name">First name: </label> </th>
                <td><sf:input path="firstName" size="15" id="first_name" /> </td>
            </tr>
            <tr>
                <th><label for="last_name">Last name: </label> </th>
                <td><sf:input path="lastName" size="15" id="last_name" /> </td>
            </tr>
            <tr>
                <th><label for="user_email">e-mail: </label> </th>
                <td><sf:input path="email" size="15" id="user_email"/> </td>
            </tr>
            <tr>
                <th><label for="user_password">Password: </label> </th>
                <td><sf:password path="password" size="30" id="user_password" />
                <small>6 characters or more (be tricky!)</small></td>
            </tr>
            <tr>
                <th></th>
                <td align="left"><input name="commit" type="submit"
                                        value="Create" class="submit" /></td>
            </tr>
        </table>
    </fieldset>
</sf:form>


</body>
</html>
