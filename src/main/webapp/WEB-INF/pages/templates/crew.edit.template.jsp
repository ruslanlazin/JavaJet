<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
Template for view crew section

author Ruslan Lazin
--%>

<%--Crew table--%>
<div>
    <%--View Pilots Field--%>
    <c:set var="position" value="Pilot" scope="page"/>
    <fmt:message key="edit-flight.pilots" var="label" scope="page"/>
    <%@include file="employee.select.template.jsp" %>

    <%--View Navigation Officer Field--%>
    <c:set var="position" value="Navigating Officer" scope="page"/>
    <fmt:message key="edit-flight.navi" var="label" scope="page"/>
    <%@include file="employee.select.template.jsp" %>

    <%--View Flight Attendants Field--%>
    <c:set var="position" value="Flight Attendant" scope="page"/>
    <fmt:message key="edit-flight.attendant" var="label" scope="page"/>
    <%@include file="employee.select.template.jsp" %>

    <%--View Radioman Field--%>
    <c:set var="position" value="Radioman" scope="page"/>
    <fmt:message key="edit-flight.radioman" var="label" scope="page"/>
    <%@include file="employee.select.template.jsp" %>
</div>
