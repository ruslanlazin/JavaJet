<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script defer src="<c:url value="/resources/js/distance.js"/>"></script>
<%--
Template for view Flight info section

author Ruslan Lazin
--%>

<%--Flight table--%>
<div class="col-sm-7">
    <table class="table table-striped">
        <tbody>

        <%--UTC Time Field--%>
        <tr>
            <td class="col-sm-2">
                <fmt:message key="shared.time"/>:
            </td>
            <td class="col-sm-4">
                <fmt:formatDate value="${requestScope.flight.departureTime}"
                                pattern="dd-MMM-yyyy HH:mm" timeZone="UTC"/>
            </td>
        </tr>

        <%--Local Time Field--%>
        <tr>
            <td class="col-sm-2">
                <fmt:message key="shared.localtime"/>:
            </td>
            <td class="col-sm-4">
                <lt:localTime value="${requestScope.flight.departureTime}" pattern="dd-MMM-yyyy HH:mm"
                              timeZone="${requestScope.flight.departureTimezone}" locale="${language}"/>
            </td>
        </tr>

        <%--From Field--%>
        <tr>
            <td class="col-sm-2">
                <fmt:message key="shared.from"/>:
            </td>
            <td class="col-sm-4">
                <b>${requestScope.flight.departure.iataCode}</b><br>
                ${requestScope.flight.departure.name} <fmt:message key="shared.airport"/>,
                ${requestScope.flight.departure.city} ,
                ${requestScope.flight.departure.country}<br>
                GPS coordinates: <br>
                Latitude: ${requestScope.flight.departure.latitude}&deg;,
                Longitude: ${requestScope.flight.departure.longitude}&deg;
                <input type="hidden" id="depart_latitude" value="${requestScope.flight.departure.latitude}">
                <input type="hidden" id="depart_longitude" value="${requestScope.flight.departure.longitude}">

            </td>
        </tr>

        <%--To Field--%>
        <tr>
            <td class="col-sm-2">
                <fmt:message key="shared.to"/>:
            </td>
            <td class="col-sm-4">
                <b>${requestScope.flight.destination.iataCode}</b><br>
                ${requestScope.flight.destination.name} <fmt:message key="shared.airport"/>,
                ${requestScope.flight.destination.city} ,
                ${requestScope.flight.destination.country}<br>
                GPS coordinates: <br>
                Latitude: ${requestScope.flight.destination.latitude}&deg;,
                Longitude: ${requestScope.flight.destination.longitude}&deg;
                <input type="hidden" id="dest_latitude" value="${requestScope.flight.destination.latitude}">
                <input type="hidden" id="dest_longitude" value="${requestScope.flight.destination.longitude}">
            </td>
        </tr>

        <%--Aircraft Field--%>
        <tr>
            <td class="col-sm-2">
                <fmt:message key="shared.aircraft"/>:
            </td>
            <td class="col-sm-4">
                ${requestScope.flight.aircraft.regNumber} (${requestScope.flight.aircraft.model})
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
</div>