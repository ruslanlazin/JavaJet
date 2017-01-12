<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>


</head>

<body>
<div class="container-fluid">
    <%--<c:import url="navbar.jsp"/>--%>
    <%@include file="templates/navbar.jsp" %>

    <div class="row">
        <div class="col-lg-8 col-lg-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.firstname"/></th>
                    <th><fmt:message key="shared.secondname"/></th>
                    <th><fmt:message key="shared.position"/></th>
                    <th><fmt:message key="shared.email"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="aircraft">
                    <tr>
                        <td>${aircraft.id}</td>
                        <td>${aircraft.firstName}</td>
                        <td>${aircraft.secondName}</td>
                        <td>${aircraft.position.title}</td>
                        <td>${aircraft.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-1 col-lg-offset-7">
            <a href="<c:url value="/add-employee"/>"
               class="btn btn-default btn-info active" role="button">
                <fmt:message key="employees.button.add-employee"/>
            </a>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>

</html>