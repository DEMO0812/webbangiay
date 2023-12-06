const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function($scope, $http) {
    // Tất cả sản phẩm
    $scope.products = [];

    // Kết quả tìm kiếm
    $scope.filteredProducts = [];

    // Văn bản tìm kiếm
    $scope.searchText = "";
    $scope.showProducts = true;
    // lấy thông tin cho modal :v
    $scope.modalPro = {};
    // QUẢN LÝ GIỎ HÀNG
    $scope.cart = {
        items: [],
        //thêm sản phẩm
        add(id) {
            //var productId = parseInt(id, 10);
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    resp.data.image = "/assets/images/" + resp.data.image;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        //xóa sp
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        //xóa hết
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //tính thành tiền 1sp
        amt_of(item) {},
        //tính tổng sl các mặt hàng 
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        //tổng thành tiền các mặt hàng
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        // lưu giỏ hàng vào local
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        // đọc giỏ hàng từ local
        loadFromLocal() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }

    $scope.cart.loadFromLocal();

    // xử lý đặt hàng
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() },
        // dòng này để them vào orderServiceImpl
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty

                }
            });
        },
        //đặt hàng
        purchase() {
            var order = angular.copy(this);
            console.log(order); // In ra console để kiểm tra dữ liệu trước khi gửi đi
            // thực hiện đặt hàng
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("đặt hàng lỗi")
                console.log(error)
            })
        }

    }

    // Hàm mở modal và load thông tin sản phẩm
    $scope.openModal = function(pId) {

        console.log("Product ID:", pId);
        // Gửi yêu cầu AJAX để lấy thông tin sản phẩm dựa trên productId
        $http.get(`/rest/products/${pId}`).then(resp => {
            // Cập nhật nội dung modal với thông tin sản phẩm nhận được
            $scope.modalPro = resp.data; // Gán thông tin sản phẩm vào $scope
            $scope.modalPro.image = '/assets/images/' + $scope.modalPro.image;
            // Hiển thị modal
            $('#modal_box').modal('show');
            console.log("Product IDshow:", $scope.modalPro);
            console.log("Image URL:", $scope.modalPro.image);

        }).catch(error => {
            console.error("Lỗi show sp js shoppe cart");
        });
    };





    // Hàm tìm kiếm
    $scope.search = function() {
        // Kiểm tra xem searchText có giá trị hay không
        if ($scope.searchText.trim() === "") {
            // Nếu không có giá trị, ẩn danh sách sản phẩm
            $scope.showProducts = false;
        } else {
            // Nếu có giá trị, hiển thị danh sách sản phẩm và lọc danh sách dựa trên searchText
            $scope.showProducts = true;
            $scope.filteredProducts = $scope.products.filter(function(product) {
                // Tìm kiếm theo name, price, createDate
                return (
                    product.name.toLowerCase().includes($scope.searchText.toLowerCase()) ||
                    product.price.toString().includes($scope.searchText) ||
                    product.createDate.includes($scope.searchText)
                );
            });
        }
    };

    // Hàm lấy danh sách sản phẩm từ server
    $scope.loadProducts = function() {
        $http.get("/rest/products").then(resp => {
            $scope.products = resp.data;
        }).catch(error => {
            console.error("Lỗi load sản phẩm");
        });
    };

    // Gọi hàm loadProducts để lấy danh sách sản phẩm khi controller khởi tạo
    $scope.loadProducts();


    // Sử dụng $watch để theo dõi sự thay đổi của searchText và thực hiện tìm kiếm tự động
    // $scope.$watch('searchText', function(newVal, oldVal) {
    //     if (newVal !== oldVal) {
    //         $scope.search();
    //     }
    // });
})