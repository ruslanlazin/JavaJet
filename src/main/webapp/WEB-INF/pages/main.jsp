<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>


</head>

<body>
<%--<c:import url="navbar.jsp"/>--%>
<%@include file="templates/navbar.jsp" %>



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



<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>