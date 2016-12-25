<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>


<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>--%>
    <%--<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>--%>


</head>

<body>
<%--<c:import url="navbar.jsp"/>--%>
<div class="container-fluid">
    <%@include file="navbar.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-sm-offset-2 col-sm-4">
                <h4> Creating a new Employee record</h4>
            </div>
        </div>
        <form class="form-horizontal" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="username">Username:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="username" placeholder="Enter username">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="firstname">Firstname:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="firstname" placeholder="Enter firsname">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="secondname">Secondname:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="secondname" placeholder="Enter secondname">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-4">
                    <input type="email" class="form-control" id="email" placeholder="Enter email">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="sel1">Select Position:</label>
                <div class="col-sm-4">
                    <select class="form-control" id="sel1">
                        <c:forEach var="role" items="${roles}">
                            <option>${role.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-4">
                    <button type="submit" class="btn btn-default">Create</button>
                </div>
            </div>
        </form>


        <a href="<c:url value="/flights"/>">flights</a> <br>
        <a href="<c:url value="/employees"/>">employees</a><br>
        <a href="<c:url value="/aircrafts"/>">aircrafts</a><br>
        <br>

        <a href="<c:url value="/flight"/>">flight</a><br>
        <a href="<c:url value="/employee"/>">employee</a><br>
        <a href="<c:url value="/aircraft"/>">aircraft</a><br>
        <br>

        <a href="<c:url value="/add-flight"/>">add-flight</a><br>
        <a href="<c:url value="/add-employee"/>">add-employee</a><br>
        <a href="<c:url value="/add-aircraft"/>">add-aircraft</a><br>
    </div>
</div>
</body>
</html>