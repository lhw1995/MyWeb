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
	<script src="${pageContext.request.contextPath}/js/assess.js"></script>

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
					<li>
						<img src="${requestScope.dish.cover}" height="80px" width="80px"/>
					</li>
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
			<c:if test="${sessionScope.loginUser.userName != 'admin'}">
				<tr>
					<td>
						<input size="2" type="text" onkeydown="return checkKey(event)" name="buyCount" value="1"/>
					</td>
					<td>
						<input type="button" onclick="addToCart()" name="addToCart" value="加入购物车"/>
					</td>
				</tr>
			</c:if>
		</table>
	</div>

	<c:if test="${sessionScope.loginUser.userName != 'admin'}">
	<img id="cartImg" src="/images/food/Cart.jpg" height="80" width="80"/>

	<div id="cart">
        <table>
            <tr>
                <td>菜品图</td>
                <td>菜名</td>
                <td>价格</td>
                <td>购买数量</td>
            </tr>
            <c:forEach items="${sessionScope.cart}" var="item">
                <tr class="cartDish">
                    <td class="cartDishCover"><input id="cartDishId" type="hidden" value="${item.value.dishId}"/><a href="#"><img height="50" width="50" src='${item.value.cover}'/></a></td>
                    <td class="cartDishName"><a href="#" class="cart_item_name">${item.value.dishName}</a></td>
                    <td class="cartDishPrice"><span class="cart_price">￥${item.value.price}</span></td>
                    <td class="cartDishBuyCount">&nbsp;&nbsp;<span class="cart_count">${item.value.buyCount}</span></td>
                    <td><img id="deleteCartDishImg" onclick="deleteCartDish(this)" src="/images/food/delete.jpg" width="15" height="15"/></td>
                </tr>
            </c:forEach>
			<tr>
				<td><input id="settle" type="button" onclick="settle()" value="结算"/></td>
			</tr>
        </table>
	</div>
	</c:if>
	<div class="clearfix"></div>

	<table id="allAssess">
		<tr id="assessCount">
			<td>累计评价数:10</td>
		</tr>
		<tr id="avgScore">
			<td>评分:8</td>
		</tr>
	</table>
	<div class="clearfix"></div>
	<br/>

	<table id="assessTable">
	<%--	<tr>
			<td id="assessHead"><img src="/images/5.jpg" width="50" height="50"></td>
			<td id="singleScore">评分:8</td>
		</tr>
		<tr>
			<td id="assessUser">zhangsan</td>
			<td id="assessContent">
				评价评价评价评价评价评价评价评价
			</td>
			<td id="assessDate"></td>
		</tr>--%>
	</table>

</div>
	<%@include file="footer.jsp"%>
</body>
</html>