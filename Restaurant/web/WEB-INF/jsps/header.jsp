<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lhw
  Date: 2016/12/1
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="container">
        <div class="logo">
            <a href="index"><img src="${pageContext.request.contextPath}/images/logo.png" alt=""></a>
        </div>
        <div class="head-right">
            <div class="head-grid">
                <ul>
                    <li>
                        <c:choose>
                            <c:when test="${sessionScope.loginUser == null}">
                                <div id="loginContainer">
                                    <a href="#" id="loginButton"><span>Login</span></a>
                                    <label>  &nbsp;&nbsp;|</label>
                                    <a href="/restaurant/register" id="loginButton"><span>Register</span></a>
                                    <div id="loginBox">
                                        <form id="loginForm">
                                            <fieldset id="body">
                                                <fieldset>
                                                    <label for="userName">User Name</label>
                                                    <input type="text" name="userName" id="userName"
                                                           value="${cookie.userName.value}">
                                                </fieldset>
                                                <fieldset>
                                                    <label for="password">Password</label>
                                                    <input type="password" name="password" onkeydown='return loginSubmit(event)' id="password">
                                                </fieldset>
                                                <input type="button" id="login" value="Sign in">
                                                <label for="checkbox"><input type="checkbox" id="checkbox"
                                                                             name="remName"/> <i>Remember me</i></label>
                                            </fieldset>
                                            <span><a href="/restaurant/consumerpwdfind">Forgot your password?</a></span>
                                            <span id="loginMessage"></span>
                                        </form>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <ul id="loginInfo">
                                    <li><img
                                            src="${pageContext.request.contextPath}${sessionScope.loginUser.headPortrait}"
                                            height="45px" width="45px"></li>
                                    <li>
                                        <div id="showName">${sessionScope.loginUser.showName}</div>
                                        <ul id="showNameUl">
                                            <li><a href="/restaurant/consumerinfo">个人信息</a></li><br/>
                                            <li><a href="/restaurant/consumerpwd">修改密码</a></li>
                                            <li><a href="/restaurant/logout">退出登录</a></li>
                                        </ul>
                                    </li>
                                    <c:choose>
                                        <c:when test="${sessionScope.loginUser.userName == 'admin'}">
                                            <li>
                                                <div id="manage">管理 &nbsp;&nbsp;</div>
                                                <ul id="manageUl">
                                                    <li><a href="/restaurant/dishadd">添加菜品</a></li>
                                                    <li><a href="/restaurant/dishmanage">管理菜品</a></li>
                                                    <li><a href="/restaurant/order/all">查看订单</a></li>
                                                </ul>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <div id="myOrder"><a href="/restaurant/order">我的订单</a> &nbsp;&nbsp;</div>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>

                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
            <div class="top-nav">
                <span class="menu"><img src="${pageContext.request.contextPath}/images/menu.png" alt=""> </span>
                <ul>
                    <li class="active"><a href="/restaurant/index">首页</a></li>
                    <li><a href="/restaurant/gallery">菜谱</a></li>
                    <li><a href="/restaurant/about">了解我们</a></li>
                    <div class="clearfix"></div>
                </ul>

                <!--script-->
                <script>
                    $("span.menu").click(function () {
                        $(".top-nav ul").slideToggle(500, function () {
                        });
                    });
                </script>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!--//header-->