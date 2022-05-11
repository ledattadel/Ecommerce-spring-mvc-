<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đơn hàng của tôi</title>
    <base href="${pageContext.servletContext.contextPath}/">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <jsp:include page="css.jsp"></jsp:include>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Header Begin -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Header End -->
    
    <!-- Profile Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <!-- right side bar -->
                <jsp:include page="profile-sidebar.jsp"/>
                <!-- right side bar end-->
                <div class="col-lg-8">
                	<h5>Đơn hàng của tôi</h5>
                	<hr>
                	
                    <c:forEach var="i" items="${lstOrder}">
                    	<div class="order">
	                		<div class="row">
	                			<div class="col-lg-6">
	                				<span>Đơn hàng #${i.id }</span>
	                			</div>
	                			<div class="col-lg-6">
	                				<span class="status">Trạng thái: ${i.status.name }</span>
	                			</div>
	                		</div>
	                		<hr>
	                		<c:forEach var="j" items="${i.orderdetails }">
	                			<div class="row">
		                			<div class="col-lg-2">
		                				<img src="resources/client/img/product/${j.product.photo }" alt="" width="100px" height="100px">
		                			</div>
		                			<div class="col-lg-10">
			                			<h6 class="cart__product__item__title">${j.product.name }</h6>
			                			<span>x ${j.quantity }</span>
			                			<br>
			                			<span><f:formatNumber value="${j.unitPrice}" type="number"/></span>
		                			</div>
	                        	</div>
	                        	<hr>
	                		</c:forEach>
	                		<span style="font-weight:bold; font-size: 20px;">Tổng số tiền : <f:formatNumber value="${i.totalPrice}" type="number"/> VNĐ</span>
	                		<c:if test="${i.status.id == 1 }"><a href="huy-don-hang/${i.id }.htm" class="status">Hủy đơn hàng</a></c:if>
	                		<c:if test="${i.status.id == 2 }"><a href="da-nhan/${i.id }.htm" class="status">Đã nhận hàng</a></c:if>
	                	</div>
	                	<hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- Profile Section End -->
    
    <!-- Footer Section Begin -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <!-- Js Plugins -->
    <jsp:include page="js.jsp"></jsp:include>
</body>

</html>