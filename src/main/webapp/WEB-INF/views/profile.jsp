<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" type="text/css" href="/resources/styles/main.css"/>
<script src="//code.jquery.com/jquery-2.1.0.min.js"></script>

<html>
<head>
    <title></title>
</head>
<body>

<div id="profile-container">

<div id="profile-top">
    <h1>Hello ${sessionScope.user.userName} <a href="/">pix home page</a> <a href="/upload">upload an image</a> <a
            href="<c:url value="/static/j_spring_security_logout" />"> Logout</a></h1>
</div>
<div id="profile-left">
    <b>ALBUMS</b><br/>
    <p id="all">All images</p>
    <c:forEach items="${sessionScope.albums}" var="album">
       <p class="album" id="${album.id}">${album.name}</p>
    </c:forEach>
</div>

<div id="profile-right">
    <table id="wideTable" align="center">
        <tr>
            <c:set var="count" value="-1" scope="page" />
            <c:forEach items="${pics}" var="picture">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:if test="${count%3==0}"><tr/><tr></c:if>

        <td width="100" class="${picture.album.id}"><img id="${picture.album.id}" content="image/jpeg"
                                                         src="<s:url value="/resources/Pictures/${picture.uuid}"/>"
                                                         width="300"/>

            <div id="${picture.album.id}">${picture.name}</div>

                </td>

            </c:forEach>
        </tr>

    </table>
</div>
</div>
<div class="test">lolo</div>

<script>
 $("p.album").click(function() {
     var id=$(this).attr('id');
     $("table#wideTable td").hide();
     $("."+id).show();
     $(".test").html(id);
  });
  $("#all").click(function(){
      $("table#wideTable td").show();
  });
</script>

</body>
</html>
