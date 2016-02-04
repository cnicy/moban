<?xml version="1.0" encoding="utf-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>View Cart</title>
</head>
<body>
<h1>View Cart</h1>
<a href="${flowExecutionUrl}&_eventId=submit">Submit</a>

<h2>Products for Your Choice</h2>
    <table>
        <c:forEach var="item" items="${items}">
             <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
             </tr>
        </c:forEach>
    </table>
</body>
</html>