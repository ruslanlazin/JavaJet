<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/aircrafts.js"/>"></script>
</head>

<body>
<div class="container-fluid">
    <%--Navbar. Also contains shared Locale Init Section--%>
    <%@include file="templates/navbar.jsp" %>

    <%--Page context--%>
    <div class="row">
        <div class="col-lg-8 col-lg-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="shared.aircraft.number"/></th>
                    <th><fmt:message key="shared.aircraft.type"/></th>
                </tr>
                </thead>
                <tbody>
                <%--View existing aircrafts section--%>
                <c:forEach var="employee" items="${requestScope.aircrafts}">
                    <form method="POST">
                        <tr>
                            <td>
                                <input type="hidden" class="form-control"
                                       id="aircraft${employee.id}" required
                                       name="aircraftId" value="${employee.id}"/>
                                    ${employee.id}
                            </td>
                            <td>
                                <input type="text" class="form-control" pattern="[\w ]{4,20}"
                                       id="reg_${employee.id}" name="regNumber" disabled
                                       value="${employee.regNumber}" required/>
                            </td>
                            <td>
                                <input type="text" class="form-control" pattern="[\w ]{4,20}"
                                       id="model_${employee.id}" name="model" disabled
                                       value="${employee.model}" required/>
                            </td>
                            <td>
                                <sec:authorize role="ROLE_ADMIN">
                                    <input type="button" class="btn-link btn active"
                                           id="edit_${employee.id}"
                                           onclick="enable(${employee.id})"
                                           value="<fmt:message key="shared.edit"/> "/>
                                </sec:authorize>
                            </td>
                            <td>
                                <input type="submit" class="btn-link btn active"
                                       id="submit_${employee.id}" style="visibility: hidden"
                                       value="<fmt:message key="shared.button.save"/> "/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <%--Add Aircraft section--%>
                <sec:authorize role="ROLE_ADMIN">
                    <form method="POST">
                        <tr>
                            <td>
                                #
                            </td>
                            <td>
                                <input type="text" class="form-control" pattern="[\w ]{4,20}"
                                       id="reg_0" name="regNumber" disabled required
                                       placeholder="<fmt:message key="shared.aircraft.number"/>"/>
                            </td>
                            <td>
                                <input type="text" class="form-control" pattern="[\w ]{4,20}"
                                       id="model_0" name="model" disabled required
                                       placeholder="<fmt:message key="shared.aircraft.type"/>"/>
                            </td>
                            <td>
                                <input type="button" class="btn-link btn active"
                                       id="edit_0" onclick="enable(0)"
                                       value="<fmt:message key="shared.edit"/> "/>
                            </td>
                            <td>
                                <input type="submit" class="btn-link btn active"
                                       id="submit_0" style="visibility: hidden"
                                       value="<fmt:message key="shared.button.save"/> "/>
                            </td>
                        </tr>
                    </form>
                </sec:authorize>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>