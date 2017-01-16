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
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/assess.css" rel="stylesheet" type="text/css" media="all" />

    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Fidele  Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---->
    <script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/singleDish.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/js/assessPicUploadPre.js"></script>

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
    <div id="leftbox">
        <div id="mainImg">
            <img src="${requestScope.dish.cover}" height="350px" width="350px"/>
        </div>
        <div id="imgList">
            <ul>
                <c:forEach items="${requestScope.dishImgList}" var="dishImg">
                    <li>
                        <img src="${dishImg.image}" height="80px" width="80px"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <input type="hidden" id="dishId" value="${requestScope.dish.id}"/>
    <div id="rightbox">
        <table>
            <tr>
                <td>菜名:</td>
                <td>${requestScope.dish.dishName}</td>
            </tr>
            <tr>
                <td>价格:</td>
                <td id="price">￥${requestScope.dish.price}</td>
            </tr>
            <tr>
                <td>销量:</td>
                <td>${requestScope.dish.count}</td>
            </tr>
            <tr>
                <td>库存:</td>
                <td>${requestScope.dish.inventory}份</td>
            </tr>
        </table>
    </div>
    <div class="clearfix"></div>

    <form action="/restaurant/assess" method="post" enctype="multipart/form-data">
        <table id="writeAssess">
            <tr>
                <td id="writeAssessTitle">
                    其他买家需要你的建议哦!
                </td>
            </tr>
            <tr id="writeAssessContent">
                <td>
                    <input type="hidden" value="${requestScope.itemId}" name="itemId"/>
                    <textarea name="content" rows="5" cols="50" placeholder="在此输入评价"></textarea>
                </td>
            </tr>
            <tr>
                <td id="score">评分：<input type="text" name="score"></td>
            </tr>
            <tr>
                <td id="picLabel">
                    晒一晒图片(最多五张)
                    <input type="file" onchange="uploadAssessImg()" name="assessPic" id="assessPic" multiple="multiple"  value="上传">
                    <span id="errorMsg" style="color: red"></span>
                </td>
            </tr>
            <tr>
                <td id="picList">
                </td>
            </tr>
            <tr id="submit">
                <td class="send">
                    <input type="submit" value="提交评价"/>
                </td>
            </tr>
        </table>
    </form>

</div>
<%@include file="footer.jsp"%>
</body>
</html>