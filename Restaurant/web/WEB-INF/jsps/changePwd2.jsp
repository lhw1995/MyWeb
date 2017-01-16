<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Contact</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all"/>

    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Fidele  Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!---->
    <script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/changePwd2.js"></script>
</head>
<body>
<%@include file="header.jsp" %>

<!--//header-->
<div class="banner-head">
    <div class="banner-1"></div>
    <div class="container">
        <h1>ChangeInfo</h1>
    </div>
</div>
<!--content-->
<div class="container">
    <div class="contact">
        <div class="contact-form">
            <div class="col-md-8 contact-grid">
                <form action="/restaurant/pwdchange3" id="changePwdForm" method="post">
                    <input type="password" placeholder="密码" name="password"/>
                    <div class="bubble-box arrow-top" id="passwordBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="passwordIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>

                    <input type="password" placeholder="确认密码" name="confirmPassword"/>
                    <div class="bubble-box arrow-top" id="confirmPasswordBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="confirmPasswordIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>
                    <input name="_method" type="hidden" value="PUT"/>
                    <div class="send">
                        <input type="submit" value="提交"/>
                    </div>
                </form>
            </div>
            <div class="col-md-4 contact-in" style="margin-top: 200px;">
                <h2>Get In Touch With Us</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                    et dolore magna aliqua. Ut enim ad minim veniam.</p>
                <div class="address-grid">
                    <h5>Address</h5>
                    <p><b>Lorem ipsum dolor</b></p>
                    <p>TL 19034-88974</p>
                    <a href="mailto:mail@example.com">Contact@example.com</a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="map">

        </div>

    </div>
    <%@include file="footer.jsp" %>
</body>
</html>