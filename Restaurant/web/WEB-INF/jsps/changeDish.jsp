<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Contact</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/addDish.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all" />

    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Fidele  Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---->
    <script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/addDish.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/upLoadDishPre.js"></script>
    <script src="${pageContext.request.contextPath}/js/getMultiPic.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

</head>
<body>
<%@include file="header.jsp"%>

<!--//header-->
<div class="banner-head">
    <div class="banner-1"> </div>
    <div class="container">
        <h1>Dish</h1>
    </div>
</div>
<!--content-->
<div class="container">
    <div class="contact">
        <div class="contact-form">
            <div class="col-md-8 contact-grid">
                <form id="addDishForm" onsubmit="return checkSubmit2()" action="${pageContext.request.contextPath}/restaurant/dishchange2/${requestScope.dish.id}"  method="post" enctype="multipart/form-data">
                    <input type="hidden" id="dishId" value="${requestScope.dish.id}"/>
                    <input type="text" placeholder="菜品名" name="dishName" value="${requestScope.dish.dishName}"/>
                    <div class="bubble-box arrow-top" id="dishNameBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="dishNameIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>

                    <input type="text" placeholder="价格" name="price" value="${requestScope.dish.price}"/>
                    <div class="bubble-box arrow-top" id="priceBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="priceIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>

                    <input type="text" placeholder="类型" name="classify" value="${requestScope.dish.classify}"/>
                    <div class="bubble-box arrow-top" id="typeBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="typeIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>

                    <input type="text" placeholder="库存" name="inventory" value="${requestScope.dish.inventory}"/>
                    <div class="bubble-box arrow-top" id="inventoryBox">
                        <div class="wrap"></div>
                    </div>
                    <img id="inventoryIcon" src="${pageContext.request.contextPath}/images/registerIcon/true.png" height="20px" width="20px"/>
                    <div class="clearfix"> </div>

                    <div id="coverImag">
                        <div style="font-size: 20px;">封面</div>
                        <img id="coverImagPr" height="200" width="200" src="${pageContext.request.contextPath}${requestScope.dish.cover}"/>
                    </div>
                    <div class="clearfix"> </div>

                    <input type="file" name="cover" id="up" onchange="upload()"/>
                    <div style="color:#888;">支持jpg、gif、png图片，最大1M</div>
                    <span style="color:red" id="errorMessage"></span>

                    <div style="font-size: 20px;">菜品图预览</div>
                    <div id="imgList">
                        <ul>
                            <li>
                                <img id="img1" height="200px" width="200px"/>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix"> </div>
                    <input type="file" multiple="multiple" onchange="uploadMulti()" id="uploadMul" name="uploadMul"/>
                    <div style="color:#888;">最多可上传三张预览图</div>
                    <span style="color:red" id="errorMessage2"></span>
                    <div class="clearfix"> </div>

                    <div class="send">
                        <input type="submit" value="Send" name="addDishButton">
                    </div>
                </form>
            </div>
            <div class="col-md-4 contact-in" style="margin-top: 200px;">
                <h2>Get In Touch With Us</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                <div class="address-grid">
                    <h5>Address</h5>
                    <p><b>Lorem ipsum dolor</b></p>
                    <p>TL 19034-88974</p>
                    <a href="mailto:mail@example.com">Contact@example.com</a>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
    <%@include file="footer.jsp"%>
</body>
</html>