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
            <form class="form-inline" method="POST">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="shared.roles"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="role" items="${roles}">
                        <tr>
                            <td> ${role.id} </td>
                            <td> ${role.title} </td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            # <input type="hidden" name="type" value="role">
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   name="title" required/>
                        </td>
                        <td>
                            <input type="submit" class="btn-link btn active"
                                   value="<fmt:message key="shared.button.create"/> "/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <br><br>

            <%--Position Section--%>
            <form class="form-inline" method="POST">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="shared.position"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="position" items="${positions}">
                        <tr>
                            <td> ${position.id} </td>
                            <td> ${position.title} </td>
                            <td> <c:if test="${position.airCrew}">
                                &#10004;
                            </c:if>
                             </td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>
                            # <input type="hidden" name="type" value="position">
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   name="title" required/>
                        </td>
                        <td>
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
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>