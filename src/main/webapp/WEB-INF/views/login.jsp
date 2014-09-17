
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <table id="right">
        <tr>
            <td>
                <form method="post" action="/">
                    <table class="body">
                        <tr>
                            <th align="right"><label for="username">Username: </label></th>
                            <td align="left"><input id="username"
                                                    name="j_username" type="text" /></td>
                        </tr>
                        <tr>
                            <th align="right"><label for="password">Password: </label></th>
                            <td align="left"><input id="password" name="j_password"
                                                    type="password" />
                        </tr>
                        <tr>
                            <p style="color: red"> ${message}</p>
                        </tr>

                        <tr>
                            <td align="right"><small><a
                                    href="/pix/account/resendPassword">Forgotten your password?</a></small></td>
                            <td><input name="commit" type="submit" value="Sign In"
                                       class="submit" /></td>
                        </tr>
                        <tr><td colspan="2"> <a href="register">Don't have account yet? Sign up!</a></td></tr>

                    </table>
                </form></td>

        </tr>
    </table>

    <script type="text/javascript">
        document.getElementById('username').focus();
    </script>

</html>
