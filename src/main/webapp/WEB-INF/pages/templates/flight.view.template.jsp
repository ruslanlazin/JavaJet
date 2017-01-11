<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</div>


