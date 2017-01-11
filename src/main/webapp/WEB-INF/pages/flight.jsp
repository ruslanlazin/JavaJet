<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lt" uri="http://lazin.pp.ua/localtime" %>

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
    <%@include file="templates/navbar.jsp" %>

    <%--Page Content--%>
    <div class="container">

        <%--Back Button--%>
        <div class="row">
            <a href="<c:url value="/schedule"/>"><fmt:message key="shared.button.back"/></a>
        </div>

        <%--Header--%>
        <div class=" row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="flight.header"/> ${flight.id}
                    <sec:authorize role="ROLE_ADMIN">
                        <form action="<c:url value="/edit/flight"/>">
                            <input type="hidden" name="flightId" value="${flight.id}">
                            <input type="submit" class="btn btn-xs  btn-link"
                                   value="<fmt:message key="flights.button.edit"/>">
                        </form>
                    </sec:authorize>
                </h4>
            </div>
        </div>


        <%--Flight table--%>
        <table class="table table-striped">
            <tbody>

            <%--UTC Time Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="shared.time"/>:
                </td>
                <td class="col-sm-4">
                    <fmt:formatDate value="${flight.departureTime}"
                                    pattern="dd-MMM-yyyy HH:mm" timeZone="UTC"/>
                </td>
            </tr>

            <%--Local Time Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="shared.localtime"/>:
                </td>
                <td class="col-sm-4">
                    <lt:localTime value="${flight.departureTime}" pattern="dd-MMM-yyyy HH:mm"
                                  timeZone="${flight.departureTimezone}" locale="${language}"/>
                </td>
            </tr>

            <%--From Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="shared.from"/>:
                </td>
                <td class="col-sm-4">
                    <b>${flight.departure.iataCode}</b><br>
                    ${flight.departure.name} <fmt:message key="shared.airport"></fmt:message>,
                    ${flight.departure.city} ,
                    ${flight.departure.country}<br>
                    GPS coordinates: <br>
                    Latitude: ${flight.departure.latitude},
                    Longitude: ${flight.departure.longitude}
                </td>
            </tr>

            <%--To Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="shared.to"/>:
                </td>
                <td class="col-sm-4">
                    <b>${flight.destination.iataCode}</b><br>
                    ${flight.destination.name} <fmt:message key="shared.airport"></fmt:message>,
                    ${flight.destination.city} <fmt:message key="shared.city"></fmt:message>,
                    ${flight.destination.country}<br>
                    GPS coordinates: <br>
                    Latitude: ${flight.destination.latitude}&deg;,
                    Longitude: ${flight.destination.longitude}&deg;
                </td>
            </tr>

            <%--Aircraft Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="shared.aircraft"/>:
                </td>
                <td class="col-sm-4">
                    ${flight.aircraft.regNumber} (${flight.aircraft.model})
                </td>
            </tr>

            <%--Distance Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="flight.distance"/>:
                </td>
                <td class="col-sm-4" id="distance"></td>
            </tr>

            <%--Flight time Field--%>
            <tr>
                <td class="col-sm-2">
                    <fmt:message key="flight.flighttime"/>:
                </td>
                <td class="col-sm-4" id="flightTime"></td>
            </tr>
            </tbody>
        </table>


        <%--Crew table header--%>
        <div class=" row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="edit-flight.crew"/>
                    <sec:authorize role="ROLE_DISPATCHER">
                        <form action="<c:url value="/edit/crew"/>">
                            <input type="hidden" name="flightId" value="${flight.id}">
                            <input type="submit" class="btn btn-xs  btn-link"
                                   value="<fmt:message key="flights.button.edit"/>">
                        </form>
                    </sec:authorize>
                </h4>
            </div>
        </div>

        <%--Crew table--%>
        <div class="table-responsive">
            <table class="table table-striped col-sm-4">
                <tbody>
                <%--View Pilots Field--%>
                <c:set var="position" value="Pilot" scope="page"/>
                <fmt:message key="edit-flight.pilots" var="label" scope="page"/>
                <%@include file="templates/employee.view.template.jsp" %>

                <%--View Navigation Officer Field--%>
                <c:set var="position" value="Navigating Officer" scope="page"/>
                <fmt:message key="edit-flight.navi" var="label" scope="page"/>
                <%@include file="templates/employee.view.template.jsp" %>

                <%--View Flight Attendants Field--%>
                <c:set var="position" value="Flight Attendant" scope="page"/>
                <fmt:message key="edit-flight.attendant" var="label" scope="page"/>
                <%@include file="templates/employee.view.template.jsp" %>

                <%--View  Radioman Field--%>
                <c:set var="position" value="Radioman" scope="page"/>
                <fmt:message key="edit-flight.radioman" var="label" scope="page"/>
                <%@include file="templates/employee.view.template.jsp" %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>