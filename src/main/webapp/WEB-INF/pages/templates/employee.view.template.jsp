<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
Template for view Crew members for some position
Variables position and label must be set before usage

--%>

<tr>
    <td class="col-sm-2">
        ${label}:
    </td>
    <td class="col-sm-4">
        <c:forEach var="employee" items="${requestScope.flight.crew}">
            <c:if test="${employee.position.title == position}">
                ${employee.firstName} ${employee.secondName}<br>
            </c:if>
        </c:forEach>
    </td>
</tr>




