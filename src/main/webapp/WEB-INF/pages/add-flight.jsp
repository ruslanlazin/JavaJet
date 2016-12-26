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
<div class="container-fluid">
    <%@include file="navbar.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="add-flight.header"/></h4>
            </div>
        </div>
        <div class="row">
            <c:if test="${success}">
                <div class="col-sm-offset-2 col-sm-4 alert alert-success">
                    <fmt:message key="add-flight.success"/>
                </div>
            </c:if>
        </div>
        <form class="form-horizontal" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="username">
                    <fmt:message key="shared.username"/>:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="username" name="username"
                           value="${employee.username}" required>
                </div>
                <c:if test="${wrongusername}">
                    <span class="text-danger">
                        <fmt:message key="add-employee.wrong.username"/>
                    </span>
                </c:if>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">
                    <fmt:message key="shared.password"/>:
                </label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pwd" name="password"
                           required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="firstname">
                    <fmt:message key="shared.firstname"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="firstname" name="firstname"
                           value="${employee.firstName}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="secondname">
                    <fmt:message key="shared.secondname"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="secondname" name="secondname"
                           value="${employee.secondName}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="email">
                    <fmt:message key="shared.email"/>:
                </label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="email" name="email"
                           value="${employee.email}" autocomplete="off" required>
                </div>
                <c:if test="${wrongemail}">
                    <span class="text-danger">
                        <fmt:message key="add-employee.wrong.email"/>
                    </span>
                </c:if>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="role">
                    <fmt:message key="shared.position"/>:
                </label>
                <div class="col-sm-4">
                    <select class="form-control" id="role" name="role">
                        <c:forEach var="airport" items="${airports}">
                            <option  >${airport.iataCode}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4">
                    <button type="submit" class="btn btn-default btn-info">
                        <fmt:message key="shared.button.create"/>
                    </button>
                </div>
            </div>
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
    </div>
</div>
</body>
</html>