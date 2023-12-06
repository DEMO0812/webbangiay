app.controller("authority-ctrl", function($scope, $http, $location) {

    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];
    $scope.message = null;

    $scope.initialize = function() {
        // load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        // load nhân viên và admin 
        $http.get("/rest/accounts?admin=true")
            .then(resp => {
                $scope.admins = resp.data;
            })
            .catch(error => {
                console.error("Error fetching data:", error);
                // Hiển thị thông báo lỗi hoặc xử lý một cách phù hợp
            });

        // load authorities of nhân viên và admin
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })

        // kiểm tra tìm xem có quyền hay không trong ng-checked
        $scope.authority_of = function(acc, role) {
            if ($scope.authorities) {
                return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
            }
        }

        // click cấp quyền hoặc đổi quyền
        $scope.authority_changed = function(acc, role) {
            var authority = $scope.authority_of(acc, role);
            if (authority) { // đã cấp quyền => thu dồi quyền
                $scope.revoke_authority(authority);
            } else { // chưa cấp và click để cấp
                authority = { account: acc, role: role };
                $scope.grant_authority(authority);
            }
        }

        // thêm mới quyền
        $scope.grant_authority = function(authority) {
            $http.post("/rest/authorities", authority).then(resp => {
                $scope.authorities.push(resp.data)
                $scope.message = "Cấp quyền sử dụng thành công!";
                alert("tc")
            }).catch(error => {
                $scope.message = "Cấp quyền sử dụng thất bại!";
                alert("tb")
                console.log("Error", error);
            })
        }

        // xóa quyền
        $scope.revoke_authority = function(authority) {
            $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
                var index = $scope.authorities.findIndex(a => a.id == authority.id);
                $scope.authorities.splice(index, 1);
                $scope.message = "Thu hồi quyền sử dụng thành công!";
                alert("tc")
            }).catch(error => {
                $scope.message = "Thu hồi quyền sử dụng thất bại!";
                alert("tb")
                console.log("Error", error);
            })
        }

    }
    $scope.initialize();

    $scope.getStatistics = function(days) {
        console.log('getStatistics called with days:', days);
        // Function to fetch statistics and update the chart
        function getStatistics(days) {
            // Implement your AJAX request to fetch statistics from the server
            // Replace the URL with the actual endpoint for fetching statistics
            fetch(`/rest/thongke/statistics?days=${days}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Data received:', data);
                    updateChart(data);
                    updateStatistics(data);
                })
                .catch(error => console.error('Error fetching statistics:', error));
        }

        // Function to update the chart
        function updateChart(data) {
            console.log('updateChart data:', data);
            var ctx = document.getElementById('orderChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Total Orders', 'Total Amount', 'Total Quantity'],
                    datasets: [{
                        label: 'Order Statistics',
                        data: [data.totalOrders, data.totalAmount, data.totalQuantity],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                        ],
                        borderWidth: 1
                    }]
                },
            });
        }

        // Function to update the statistics display
        function updateStatistics(data) {
            console.log('updateStatistics data:', data);

            var statisticsDiv = document.getElementById('statistics');
            statisticsDiv.innerHTML = `
                <h5>Statistics:</h5>
                <p>Total Orders: ${data.totalOrders}</p>
                <p>Total Amount: ${data.totalAmount}</p>
                <p>Total Quantity: ${data.totalQuantity}</p>
            `;
        }
    };


});