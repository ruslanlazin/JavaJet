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
    <link href="<c:url value="/resources/css/select2.min.css" />" rel="stylesheet"/>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/moment-with-locales.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/bootstrap-datetimepicker.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/lib/select2.min.js" />"></script>
    <script defer type="text/javascript" src="<c:url value="/resources/js/flight.js" />"></script>
</head>

<body>
<div class="container-fluid">
    <%--Navbar. Also contains shared Locale Init Section--%>
    <%@include file="navbar.jsp" %>

    <!-- Values passed to javascript -->
    <input type="hidden" id="departureTime" value="<fmt:formatDate
    pattern="yyyy-MM-dd'T'HH:mm" timeZone="UTC" value="${flight.departureTime}"/>"/>
    <input type="hidden" id="language" value="${language}"/>

    <%--Page Content--%>
    <div class="container">

        <%--Back Button--%>
        <div class="row">
            <a href="<c:url value="/flights"/>"><fmt:message key="shared.button.back"/></a>
        </div>

        <%--Header--%>
        <div class=" row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="edit-flight.header"/> ${flight.id}</h4>
            </div>
        </div>
        <div class="row">
            <c:if test="${success}">
                <div class="col-sm-offset-2 col-sm-4 alert alert-success">
                    <fmt:message key="edit-flight.success"/>
                </div>
            </c:if>
        </div>

        <%--Flight form--%>
        <form class="form-horizontal" method="POST">
            <input type="hidden" name="flightId" value="${flight.id}"/>

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

            <%--Input From Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="from">
                    <fmt:message key="shared.from"/>:
                </label>
                <div class="input-group col-sm-4">
                    <select class="form-control airport-select" id="from" name="from" required>
                        <option value="${flight.departure.iataCode}" selected="selected">
                            ${flight.departure.iataCode}
                        </option>
                    </select>
                </div>
            </div>

            <%--Input To Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="to">
                    <fmt:message key="shared.to"/>:
                </label>
                <div class="input-group col-sm-4">
                    <select class="form-control airport-select" id="to" name="to" required>
                        <option value="${flight.destination.iataCode}" selected="selected">
                            ${flight.destination.iataCode}
                        </option>
                    </select>
                </div>
            </div>

            <%--Select Aircraft Field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="aircraft">
                    <fmt:message key="shared.aircraft"/>:
                </label>
                <div class="input-group col-sm-4">
                    <select class="form-control js-select" id="aircraft" name="aircraft">
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

            <%--Crew assigntment header--%>
            <div class=" row">
                <div class="col-sm-offset-2 col-sm-4">
                    <h4><fmt:message key="edit-flight.crew"/></h4>
                </div>
            </div>

            <%--Select Pilots Field--%>
            <c:set var="position" value="Pilot" scope="page"/>
            <fmt:message key="edit-flight.pilots" var="label" scope="page"/>
            <%@include file="select.template.jsp" %>

            <%--Select Navigation Officer Field--%>
            <c:set var="position" value="Navigating Officer" scope="page"/>
            <fmt:message key="edit-flight.navi" var="label" scope="page"/>
            <%@include file="select.template.jsp" %>

            <%--Select Flight Attendants Field--%>
            <c:set var="position" value="Flight Attendant" scope="page"/>
            <fmt:message key="edit-flight.attendant" var="label" scope="page"/>
            <%@include file="select.template.jsp" %>

            <%--Select Radioman Field--%>
            <c:set var="position" value="Radioman" scope="page"/>
            <fmt:message key="edit-flight.radioman" var="label" scope="page"/>
            <%@include file="select.template.jsp" %>


            <%--Save Button--%>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4">
                    <button type="submit" class="btn btn-default btn-info" id="edit-btn">
                        <fmt:message key="shared.button.save"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>