<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://lazin.pp.ua/access" %>
<%@ taglib prefix="lt" uri="http://lazin.pp.ua/localtime" %>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>

<%--Language selector init --%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty sessionScope.language ? sessionScope.language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/" />">
                <img class="img-responsive" src="<c:url value="/resources/images/logo-xs.png"/>">
            </a>
        </div>
        <ul class="nav navbar-nav">
            <sec:authorize role="ROLE_AUTHENTICATED">
                <li><a href="<c:url value="/common/schedule"/>"><fmt:message key="shared.flights"/></a></li>
                <li><a href="<c:url value="/common/employees"/>"><fmt:message key="shared.employees"/></a></li>
            </sec:authorize>
            <sec:authorize role="ROLE_ADMIN">
                <li><a href="<c:url value="/admin/aircrafts"/>"><fmt:message key="shared.aircrafts"/></a></li>
            </sec:authorize>
            <sec:authorize role="ROLE_CREW">
                <li><a href="<c:url value="/crew/mySchedule"/>"><fmt:message key="navbar.link.myschedule"/></a></li>
            </sec:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <%--Language selector--%>
                <form class="form-inline float-xs-right">
                    <c:if test="${not empty requestScope.flight}">
                        <input type="hidden" name="flightId" value="${requestScope.flight.id}"/>
                    </c:if>
                    <c:if test="${not empty requestScope.employee}">
                        <input type="hidden" name="userId" value="${requestScope.employee.id}"/>
                    </c:if>
                    <select id="lang" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
            </li>
            <sec:authorize role="ROLE_ADMIN">
                <li><a href="<c:url value="/admin/settings"/>"><fmt:message key="shared.settings"/></a></li>
            </sec:authorize>
            <sec:authorize role="ROLE_AUTHENTICATED">
                <li><a href="<c:url value="/logout"/>"><fmt:message key="navbar.link.logout"/></a></li>
            </sec:authorize>
        </ul>
    </div>
</nav>



