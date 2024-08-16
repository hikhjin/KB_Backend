<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Page</title>
</head>
<body>
<h2>Add Product</h2>
<form action="/web04/product_insert" method="post">
    Product ID: <input type="text" name="id"/><br/>
    Name: <input type="text" name="name"/><br/>
    Price: <input type="text" name="price"/><br/>
    <input type="submit" value="Add"/>
</form>

<h2>Delete Product</h2>
<form action="/web04/product_delete" method="post">
    Product ID: <input type="text" name="id"/><br/>
    <input type="submit" value="Delete"/>
</form>
</body>
</html>

