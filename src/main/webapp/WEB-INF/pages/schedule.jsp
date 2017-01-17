<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="lt" uri="http://lazin.pp.ua/localtime" %>
<%@ taglib prefix="sec" uri="http://lazin.pp.ua/access" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
</head>

<body>
<div class="container-fluid">
    <%--Navbar. Also contains shared Locale Init Section--%>
    <%@include file="templates/navbar.jsp" %>

    <%--Page context--%>
    <div class="row">
        <div class="col-sm-2 col-sm-offset-3">
            <c:choose>
                <c:when test="${requestScope.myMode}">
                    <h4><fmt:message key="flights.my.header"/></h4>
                </c:when>
                <c:otherwise>
                    <h4><fmt:message key="flights.header"/></h4>
                </c:otherwise>
            </c:choose>
        </div>
        <sec:authorize role="ROLE_ADMIN">
            <div class="col-sm-1 col-sm-offset-10">
                <a href="<c:url value="/admin/archive"/>"
                   class="btn btn-link " role="button">
                    <fmt:message key="shared.archive"/>
                </a>
            </div>
        </sec:authorize>
    </div>
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
                    <th><fmt:message key="shared.aircraft.type"/></th>
                    <th><fmt:message key="shared.aircraft.number"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.flights}" var="flight">
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

                        <sec:authorize role="ROLE_ADMIN">
                            <td>
                                <form action="<c:url value="/admin/flight/edit"/>">
                                    <input type="hidden" name="flightId" value="${flight.id}">
                                    <input type="submit" class="btn btn-xs  btn-default"
                                           value="<fmt:message key="shared.edit"/>">
                                </form>
                            </td>
                        </sec:authorize>

                        <sec:authorize role="ROLE_DISPATCHER">
                            <td>
                                <form action="<c:url value="/dispatcher/crew/edit"/>">
                                    <input type="hidden" name="flightId" value="${flight.id}">
                                    <input type="submit" class="btn btn-xs  btn-default"
                                           value="<fmt:message key="flights.button.crew"/>">
                                </form>
                            </td>
                        </sec:authorize>

                        <td>
                            <form action="<c:url value="/common/flight"/>">
                                <input type="hidden" name="flightId" value="${flight.id}">
                                <input type="submit" class="btn btn-xs  btn-default"
                                       value="<fmt:message key="flights.button.view"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <sec:authorize role="ROLE_ADMIN">
        <div class="row">
            <div class="col-sm-1 col-sm-offset-10">
                <a href="<c:url value="/admin/flight/edit"/>"
                   class="btn btn-default btn-info active" role="button">
                    <fmt:message key="flights.button.add-flight"/>
                </a>
            </div>
        </div>
    </sec:authorize>
    <br>
</div>
<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>

</html>