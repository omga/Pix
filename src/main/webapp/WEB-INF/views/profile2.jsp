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
        <h1>Hello ${sessionScope.user.userName}             <a href="/" >pix home page</a>              <a href="/upload">upload an image</a>    </h1>
    </div>
    <div id="profile-left">
        <b>ALBUMS</b><br/>
        <c:forEach items="${sessionScope.albums}" var="album">
            <button class="button"id="${album.id}">${album.name}</button>
        </c:forEach>
    </div>

    <div id="profile-right">
        <table id="wideTable" align="center">


        </table>
    </div>
</div>
<div class="test">lolo</div>

<script>
    $(document).ready(function() {
        $("button").click(function () {
            var idd=$(this).attr('id');
            //$(".test").html(idd);
            $.ajax( {type: "GET",
                url: "/picture-ajax",
                dataType: "json",
                data: {id:idd},
                contentType:"application/json",
                success: function(pictures) {
                    $("#wideTable").html('');
                    var table='<tr>';
                    for ( var i = 1, l = pictures.length+1; i < l; i++ ) {
                        table+='<td><img content="image/jpeg" src="/resources/Pictures/'+pictures[i-1].uuid+'" width="300" /><p>'+pictures[i-1].name+'</p></td>';
                        if(i%3==0) {table+='<tr/><tr>';console.log(i);}
                    }
                    table+='</tr>';
                    $("#wideTable").append(table);

                    var pic1=pictures[0].uuid;
                    //$(".test").html(pic1);
                    //$("#wideTable").append('<img content="image/jpeg" src="/resources/Pictures/'+pic1+'" width="300" />');
                },
                error: function (request, textStatus, errorThrown) {
                    if (textStatus == 'error') {
                        $(".test").html(request.responseText).fadeOut(50000);
                    }else{
                        $(".test").html(request.responseText).fadeOut(50000);
                        alert('error');}
                }
            });
        });
    });

</script>

</body>
</html>
