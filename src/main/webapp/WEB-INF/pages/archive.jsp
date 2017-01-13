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
    <%--<c:import url="navbar.jsp"/>--%>
    <%@include file="templates/navbar.jsp" %>
    <div class="row">
        <div class="col-sm-2 col-sm-offset-3">
            <h4>
                <fmt:message key="archive.header"/>
            </h4>
        </div>
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
                <c:forEach items="${flights}" var="employee">
                    <tr>
                        <td>${employee.id}</td>
                        <td><fmt:formatDate value="${employee.departureTime}"
                                            pattern="yyyy-MMM-dd HH:mm" timeZone="UTC"/></td>
                        <td><lt:localTime value="${employee.departureTime}" pattern="HH:mm"
                                          timeZone="${employee.departureTimezone}"/></td>
                        <td>${employee.departure.iataCode}</td>
                        <td>${employee.destination.iataCode}</td>
                        <td>${employee.aircraft.model}</td>
                        <td>${employee.aircraft.regNumber}</td>
                        <td>
                            <form action="<c:url value="/flight"/>">
                                <input type="hidden" name="flightId" value="${employee.id}">
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
    <br>
</div>
<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>

</html>