<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<body>
<h2>${mess}</h2>

    <c:if test="${auth!=true || auth==null }">

            <a href="reg.jsp" class="register">REGISTRATION</a>
            <span> / </span>
            <br>
            <a href="login" class="sign_in">LOG IN</a>

    </c:if>
    <c:if test="${auth==true}">

            <span> Привет, ${user.name} ${user.surname} </span>
            <br>
            <span> Ты из ${user.address.countryName} из города ${user.address.city} </span>
            <br>
            <span> Твой адресс ${user.address.address} </span>
            <br>
            <span> Почтовый индекс [${user.address.postcode}]  </span>
            <br>
            <%--<a style="text-decoration: none;color:black;border:dotted" href="send">[Send a postcard]</a>--%>
            <ul>
                <li><a href="editUser.jsp"> Изменить личные данные</a></li>
                <li><a href="registerParcel.jsp"> Зарегистировать посылку</a></li>
                <li><a href="send">Отправить посылку</a></li>
                <li><a href="logout">Разлогиниться</a></li>
                <li><a href="parcelStats">Статистика отправлений</a></li>
            </ul>

    </c:if>


</body>
</html>
