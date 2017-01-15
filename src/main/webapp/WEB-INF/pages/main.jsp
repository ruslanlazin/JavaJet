<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <style>body {
        background-image: url("<c:url value="/resources/images/javajet-tr.png"/>")
    }</style>
</head>

<body>
<div class="container-fluid">
    <%@include file="templates/navbar.jsp" %>
    <div>

        <%--Greatings--%>
        <fmt:message key="main.hello"/>
        ${sessionScope.currentUser.firstName}
        ${sessionScope.currentUser.secondName},
        ${sessionScope.currentUser.position.title}
        <br>
        <fmt:message key="main.pleasure"/>
        <h2><fmt:message key="main.welcome"/></h2>
        <br>
        <fmt:message key="main.options"/>

        <%--Options--%>
        <ul class="list-group">
            <li class="list-group-item list-group-item-info">
                <a href="<c:url value="/common/schedule"/>"><fmt:message
                        key="shared.flights"/></a></li>
            <li class="list-group-item list-group-item-info">
                <a href="<c:url value="/common/employees"/>"><fmt:message
                        key="shared.employees"/></a></li>

            <sec:authorize role="ROLE_ADMIN">
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/admin/aircrafts"/>"><fmt:message
                            key="shared.aircrafts"/></a></li>
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/admin/archive"/>"><fmt:message
                            key="shared.archive"/></a></li>
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/admin/settings"/>"><fmt:message
                            key="shared.settings"/></a></li>
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/admin/flight/edit"/>"><fmt:message
                            key="flights.button.add-flight"/></a></li>
            </sec:authorize>

            <sec:authorize role="ROLE_CREW">
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/crew/mySchedule"/>"><fmt:message
                            key="navbar.link.myschedule"/></a></li>
            </sec:authorize>

            <sec:authorize role="ROLE_DISPATCHER">
                <li class="list-group-item list-group-item-info">
                    <a href="<c:url value="/dispatcher/employee/add"/>">
                        <fmt:message key="shared.add-employee"/></a></li>
            </sec:authorize>

            <li class="list-group-item list-group-item-warning">
                <a href="<c:url value="/logout"/>"><fmt:message
                        key="navbar.link.logout"/></a></li>
        </ul>
    </div>

    <%--Footer--%>
    <%@include file="templates/footer.html" %>
</div>
</body>
</html>