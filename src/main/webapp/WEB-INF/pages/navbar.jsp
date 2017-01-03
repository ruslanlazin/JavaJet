<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="<c:url value="/resources/css/navbar.css" />" rel="stylesheet"/>
<link rel="icon" type="image/png" href="<c:url value="/resources/images/favicon.ico" />">

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/" />">JavaJet AirLines</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/flights"/>"><fmt:message key="shared.flights"/></a></li>
            <li><a href="<c:url value="/employees"/>"><fmt:message key="shared.employees"/></a></li>
            <li><a href="<c:url value="/aircrafts"/>"><fmt:message key="shared.aircrafts"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form class="form-inline float-xs-right">
                    <c:if test="${not empty flight}">
                        <input type="hidden" name="flightId" value="${flight.id}"/>
                    </c:if>
                    <select id="lang" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
            </li>
            <li><a href="/settings">Settings</a></li>
            <c:if test="${not empty user}">
                <li><a href="<c:url value="/logout"/>"><fmt:message key="navbar.link.logout"/></a></li>
            </c:if>
        </ul>
    </div>
</nav>

  


