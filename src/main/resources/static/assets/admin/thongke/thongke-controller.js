app.controller("thongke1-ctrl", function($scope, $http) {
    alert("ahiih");
    var ctx = document.getElementById('thongkeChart').getContext('2d');
    var thongKeData = {
        labels: ['Tổng số đơn hàng', 'Tổng tiền', 'Tổng số lượng'],
        datasets: [{
            label: 'Tổng số đơn hàng',
            data: [0, 0, 0],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }, {
            label: 'Tổng tiền',
            data: [0, 0, 0],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }, {
            label: 'Tổng số lượng',
            data: [0, 0, 0],
            backgroundColor: 'rgba(54, 162, 235, 0.2)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1
        }]
    };

    var myChart = new Chart(ctx, {
        type: 'bar',
        data: thongKeData,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    $scope.loadChartData = function(days) {
        $http.get("/rest/thongke?days=" + days)
            .then(function(response) {
                var thongKeDTO = response.data;

                myChart.data.datasets[0].data = [thongKeDTO.totalOrders, 0, 0];
                myChart.data.datasets[1].data = [0, thongKeDTO.totalMoney, 0];
                myChart.data.datasets[2].data = [0, 0, thongKeDTO.totalQuantity];

                myChart.data.labels = ['Tổng số đơn hàng', 'Tổng tiền', 'Tổng số lượng'];

                myChart.update();
            });
    };

    // ... (các phương thức và cài đặt khác nếu có)
});