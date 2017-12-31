<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/mealDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="meal.title"/></h3>
        <br/>
        <div class="row">
            <div class="col-sm-7">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form id="filtersForm" class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-3" for="startDate"><spring:message code="meal.startDate"/>:</label>
                                <div class="col-sm-3">
                                        <input class="form-control" name="startDate" id="startDate" value="${param.startDate}">
                                </div>
                                <label class="control-label col-sm-4" for="startTime"><spring:message code="meal.startTime"/>:</label>
                                <div class="col-sm-2">
                                        <input class="form-control" name="startTime" id="startTime" value="${param.startTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3" for="endDate"><spring:message code="meal.endDate"/>:</label>
                                <div class="col-sm-3">
                                    <input class="form-control" name="endDate" id="endDate" value="${param.endDate}">
                                </div>
                                <label class="control-label col-sm-4" for="endTime"><spring:message code="meal.endTime"/>:</label>
                                <div class="col-sm-2">
                                    <input class="form-control" name="endTime" id="endTime" value="${param.endTime}">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer text-right">
                        <a class="btn btn-primary" type="button" onclick="updateTable()">
                            <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="meal.add"/>
        </a>
        <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th><spring:message code="meal.dateTime"/></th>
                        <th><spring:message code="meal.description"/></th>
                    <th><spring:message code="meal.calories"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach items="${meals}" var="meal">
                    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                    <tr id="${meal.id}" class="${meal.exceed ? 'exceeded' : 'normal'}">
                        <td>
                                ${fn:formatDateTime(meal.dateTime)}
                        </td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                        <td><a class="delete"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                    </tr>
                </c:forEach>
            </table>
    </div>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><spring:message code="${meal.isNew() ? 'meal.add' : 'meal.edit'}"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><spring:message code="meal.dateTime"/>:</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="dateTime" placeholder="<spring:message code="meal.dateTime"/>" required >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><spring:message code="meal.description"/>:</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="<spring:message code="meal.description"/>" required >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3"><spring:message code="meal.calories"/></label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories" placeholder="1000" required >
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>