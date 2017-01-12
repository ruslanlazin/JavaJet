<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaJet</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css" />" rel="stylesheet"/>
</head>

<body>
<div class="container-fluid">
    <%--<c:import url="navbar.jsp"/>--%>
    <%@include file="templates/navbar.jsp" %>

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
                <c:forEach var="aircraft" items="${aircrafts}">
                    <form>
                        <tr>
                            <td>
                                <input type="hidden" class="form-control"
                                       id="aircraft${aircraft.id}"
                                       name="aircraftId" value="${aircraft.id}" required/>
                                    ${aircraft.id}
                            </td>
                            <td>
                                <input type="text" class="form-control"
                                       id="reg_${aircraft.id}"
                                       name="regNumber" disabled
                                       value="${aircraft.regNumber}" required/>
                            </td>
                            <td>
                                <input type="text" class="form-control"
                                       id="model_${aircraft.id}"
                                       name="model" disabled
                                       value="${aircraft.model}" required/>
                            </td>
                            <td>
                                <input type="button" class="btn-link btn active"
                                       id="edit_${aircraft.id}"
                                       onclick="enable(${aircraft.id})"
                                       value="<fmt:message key="shared.edit"/> "/>
                            </td>
                            <td>
                                <input type="submit" class="btn-link btn active"
                                       id="submit_${aircraft.id}" style="visibility: hidden"
                                       value="<fmt:message key="shared.button.save"/> "/>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <form>
                    <tr>

                        <td>
                            #
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   id="reg_0"
                                   name="regNumber" disabled
                                   placeholder="" required/>
                        </td>
                        <td>
                            <input type="text" class="form-control"
                                   id="model_0"
                                   name="model" disabled
                                   placeholder="" required/>
                        </td>
                        <td>
                            <input type="button" class="btn-link btn active"
                                   id="edit_0"
                                   onclick="enable(0)"
                                   value="<fmt:message key="shared.edit"/> "/>
                        </td>
                        <td>
                            <input type="submit" class="btn-link btn active"
                                   id="submit_0" style="visibility: hidden"
                                   value="<fmt:message key="shared.button.save"/> "/>
                        </td>
                    </tr>
                </form>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--Footer--%>
<%@include file="templates/footer.html" %>
</body>
<script type="text/javascript">
    function enable(aircraftId) {
        document.getElementById("reg_" + aircraftId).removeAttribute("disabled");
        document.getElementById("model_" + aircraftId).removeAttribute("disabled");
        document.getElementById("submit_" + aircraftId).style.visibility = "visible";
        document.getElementById("edit_" + aircraftId).style.visibility = "hidden";
    }
</script>
</html>