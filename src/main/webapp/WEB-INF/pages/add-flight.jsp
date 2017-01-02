<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/jquery.js/js/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/moment.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/bootstrap.min.js/js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/bootstrap-datetimepicker.min.js/js/bootstrap-datetimepicker.min.js" />"></script>
</head>

<body>
<div class="container-fluid">
    <%--Navbar. Also contains shared Locale Init Section and taglib Declarations--%>
    <%@include file="navbar.jsp" %>
    <%--Page Content--%>
    <div class="container">
        <%--Back Button--%>
        <div class="row">
            <a href="<c:url value="/flights"/>"><fmt:message key="shared.button.back"/></a>
        </div>
        <%--Header--%>
        <div class=" row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="add-flight.header"/> ${flight.id}</h4>
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
            <%--Input Time Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="time">
                    <fmt:message key="shared.time"/>:
                </label>
                <div class="input-group date col-sm-4" id="datetimepicker">
                    <input type='text' class="form-control" id="time"
                           name="departureTime" onkeydown="return false" required>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker').datetimepicker({
                        format: 'DD/MM/YYYY HH:mm',
                        defaultDate: '${flight.departureTime}',
                        minDate: moment(),
                        maxDate: moment().add(90, 'days')
                    });
                });
            </script>
            <%--Input From Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="from">
                    <fmt:message key="shared.from"/>:
                </label>
                <div class="input-group col-sm-4">
                    <input type="text" class="form-control" id="from" name="from"
                           value="${flight.departure.iataCode}" pattern="[A-Z]{3}" required>
                </div>
            </div>
            <%--Input To Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="to">
                    <fmt:message key="shared.to"/>:
                </label>
                <div class="input-group col-sm-4">
                    <input type="text" pattern="[A-Z]{3}" class="form-control" id="to" name="to"
                           value="${flight.destination.iataCode}" title="" required>
                </div>
            </div>
            <%--Select Aircraft Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="aircraft">
                    <fmt:message key="shared.aircraft"/>:
                </label>
                <div class="input-group col-sm-4">
                    <select class="form-control" id="aircraft" name="aircraft">
                        <c:forEach var="aircraft" items="${aircrafts}">
                            <option <c:if test="${aircraft.id == flight.aircraft.id}">
                                selected="selected"
                            </c:if>
                                    value="${aircraft.id}">${aircraft.regNumber} (${aircraft.model})
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <%--Create Button--%>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4">
                    <button type="submit" class="btn btn-default btn-info">
                        <fmt:message key="shared.button.create"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>