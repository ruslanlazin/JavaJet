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
<%--<c:import url="navbar.jsp"/>--%>
<div class="container-fluid">
    <%@include file="templates/navbar.jsp" %>
    <%--Page context--%>
    <div class="container">

        <%--Header--%>
        <div class="row">
            <div class="col-sm-offset-2 col-sm-4">
                <c:choose>
                    <c:when test="${editMode}">
                        <h4><fmt:message key="edit-employee.header"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><fmt:message key="add-employee.header"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%--Success message--%>
        <div class="row">
            <c:if test="${success}">
                <div class="col-sm-offset-2 col-sm-4 alert alert-success">
                    <c:choose>
                        <c:when test="${editMode}">
                            <fmt:message key="shared.save.success"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="shared.add.success"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </div>

        <%--Concurrent modification message--%>
        <div class="row">
            <c:if test="${concurrent}">
                <div class="col-sm-offset-2 col-sm-4 alert alert-warning">
                    <fmt:message key="shared.concurrent"/>
                </div>
            </c:if>
        </div>

        <%--Form--%>
        <form class="form-horizontal" method="POST"
                <c:if test="${editMode}">
                    action="/admin/employee/edit"
                </c:if>>
            <input type="hidden" name="userId" value="${employee.id}">
            <input type="hidden" name="version" value="${employee.version}">

            <%--Username field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="username">
                    <fmt:message key="shared.username"/>:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="username" name="username"
                           value="${employee.username}" required>
                </div>
                <c:if test="${wrongusername}">
                    <span class="text-danger">
                        <fmt:message key="add-employee.wrong.username"/>
                    </span>
                </c:if>
            </div>

            <%--Password field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">
                    <fmt:message key="shared.password"/>:
                </label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pwd" name="password"
                           value="${employee.password}" required>
                </div>
            </div>

            <%--First Name--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="firstname">
                    <fmt:message key="shared.firstname"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="firstname" name="firstname"
                           value="${employee.firstName}" required>
                </div>
            </div>

            <%--Second name field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="secondname">
                    <fmt:message key="shared.secondname"/>:
                </label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="secondname" name="secondname"
                           value="${employee.secondName}" required>
                </div>
            </div>

            <%--Email field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">
                    <fmt:message key="shared.email"/>:
                </label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="email" name="email"
                           value="${employee.email}" autocomplete="off" required>
                </div>
                <c:if test="${wrongemail}">
                    <span class="text-danger">
                        <fmt:message key="add-employee.wrong.email"/>
                    </span>
                </c:if>
            </div>

            <%--Position selection field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="position">
                    <fmt:message key="shared.position"/>:
                </label>
                <div class="col-sm-4">
                    <select class="form-control" id="position" name="position">
                        <c:forEach var="position" items="${positions}">
                            <option value="${position.id}"
                                    <c:if test="${position.id == employee.position.id}">
                                        selected="selected"
                                    </c:if>
                            >${position.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <%--Roles selection field--%>
            <div class="form-group">
                <label class="control-label col-sm-2" for="roles">
                    <fmt:message key="shared.roles"/>:
                </label>
                <div class="col-sm-4">
                    <select multiple required class="form-control" id="roles" name="userRoles">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}"
                                    <c:forEach var="userRole" items="${employee.roles}">
                                        <c:if test="${role.id == userRole.id}">
                                            selected="selected"
                                        </c:if>
                                    </c:forEach>
                            >${role.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <%--Working now checkbox--%>
            <div class="checkbox col-sm-2 col-sm-offset-2">
                <label>
                    <input type="checkbox" name="working" value="true"
                    <c:if test="${employee.working}">
                           checked="checked"
                    </c:if> >
                    <fmt:message key="shared.working"/>
                </label>
            </div>

            <%--Create button--%>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4">
                    <button type="submit" class="btn btn-default btn-info">
                        <fmt:message key="shared.button.save"/>
                    </button>
                </div>
            </div>

        </form>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>