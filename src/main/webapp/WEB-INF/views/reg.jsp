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
    <title>Registraion</title>
</head>
<body>
<c:if test="${errorMessage!=null}">
    <div style="background-color:red;width: 400px;height:auto;margin-left:auto;margin-right:auto;color:white;text-align: center">
            ${errorMessage}
    </div>
</c:if>
<div class="reg" style="background-color:green;width: 400px;height:auto;margin-left:auto;margin-right:auto">
    <form action="registration" method="POST">
        <div >
            <label for="log1" style="display: inline-block;width: 150px;text-align:right;">Login</label>
            <input type="text" name="login" id="log1" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;">
            <label  style="display: inline-block;width: 150px;text-align:right" for="pass1">Password</label>
            <input type="password" name="password" id="pass1" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;">
            <label for="name" style="display: inline-block;width: 150px;text-align:right;">Name</label>
            <input type="text" name="name" id="name" style="margin-left:auto;margin-right:auto;">

        </div>

        <div style="padding-top:10px;">
            <label for="surname" style="display: inline-block;width: 150px;text-align:right;">Surname</label>
            <input type="text" name="surname" id="surname" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;">
            <label for="email" style="display: inline-block;width: 150px;text-align:right;">Email</label>
            <input type="text" name="email" id="email" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;">
            <label for="country" style="display: inline-block;width: 150px;text-align:right;">Country</label>
            <input type="text" name="country" id="country" style="margin-left:auto;margin-right:auto;">

        </div>

        <div style="padding-top:10px;">
            <label for="city" style="display: inline-block;width: 150px;text-align:right;">City</label>
            <input type="text" name="city" id="city" style="margin-left:auto;margin-right:auto;">
        </div>

        <div style="padding-top:10px;">
            <label for="address" style="display: inline-block;width: 150px;text-align:right;">Address</label>
            <input type="text" name="address" id="address" style="margin-left:auto;margin-right:auto;">
        </div>

        <div style="padding-top:10px;">
            <label for="postcode" style="display: inline-block;width: 150px;text-align:right;">Postcode</label>
            <input type="text" name="postcode" id="postcode" style="margin-left:auto;margin-right:auto;">
        </div>

        <div style="padding-top:10px;margin-right:auto;margin-left:auto; width:110px">
            <input  type="submit" name="Reg" value="Reg" >
        </div>
    </form>
</div>
</body>
</html>
