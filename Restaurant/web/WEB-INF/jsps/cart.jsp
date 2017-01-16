<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>cart</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/singleDish.css" rel="stylesheet" type="text/css" media="all" />

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
    <script src="${pageContext.request.contextPath}/js/cart/addToCart.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo.js"></script>

</head>
<body>
<%@include file="header.jsp"%>

<!--//header-->
<div class="banner-head">
    <div class="banner-1"> </div>
    <div class="container">
        <h1>Cart</h1>
    </div>
</div>
<!--content-->
<div class="container">
    <div>
        <table id="cartTable">
            <thead>
            <tr>
                <th>商品</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.cart}" var="item">
                <tr>
                    <td class="goods"><input id="cartDishId" type="hidden" value="${item.value.dishId}"/><img src="${item.value.cover}" alt=""/><span>${item.value.dishName}</span></td>
                    <td class="price">${item.value.price}</td>
                    <td class="count"><span onclick="countDelete(this)" class="reduce">-</span><input class="count-input" onchange="countChange(this)" type="text" value="${item.value.buyCount}"/><span onclick="countAdd(this)" class="add">+</span></td>
                    <td class="subtotal"><fmt:formatNumber type="number" value="${item.value.price * item.value.buyCount}" maxFractionDigits="2"/></td>
                    <td class="operation"><span class="delete" onclick="deleteCartDish2(this)">删除</span></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

      <div class="foot" id="foot">
          <form id="_form" action="/restaurant/order" method="post">
            <div class="fr closing" onclick="formSettle()">结 算</div>
          </form>
          <div class="fr total">合计：￥<span id="priceTotal"><fmt:formatNumber type="number" value="${requestScope.sumPrice}" maxFractionDigits="2"/></span></div>
          <div class="fr selected" id="selected">已选商品<span id="selectedTotal">${requestScope.sumCount}</span>件<span class="arrow up">︽</span><span class="arrow down">︾</span></div>
          <div class="selected-view">
              <div id="selectedViewList" class="clearfix">
                  <div><img src="images/1.jpg"><span>取消选择</span></div>
              </div>
              <span class="arrow">◆<span>◆</span></span>
          </div>
      </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>