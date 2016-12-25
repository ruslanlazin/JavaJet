<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>


<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>


</head>

<body>
<%--<c:import url="navbar.jsp"/>--%>
<%@include file="navbar.jsp" %>


<form class="register-form" action="register" method="POST" id="registerForm">

    <input type="text" placeholder="name" name="username" id="username" autocomplete="off" required/>
    <input type="password" placeholder="password" name="password" id="password" required/>
    <input type="email" placeholder="email address" name="email" id="email" autocomplete="off" required/>
    <input type="text" placeholder="Your Name" name="name" id="name" required/>
    <input type="text" placeholder="Your Surname" name="surname" id="surname" required/>
    <button id = "create-button">create</button>
</form>


<a href="<c:url value="/flights"/>">flights</a> <br>
<a href="<c:url value="/employees"/>">employees</a><br>
<a href="<c:url value="/aircrafts"/>">aircrafts</a><br>
<br>

<a href="<c:url value="/flight"/>">flight</a><br>
<a href="<c:url value="/employee"/>">employee</a><br>
<a href="<c:url value="/aircraft"/>">aircraft</a><br>
<br>

<a href="<c:url value="/add-flight"/>">add-flight</a><br>
<a href="<c:url value="/add-employee"/>">add-employee</a><br>
<a href="<c:url value="/add-aircraft"/>">add-aircraft</a><br>




</body>
</html>