<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Meals</title>
</head>
<body>
<div class="container">
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal">
            <tr <c:out value="${meal.exceed ? 'class=danger' : 'class=success'}" />>
                <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}</td>
                <td><c:out value="${meal.description}" /></td>
                <td><c:out value="${meal.calories}" /> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>