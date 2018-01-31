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
   <span>Введите в поле регистрационный код, указанный на полученной вами открытке</span>
    <form action="regParcel" method="POST">
        <div >
            <label for="regcode" style="display: inline-block;width: 150px;text-align:right;">Регистрационный код</label>
            <input type="text" name="regcode" id="regcode" style="margin-left:auto;margin-right:auto;">

        </div>


        <div style="padding-top:10px;margin-right:auto;margin-left:auto; width:110px">
            <input  type="submit" name="Reg" value="Reg" >
        </div>
    </form>
</div>
</body>
</html>
