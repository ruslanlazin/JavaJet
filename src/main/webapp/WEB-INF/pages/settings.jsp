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

    <%--Page context--%>
    <div class="row">
        <div class="col-sm-5 col-sm-offset-1">

            <%--Roles section--%>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.roles"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="role" items="${roles}">
                    <tr>
                        <td> ${role.id} </td>
                        <td> ${role.title} </td>
                    </tr>
                </c:forEach>
                <tr>
                    <form class="form-inline" method="POST">
                        <td>
                            # <input type="hidden" name="set" value="role">
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   name="title" required/>
                        </td>
                        <td>
                            <input type="submit" class="btn-link btn active"
                                   value="<fmt:message key="shared.button.create"/> "/>
                        </td>
                    <%--</form>--%>
                </tr>
                </tbody>
            </table>
            <br><br>

            <%--Position Section--%>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.position"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="position" items="${positions}">
                    <tr>
                        <td> ${position.id} </td>
                        <td> ${position.title} </td>
                    </tr>
                </c:forEach>
                <tr>
                    <form class="form-inline" method="POST">
                        <td>
                            # <input type="hidden" name="setType" value="position">
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   name="title" required/>

                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="aircrew" value="true">
                                    <fmt:message key="settings.air.crew.member"/>
                                </label>
                            </div>

                        </td>
                        <td>
                            <input type="submit" class="btn-link btn active"
                                   value="<fmt:message key="shared.button.create"/> "/>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>