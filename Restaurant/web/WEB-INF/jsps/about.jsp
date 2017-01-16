<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>About</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/loginInfo.css" rel="stylesheet" type="text/css" media="all" />

	<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Fidele Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!---->
<script src="${pageContext.request.contextPath}/js/menu_jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</head>
<body> 
<!--header-->
<%@include file="header.jsp"%>
<!--//header-->
<div class="banner-head">
	<div class="banner-1"> </div>
		<div class="container">
			<h1>About Us</h1>	
		</div>
</div>
<!--content-->
<div class="about">
		<!--content-top-->
		<div class="container">
			<div class="about-top">
			<div class="col-sm-4 top-content">
				<a href="single"><img class="img-responsive" src="${pageContext.request.contextPath}/images/food/1482489369026.jpg" alt=""></a>
				<h4><a href="single">Consectetur adipisicing</a></h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
			<div class="col-sm-4 top-content">
				<a href="single"><img class="img-responsive" src="${pageContext.request.contextPath}/images/food/1482489369026.jpg" alt=""></a>
				<h4><a href="single">Consectetur adipisicing</a></h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
			<div class="col-sm-4 top-content">
				<a href="single"><img class="img-responsive" src="${pageContext.request.contextPath}/images/food/1482489369026.jpg" alt=""></a>
				<h4><a href="single">Consectetur adipisicing</a></h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<!---->
	<div class="why">
		<div class="container">
			<div class="why-top-top">
				<div class="why-top">
					<h2>Why Choose Us</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
				</div>
				<div class="col-sm-4 why-top1">
					<span>1</span>
					<h6><a href="single">Lorem ipsum dolor sit amet</a></h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore.</p>
					<a href="single" class="read-more">Read More</a>
				</div>
				<div class="col-sm-4 why-top1">
					<span>2</span>
					<h6><a href="single">Lorem ipsum dolor sit amet</a></h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.</p>
					<a href="single" class="read-more">Read More</a>
				</div>
				<div class="col-sm-4 why-top1">
					<span>3</span>
					<h6><a href="single">Lorem ipsum dolor sit amet</a></h6>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.</p>
					<a href="single" class="read-more">Read More</a>
				</div>
				<div class="clearfix"> </div>
			</div>
			
		</div>
	</div>
	<!---->

</div>
<!--footer-->
<%@include file="footer.jsp"%>

<!--//footer-->
</body>
</html>