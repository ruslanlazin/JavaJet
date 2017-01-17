<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Error page provides custom error message exposing
  @author Ruslan Lazin
--%>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
<%@include file="/WEB-INF/pages/templates/navbar.jsp" %>
<div class="text-center"> We are sorry, but Internal server error has occurred.
    <br>
    Try to use other account <a href="<c:url value="/logout"/>">Logout</a>
    <br>
    Or go to the main page <a href="<c:url value="/common/"/>">Main</a>
</div>
</div>
<%--Footer--%>
<%@include file="/WEB-INF/pages/templates/footer.html" %>
</body>
</html>
