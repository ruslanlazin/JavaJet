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
                                   value="<fmt:message key="shared.edit"/>">
                        </form>
                    </sec:authorize>
                </h4>
            </div>
        </div>

        <%--View Flight info table--%>
        <%@include file="templates/flight.view.template.jsp" %>

        <%--Crew table header--%>
        <div class=" row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4><fmt:message key="shared.crew-assignment"/>
                    <sec:authorize role="ROLE_DISPATCHER">
                        <form action="<c:url value="/edit/crew"/>">
                            <input type="hidden" name="flightId" value="${flight.id}">
                            <input type="submit" class="btn btn-xs  btn-link"
                                   value="<fmt:message key="shared.edit"/>">
                        </form>
                    </sec:authorize>
                </h4>
            </div>
        </div>

        <%--Crew table--%>
        <%@include file="templates/crew.view.template.jsp" %>
    </div>
</div>
<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>