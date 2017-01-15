<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <div class="col-lg-8 col-lg-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.firstname"/></th>
                    <th><fmt:message key="shared.secondname"/></th>
                    <th><fmt:message key="shared.position"/></th>
                    <th><fmt:message key="shared.email"/></th>
                    <th><fmt:message key="shared.working"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.secondName}</td>
                        <td>${employee.position.title}</td>
                        <td>${employee.email}</td>
                        <td>
                            <c:if test="${employee.working}">
                                &#10004;
                            </c:if>
                        </td>
                        <sec:authorize role="ROLE_DISPATCHER">
                            <td>
                                <form action="<c:url value="/dispatcher/employee/edit"/>">
                                    <input type="hidden" name="userId" value="${employee.id}">
                                    <input type="submit" class="btn btn-xs  btn-default"
                                           value="<fmt:message key="shared.edit"/>">
                                </form>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <sec:authorize role="ROLE_DISPATCHER">
            <div class="col-lg-1 col-lg-offset-7">
                <a href="<c:url value="/dispatcher/employee/add"/>"
                   class="btn btn-default btn-info active" role="button">
                    <fmt:message key="shared.add-employee"/>
                </a>
            </div>
        </sec:authorize>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>

</html>