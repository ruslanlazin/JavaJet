<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link href="<c:url value='/resources/css/login.css' />" rel="stylesheet"/>
</head>

<body>

<div class="login-page">
    <div class="form">
        <font color="red">
				<span style="align: center">
					<c:if test="${not empty param['error']}">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                    </c:if>
				</span>
        </font>

        <form id="login-form" class="login-form" action="login" method="post">
            <c:if test="${wronglogin}"><p>Login or password are incorrect. Try again</p></c:if>
            <input id="login" type="text" name="login" placeholder="username" autocomplete="off"/>
            <input type="password" name="password" placeholder="password"/>
            <button id="login-button" class="submit" type="submit">Login</button>
        </form>
    </div>
</div>

</body>
</html>