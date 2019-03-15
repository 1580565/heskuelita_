<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>

</head>
<body>
<div class="main">
    <div class="container">
        <center>
            <div class="middle">
                <div id="login">
                    <h2 id="etiqueta-h2">User Login</h2>
                    <form action="login" method="post">
                        <fieldset class="clearfix">
                            <p><span class="fa fa-user"></span>
                                <input type="text"  name="user" Placeholder="User name/email" required>
                            </p>
                            <!-- JS because of IE support; better: placeholder="Username" -->
                            <p><span class="fa fa-lock"></span>
                                <input type="password" name="password" Placeholder="Password" required>
                            </p>
                            <!-- JS because of IE support; better: placeholder="Password" -->
                            <div>
                                <span style="width:48%; text-align:left;  display: inline-block; font-size: 17px"><a class="small-text" href="Register_1.jsp">New User</a></span>
                                <span style="width:50%; text-align:right;  display: inline-block;"><input type="submit" value="Sign In"></span>
                            </div>
                        </fieldset>
                        <div class="clearfix"></div>
                    </form>
                    <div class="clearfix"></div>
                </div>
                <!-- end login -->
                <div class="logo">
                    <img src="Images/logo_white.png" alt="" />
                    <div class="clearfix"></div>
                </div>
            </div>
        </center>
    </div>
</div>
</body>
</html>