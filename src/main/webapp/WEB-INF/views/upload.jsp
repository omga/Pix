<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="/resources/styles/main.css"/>
<script src="//code.jquery.com/jquery-2.1.0.min.js"></script>

<html>
<head>
    <title></title>
</head>

<body>
<div id="profile-top">
    <h1>Hello, ${sessionScope.user.userName}  <a href="/" >pix home page</a>           </h1>
</div>
<table align="center">
    <tr><td>

        <b>Upload an imgage:</b><br/><br/>

        <form name="form1" id="uploadform" method="post" enctype="multipart/form-data" action="upload">
            <fieldset>
            <p>
                <input type="file" name="ImageFile" id="ImageFile" />
            </p>
            <p><label>Name</label>
                <input type="text" name="pic_name" id="name" />
            </p>
            <p><label>Comment</label>
                <input type="text" name="pic_comment" id="comment" />
            </p>
            <p>
                <label>Save to album:</label>

                <select name="album" form="uploadform">
                    <c:forEach items="${sessionScope.albums}" var="album">
                      <option name="album" value="${album.id}">${album.name}</option>
                    </c:forEach>
                </select>

            </p>

            <p>
                <input type="submit" class="button" value="submit" />
            </p>
            </fieldset>
        </form>

    </td>
    </tr>
    <td>
        <b>Add new album:</b><br/><br/>
    <form id="add-album" action="javascript:alert('submit')">
        <fieldset>
            <p>
                <label for="add-album-name">Name</label>
                <input align="left" id="add-album-name" name="name" type="text"/>
            </p>
            <p>
                <label for="add-album-description">Description</label>
                <input id="add-album-description" name="description" type="text"/>
            </p>


            <p>
                <input class="button" type="submit" value="Add"/>
            </p>

        </fieldset>
    </form>

    </td>
</table>

<div align="center" id="add-stock-flash-message" class="flash-message">

</div>

<script type="application/javascript">
    $("#add-album").submit(function() {

        $.ajax({
            type: "POST",
            url: '/album-ajax',
            data: $("#add-album").serialize(),

            success: function () {
                $("#add-stock-flash-message").show().html("ALBUM was added").fadeOut(5000);
            },

            error: function (request, textStatus, errorThrown) {
                if (textStatus == 'error') {
                    $("#add-stock-flash-message").html(request.responseText).fadeOut(5000);
                }
                else {
                    $("#add-stock-flash-message").show().html("Server error").fadeOut(5000);
                }
            }
        });
    });
</script>
</body>
</html>
