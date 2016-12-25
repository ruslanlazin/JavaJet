<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="<c:url value="/resources/css/navbar.css" />" rel="stylesheet"/>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/" />">JavaJet AirLines</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/fr"/>">Friends</a></li>
            <li><a href="<c:url value="/lo"/>">Followers</a></li>
            <li><a href="<c:url value="/lo"/>">All</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form class="form-inline float-xs-right">
                    <select id="language" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
            </li>
            <li><a href="/settings">Settings</a></li>
            <li><a href="<c:url value="/logout"/>"><fmt:message key="navbar.link.logout"/> </a></li>
        </ul>
    </div>
</nav>

  


