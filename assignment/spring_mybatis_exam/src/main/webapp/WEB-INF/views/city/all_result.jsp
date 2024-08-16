<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>City List</title>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">City List</h2>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th style="background: lightcyan; color: blue">ID</th>
            <th style="background: lightcyan; color: blue">Name</th>
            <th style="background: lightcyan; color: blue">Country Code</th>
            <th style="background: lightcyan; color: blue">District</th>
            <th style="background: lightcyan; color: blue">Population</th>
        </tr>
        </thead>
        <tbody class="thead-dark">
        <c:forEach var="city" items="${cities}">
            <tr>
                <th style="background: lightpink">${city.id}</th>
                <td style="background: lightpink">${city.name}</td>
                <td style="background: lightpink">${city.countryCode}</td>
                <td style="background: lightpink">${city.district}</td>
                <td style="background: lightpink">${city.population}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script></body>
</html>
