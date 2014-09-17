<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 21.08.2014
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.sql.*" %>
<%!Statement st=null;
    Connection cn=null;
%>
<%
    Class.forName("org.postgresql.Driver");
    cn=DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testcon","postgres","admin");
    <!-- database name= data, username=root, password=blank-->
    st=cn.createStatement();
%>
