app = angular.module("admin-app", ["ngRoute"]);




app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html", // Đường dẫn tương đối từ thư mục gốc
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/assets/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .when("/thongke", {

        })
        .otherwise({
            templateUrl: "/assets/admin/authority/unauthorized.html"
        });
});