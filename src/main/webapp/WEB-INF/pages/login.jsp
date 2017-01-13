<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
    <style>body {
        background-image: url("<c:url value="/resources/images/javajet.jpg"/>")
    }</style>

</head>

<body>
<div class="container-fluid">
    <%@include file="templates/navbar.jsp" %>

    <%--TODO remove after alfa testing--%>
    <div class="col-sm-2" style="background-color: #d5d5d5">
        For testing purpose use:<br>
        a/a Administrator<br>
        d/d Dispatcher<br>
        p/p Pilot<br>
        g/g God
    </div>

    <div class="login-page">
        <div class="form">

            <font color="red">
				<span style="align: center">
                        <c:out value="${message}"/>
				</span>
            </font>

            <form id="login-form" class="login-form" method="post" action="<c:url value="/login"/>">
                <c:if test="${wronglogin}"><p>Login or password are incorrect. Try again</p></c:if>

                <%--TODO change patterns after alfa testingg--%>
                <input type="text" id="login" pattern="[\w]{1,}" required
                       name="login" autocomplete="off"
                       placeholder="<fmt:message key="shared.username"/>"/>

                <%--TODO change patterns after alfa testingg--%>
                <input type="password" pattern="[\w]{1,}" required name="password"
                       placeholder="<fmt:message key="shared.password"/>"/>
                <fmt:message key="login.button.submit" var="buttonValue"/>
                <button id="login-button" class="submit" type="submit">${buttonValue}</button>
            </form>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
</html>