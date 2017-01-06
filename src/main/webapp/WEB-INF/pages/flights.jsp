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
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>
    <style>body {
        background-image: url("<c:url value="/resources/images/javajet-tr.png"/>")
    }</style>

</head>

<body>
<div class="container-fluid">
    <%--<c:import url="navbar.jsp"/>--%>
    <%@include file="templates/navbar.jsp" %>

    <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.time"/></th>
                    <th><fmt:message key="shared.localtime"/></th>
                    <th><fmt:message key="shared.from"/></th>
                    <th><fmt:message key="shared.to"/></th>
                    <th><fmt:message key="flights.aircraft.type"/></th>
                    <th><fmt:message key="flights.aircraft.number"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td>${flight.id}</td>
                        <td><fmt:formatDate value="${flight.departureTime}"
                                            pattern="yyyy-MMM-dd HH:mm" timeZone="UTC"/></td>
                        <td><lt:localTime value="${flight.departureTime}" pattern="HH:mm"
                                          timeZone="${flight.departureTimezone}"/></td>
                        <td>${flight.departure.iataCode}</td>
                        <td>${flight.destination.iataCode}</td>
                        <td>${flight.aircraft.model}</td>
                        <td>${flight.aircraft.regNumber}</td>

                        <td>
                            <form action="<c:url value="/flight"/>">
                                <input type="hidden" name="flightId" value="${flight.id}">
                                <input type="submit" class="btn btn-xs  btn-default"
                                       value="<fmt:message key="flights.button.edit"/>">
                            </form>
                        </td>


                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-1 col-sm-offset-9">
            <a href="<c:url value="/add-flight"/>"
               class="btn btn-default btn-info active" role="button">
                <fmt:message key="flights.button.add-flight"/>
            </a>
        </div>
    </div>
</div>
<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>

</html>