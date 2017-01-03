<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
Template for select Emploees for some position
--%>
<div class="form-group">
    <label class="control-label col-sm-2" for="${position}">
        ${label}:
    </label>
    <div class="input-group col-sm-4">
        <select class="form-control js-select" id="${position}"
                multiple="multiple" name="crew">
            <c:forEach var="employee" items="${employees}">
                <c:if test="${employee.role.title == position}">
                    <option
                            <c:if test="${flight.crew.contains(employee)}">
                                selected="selected"
                            </c:if>
                            value="${employee.id}">${employee.firstName} ${employee.secondName}
                    </option>
                </c:if>
            </c:forEach>
        </select>
    </div>
</div>
