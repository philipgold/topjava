<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Add new meal</title>
</head>
<body>

<div class="container">
    <h3><a href="meals?action=list">Meals</a></h3>
    <h2>Add new meal</h2>
    <form class="form-horizontal" method="POST" action='meals' name="frmAddMeal">
        <input type="hidden" readonly="readonly" class="form-control" id="inputMealId" name="inputMealId" placeholder="ID" value="<c:out value="${meal.id}"/>">
        <div class="form-group">
            <label for="inputDateTime" class="col-sm-2 control-label">Date Time</label>
            <div class="col-sm-10">
                <div class='input-group date'   >
                    <input type='text'  class="form-control" id="inputDateTime" name="inputDateTime" placeholder="Date Time" value="<c:out value="${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}" />" />
                    <span class="input-group-addon">
                            <span class="glyphicon glyphicon-time"></span>
                        </span>
                </div>
            </div>

        </div>
        <div class="form-group">
            <label for="inputDescription" class="col-sm-2 control-label">Description</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputDescription" name="inputDescription" placeholder="Description" value="<c:out value="${meal.description}" />">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCalories" class="col-sm-2 control-label">Calories</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" id="inputCalories" name="inputCalories" placeholder="Calories" value="<c:out value="${meal.calories}" />">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" class="btn btn-default" value="Submit"/>
            </div>
        </div>
    </form>

</div>
</body>
</html>
