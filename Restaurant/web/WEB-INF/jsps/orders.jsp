<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>order</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${pageContext.request.contextPath}/css/orders.css" rel="stylesheet" type="text/css" media="all" />

    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Fidele  Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---->
    <script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/orders.js"></script>

</head>
<body>
<%@include file="header.jsp"%>

<!--//header-->
<div class="banner-head">
    <div class="banner-1"> </div>
    <div class="container">
        <h1>Order</h1>
    </div>
</div>
<!--content-->
<div class="container">
       <c:forEach items="${requestScope.orderMap}" var="order">
           <table class="orderTable">
               <tr class="orderHead">
                   <td class="orderTime" colspan="2">
                       <c:if test = "${sessionScope.loginUser.userName == 'admin'}">
                           <b>用户名:</b>${order.key.consumer.userName}
                       </c:if>
                       <b>日期:</b>${order.key.time}
                   </td>
                   <td class="orderId" colspan="3">订单号:<span id="orderIdSpan">${order.key.id}</span></td>
               </tr>
               <c:forEach items="${order.value}" var="item">
                   <tr class="item">
                       <td class="itemDishCover"><img src="${item.dish.cover}" width="70px" height="70px"/></td>
                       <td class="itemDishName">${item.dish.dishName}</td>
                       <td class="itemDishPrice">￥${item.dish.price}</td>
                       <td class="itemDishCount">数量:${item.count}</td>
                       <td class="itemAssess">
                           <c:if test = "${sessionScope.loginUser.userName != 'admin'}">
                               <c:choose>
                                   <c:when test="${item.assess == 'assessing'}">
                                       <form class="_form" action="/restaurant/assesspage/${item.dish.id}" method="post">
                                           <input type="hidden" value="true" name="confirm"/>
                                           <input type="hidden" value="${item.id}" name="itemId"/>
                                           <span onclick="submit(this)" id="assessBtn">评价</span>
                                       </form>
                                   </c:when>
                                   <c:otherwise>
                                       <span>已评价</span>
                                   </c:otherwise>
                               </c:choose>
                           </c:if>
                       </td>
                   </tr>
               </c:forEach>
               <tr class="orderTail">
                   <td class="orderPrice">总价<span>￥${order.key.price}</span></td>
                   <td class="orderStatus">
                       <c:choose>
                           <c:when test="${order.key.status == 'receiving'}">
                               <c:choose>
                                   <c:when test="${sessionScope.loginUser.userName != 'admin'}">
                                       <span id="confirmOrder" onclick="confirmOrder(this)">确认收货</span>
                                   </c:when>
                                   <c:otherwise>
                                       <span style="color: #ff621f">未确认收货</span>
                                   </c:otherwise>
                               </c:choose>
                           </c:when>
                           <c:otherwise>
                               已确认收货
                           </c:otherwise>
                       </c:choose>
                   </td>
               </tr>
           </table>
       </c:forEach>
</div>
<%@include file="footer.jsp"%>
</body>
</html>