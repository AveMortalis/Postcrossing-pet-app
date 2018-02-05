<%--
  Created by IntelliJ IDEA.
  User: Evexemeris
  Date: 21.01.2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Статистика</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/stats.css">
</head>
<body>
<table>
    <tr>
        <th>№</th>
        <th>От</th>
        <th>Кому</th>
        <th>Статус</th>
    </tr>
<c:forEach items="${parcels}" var="parcel">
    <tr>
        <td>${parcel.id}</td>
        <td>${parcel.mailer.name} ${parcel.mailer.surname},${parcel.mailer.address.countryName}</td>
        <td>${parcel.recipient.name} ${parcel.recipient.surname},${parcel.recipient.address.countryName}</td>
        <td>${parcel.status}</td>
    </tr>
</c:forEach>
</table>


<a href="index.jsp">На главную</a>
</body>
</html>
