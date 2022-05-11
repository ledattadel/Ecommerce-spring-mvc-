<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="admin/index.htm">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Flower Admin</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="admin/index.htm">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">
		
        <div class="sidebar-heading">
            Chức năng
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                <i class="fab fa-product-hunt"></i>
                <span>Quản lý sản phẩm</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="admin/product/list.htm">Xem tất cả sản phẩm</a>
                    <a class="collapse-item" href="admin/product/add.htm">Đăng sản phẩm mới</a>
                </div>
            </div>
        </li>
        
        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fas fa-shopping-cart"></i>
                <span>Quản lý đơn hàng</span>
            </a>
            <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="admin/order/tat-ca-don-hang.htm">Xem tất cả đơn hàng</a>
                    <a class="collapse-item" href="admin/order/don-hang-da-xac-nhan.htm">Đơn hàng đã xác nhận</a>
                    <a class="collapse-item" href="admin/order/don-hang-dang-xac-nhan.htm">Đơn hàng đang xác nhận</a>
                    <a class="collapse-item" href="admin/order/don-hang-da-huy.htm">Đơn hàng đã hủy</a>
                    <a class="collapse-item" href="admin/order/don-hang-da-giao.htm">Đơn hàng đã giao</a>
                </div>
            </div>
        </li>
        
        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser" aria-expanded="true" aria-controls="collapseUser">
                <i class="fas fa-users"></i>
                <span>Quản lý người dùng</span>
            </a>
            <div id="collapseUser" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="admin/user/list.htm">Xem tất cả người dùng</a>
                    <a class="collapse-item" href="admin/user/add.htm">Thêm người dùng mới</a>
                </div>
            </div>
        </li>

		<!-- Divider -->
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            Giao diện
        </div>
		<!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
         <a class="nav-link collapsed" href="admin/hot-trend/list.htm">
             <i class="fas fa-sliders-h"></i>
             <span>Hot Trend</span>
         </a>
         <a class="nav-link collapsed" href="admin/feature/add.htm">
             <i class="fas fa-sliders-h"></i>
             <span>Feature</span>
         </a>
         <a class="nav-link collapsed" href="admin/slider/list.htm">
             <i class="fas fa-sliders-h"></i>
             <span>Slider Discount</span>
         </a>
         <a class="nav-link collapsed" href="admin/banner/list.htm">
             <i class="fas fa-sliders-h"></i>
             <span>Banner Discount</span>
         </a>
                
        </li>
        <!-- Heading -->
        
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
		<div class="text-center d-none d-md-inline">
            <a class="btn btn-primary" href="index.htm" role="button">Xem trang chủ</a>
        </div>
        </ul>