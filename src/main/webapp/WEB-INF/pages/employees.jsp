<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<div class="container-fluid">
    <%--<c:import url="navbar.jsp"/>--%>
    <%@include file="navbar.jsp" %>

    <div class="row">
        <div class="col-lg-8 col-lg-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.firstname"/></th>
                    <th><fmt:message key="shared.secondname"/></th>
                    <th><fmt:message key="shared.position"/></th>
                    <th><fmt:message key="shared.email"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="flight">
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.firstName}</td>
                        <td>${flight.secondName}</td>
                        <td>${flight.role.title}</td>
                        <td>${flight.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-1 col-lg-offset-7">
            <a href="<c:url value="/add-employee"/>"
               class="btn btn-default btn-info active" role="button">
                <fmt:message key="employees.button.add-employee"/>
            </a>
        </div>
    </div>

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

</div>


</body>
</html>