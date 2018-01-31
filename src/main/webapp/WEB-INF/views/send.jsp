<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>

<c:if test="${errorMessage!=null}">
    <div style="background-color:red;width: 400px;height:auto;margin-left:auto;margin-right:auto;color:white;text-align: center">
            ${errorMessage}
    </div>
</c:if>

<c:if test="${auth!=true || auth==null }">

    <a href="reg.jsp" class="register">REGISTRATION</a>
    <span> / </span>
    <br>
    <a href="login.jsp" class="sign_in">LOG IN</a>

</c:if>
<c:if test="${auth==true}">

    <span> Отправь октрытку  ${recipient.name} ${recipient.surname} </span>
    <br>
    <span> Он(а) проживает в  ${recipient.address.countryName} в городе ${recipient.address.city} </span>
    <br>
    <span> По адрессу ${recipient.address.address} </span>
    <br>
    <span> Почтовый индекс [${recipient.address.postcode}]  </span>
    <br>
    <span> И не забудь указать -> [${regcode}] <- этот код на открытке  </span>
    <br>

    <a href="index.jsp">На главную</a>
</c:if>


</body>
</html>