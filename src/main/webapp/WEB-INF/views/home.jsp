<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="/resources/styles/main.css"/>


<div id="left">
	<table id="wideTable" align="center">
		<tr>
			<td colspan="2">
				<table id="picTable">
                    <tr>
					<c:forEach items="${pics}" var="picture">

							<td width="100" ><img src="<s:url value="resources/Pictures/${picture.uuid}"/>" width="300" />
                                <p>${picture.name}</p>

							</td>

					</c:forEach>
                    </tr>

				</table>
			</td>
		</tr>
		<tr>

		</tr>
	</table>
</div>

<div id="right">
<%--<% if (session.getAttribute("username")==null){%>--%>
<c:choose>
    <c:when test="${sessionScope.username==null}">
        <jsp:include page="login.jsp" flush="false"></jsp:include>
    </c:when>
    <c:otherwise>
    <div id="profile-right">
    <h1> Hello,${sessionScope.username} </h1>
    <br/><br/>
    <h3><a href="/upload" style="">upload a picture</a></h3>
    <br/><br/>
    <h3><a href="/users/${username}" style="">profile</a></h3>
        <br/><br/>

        <h3><a href="/${username}" style="">profile (using ajax)</a></h3>
        <br/><br/>

        <h3><a href="<c:url value="/static/j_spring_security_logout" />">Logout</a>
        </h3>

    </div>
    </c:otherwise>
</c:choose>
</div>
<%--<% }%>--%>


