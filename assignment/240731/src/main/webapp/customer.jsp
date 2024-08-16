<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Page</title>
</head>
<body>
<h2>Add Customer</h2>
<form action="/web04/customer_insert" method="post">
    Customer ID: <input type="text" name="id"/><br/>
    Name: <input type="text" name="name"/><br/>
    Email: <input type="text" name="email"/><br/>
    <input type="submit" value="Add"/>
</form>

<h2>Delete Product</h2>
<form action="/web04/customer_delete" method="post">
    Customer ID: <input type="text" name="id"/><br/>
    <input type="submit" value="Delete"/>
</form>
</body>
</html>

