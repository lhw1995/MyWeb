<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Gallery</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/manageDish.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/searchDish.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/css/dishTable.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chocolat.css" type="text/css" media="screen"
          charset="utf-8">

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
    <script src="${pageContext.request.contextPath}/js/jquery.chocolat.js"></script>
    <!--light-box-files -->
    <script type="text/javascript" charset="utf-8">
        $(function () {
            $('.gallery-top a').Chocolat();
        });
    </script>

    <!---->
    <script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
</head>
<body>
<!--header-->
<%@include file="header.jsp" %>
<!--//header-->
<div class="banner-head">
    <div class="banner-1"></div>
    <div class="container">
        <h1>Dish</h1>
    </div>
</div>
<!--content-->
<div class="gallery">
    <div class="container">
        <div class="grid" style="margin-left: 0px">
            <input type="text" name="search" onkeydown='return search(event)' placeholder="搜索" size="4"/>
            <table name="dishTable" id="dishTable">
                <tr>
                    <td>菜品id</td>
                    <td>菜品名</td>
                    <td>菜品图</td>
                    <td>菜品价格</td>
                    <td>菜品类型</td>
                    <td>菜品评分</td>
                    <td>菜品库存</td>
                </tr>
                <tr><td colspan='8'><hr/></td></tr>
            </table>
            <div class="clearfix"></div>
        </div>
        <div>
            <table name = "pageTable" id="pageTable">
                <tr>
                    <td id="currentPage">当前页面:2</td>
                    <td id="totalPageCount">页面总数:1</td>
                    <td id="firstPage">首页</td>
                    <td id="prePage">上一页</td>
                    <td id="nextPage">下一页</td>
                    <td id="lastPage">尾页</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<!--footer-->
<%@include file="footer.jsp" %>

<!--//footer-->
</body>
</html>