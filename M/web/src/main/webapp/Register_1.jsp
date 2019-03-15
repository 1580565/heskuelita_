<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 3/8/2019
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>

</head>

<body>

<!------ Include the above in your HEAD tag ---------->
<div class="main">
    <div class="container register">
        <div class="row">
            <div class="col-md-3 register-left">
                <img src="Images/logo_white.png" alt="" />
                <h3>Welcome</h3>
                <input type="submit" name="" value="Login" />
                <br/>
            </div>
            <div class="col-md-9 register-right">
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane show active" id="home" aria-labelledby="home-tab">
                        <h3 class="register-heading">Apply as Student</h3>
                        <form action="NewUser" method="post">
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="first_name" class="form-control" placeholder="First Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" name="last_name" class="form-control" placeholder="Last Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control" placeholder="Password *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Confirm Password *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <div class="maxl">
                                            <label class="radio inline">
                                                <input type="radio" name="gender" value="male">
                                                <span> Male </span>
                                            </label>
                                            <label class="radio inline">
                                                <input type="radio" name="gender" value="female">
                                                <span>Female </span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="email" name="email" class="form-control" placeholder="Your Email *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" minlength="10" maxlength="10" name="phone_number" class="form-control" placeholder="Your Phone *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="date" class="form-control" name="birth_date" placeholder="Birth date / dd-mm-yyyy" value="" />
                                    </div>
                                    <div class="form-group">
                                        <select class="form-control" name="type_id">
                                            <option class="hidden"  selected disabled>Type Id</option>
                                            <option>DNI</option>
                                            <option>Passport</option>
                                            <option>Other</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="number_id" placeholder="Number id *" value="" />
                                    </div>
                                    <input type="submit" class="btnRegister" value="Register"/>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>