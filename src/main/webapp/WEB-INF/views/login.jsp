<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <title>Log In</title>
</head>
<body>
<div class="reg" style="background-color:green;width: 400px;height:auto;margin-left:auto;margin-right:auto">
    <form action="login" method="POST" >
        <div >
            <label for="log1" style="display: inline-block;width: 150px;text-align:right;">Login</label>
            <input type="text" name="login" id="log1" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;">
            <label  style="display: inline-block;width: 150px;text-align:right" for="pass1">Password</label>
            <input type="text" name="password" id="pass1" style="margin-left:auto;margin-right:auto;">

        </div>
        <div style="padding-top:10px;margin-right:auto;margin-left:auto; width:110px">
            <input  type="submit" name="Login" value="Login" >
        </div>

    </form>
</div>
</body>
</html>
