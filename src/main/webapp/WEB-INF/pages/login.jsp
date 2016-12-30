<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"/>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>
    <style>body {
        background-image: url("<c:url value="/resources/images/javajet.jpg"/>")
    }</style>

</head>

<body>
<%--<c:import url="navbar.jsp"/>--%>
<%@include file="navbar.jsp" %>


<div class="login-page">
    <div class="form">
        <font color="red">
				<span style="align: center">
					<c:if test="${not empty param['error']}">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                    </c:if>
				</span>
        </font>

        <form id="login-form" class="login-form" method="post">
            <c:if test="${wronglogin}"><p>Login or password are incorrect. Try again</p></c:if>
            <input id="login" type="text" name="login" autocomplete="off"
                   placeholder="<fmt:message key="shared.username"/>"/>
            <input type="password" name="password"
                   placeholder="<fmt:message key="shared.password"/>"/>
            <fmt:message key="login.button.submit" var="buttonValue"/>
            <button id="login-button" class="submit" type="submit">${buttonValue}</button>
        </form>
    </div>
</div>

</body>
</html>