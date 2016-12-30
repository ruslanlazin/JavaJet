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
                <label class="control-label col-sm-2" for="time">
                    <fmt:message key="shared.time"/>:
                </label>
                <div class="col-sm-4">
                    <input type="datetime-local" class="form-control" id="time"
                           name="departureTime"
                           required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="from1">
                    <fmt:message key="shared.from"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="from1" name="from"
                           value="" pattern="[a-zA-Z]{3}" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="to">
                    <fmt:message key="shared.to"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" pattern="[a-zA-Z]{3}" class="form-control" id="to" name="to"
                           value=""  title="" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="aircraft">
                    <fmt:message key="shared.aircraft"/>:
                </label>
                <div class="col-sm-4">
                    <select class="form-control" id="aircraft" name="aircraft">
                        <c:forEach var="aircraft" items="${aircrafts}">
                            <option value="${aircraft.id}">${aircraft.regNumber} (${aircraft.model})
                            </option>
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