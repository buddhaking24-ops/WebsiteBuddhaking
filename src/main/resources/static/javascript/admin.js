let host = "/api"

var app = angular.module('myApp', ['ngRoute']);
app.controller('myCtrl', function($http, $scope) {

    $scope.items = []
	
    $scope.form = {}

    $scope.modal = {}
	
	//Trang lien he start
    $scope.initLh = function() {
        var url = `${host}/LienHe`
        $http.get(url).then(resp => {
            $scope.items = resp.data
            console.log($scope.items)
        })
    }
	
	$scope.loadLhById = function(id){
		var url = `${host}/LienHe/${id}`
		$http.get(url).then(resp => {
			$scope.lienHe = resp.data
			console.log(resp.data)
		})	
	}
	
	//Trang lien he end

    $scope.noAction = function() {
        Swal.fire({
            title: "Tò mò ư ?",
            text: "Tính năng vẫn chưa khả dụng",
            icon: "question"
        });
    }


    $scope.create = function() {
        var url = `${host}/VatChat`
        $http.post(url, $scope.form).then(resp => {
            $scope.init();
            $scope.update = true
            // --- CODE ĐÓNG MODAL ---
            var modalElement = document.getElementById('modalVatChat');
            var modalInstance = bootstrap.Modal.getInstance(modalElement);
            if (modalInstance) {
                modalInstance.hide();
            }
            // -----------------------
            Swal.fire({
                title: "Thêm thành công !",
                icon: "success",
                draggable: true
            });
        }).catch(error => {
            if (error.status == 400) {
                Swal.fire({
                    icon: "error",
                    title: "Thêm thất bại",
                    text: "Mã vật chất đã tồn tại trong csdl, hãy nhập mã khác !"
                });
            }
            if (error.status == 500) {
                Swal.fire({
                    icon: "error",
                    title: "Thêm thất bại",
                    text: "Tên vật chất đã tồn tại trong csdl, hãy nhập tên khác !"
                });
            }
        })
    }


    //Trang lịch sử kho
    $scope.itemsLs = []
    $scope.initLs = function() {
        var url = `${host}/LichSu`
        $http.get(url).then(resp => {
            $scope.itemsLs = resp.data
            $scope.initPage()
        })
    }

    $scope.limit = 4
    $scope.page = 1
    $scope.start = ($scope.page - 1) * $scope.limit
    $scope.initPage = function() {
        $scope.totalPage = Math.ceil($scope.itemsLs.length / $scope.limit)
        console.log($scope.totalPage)
        $scope.numberOfPage = Array.from(Array($scope.totalPage).keys())
        $scope.page = 1
        $scope.start = ($scope.page - 1) * $scope.limit
    }
    $scope.changePage = function(i) {
        $scope.page = i
        $scope.start = ($scope.page - 1) * $scope.limit
    }
    //

    //Trang thống kê start
    try {
        var labels = ["Tháng 11/2025", "Tháng 12/2025", "Tháng 1/2026"];
        var data = [850, 1400, 60];

        var ctx = document.getElementById("bieuDoDoanhThuTheoThang").getContext("2d");

        $scope.present_chart = new Chart(ctx, {
            type: "bar",
            data: {
                labels: labels,
                datasets: [{
                    label: "Số lượng thành phẩm xuất kho",
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(75, 192, 192, 0.2)'],
                    borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 159, 64)',
                        'rgb(75, 192, 192)'],
                    borderWidth: 2
                }]
            },
            options: {
                indexAxis: 'y',
                plugins: {
                    legend: {
                        display: false   // Ẩn legend
                    }
                }

            }
        });
    } catch (error) {
        console.error("Có lỗi xảy ra khi vẽ biểu đồ:", error);
    }

    //Trang thống kê end

    /*Quản lý đơn hàng start*/
	$scope.listDh = []
	$scope.listTt = []
	$scope.startDh =  ($scope.page - 1)*$scope.limit
	
    $scope.initTt = function() {
        var url = `${host}/DonHang/TrangThai`
        $http.get(url).then(resp => {
            $scope.listTt = resp.data
            console.log($scope.listTt)
        })
    }

    $scope.initDh = function() {
        var url = `${host}/DonHang`
        $http.get(url).then(resp => {
            $scope.listDh = resp.data
            console.log($scope.listDh)
			$scope.initPageDh()
        })
    }
	
	$scope.initPageDh = function(){
		$scope.totalPageDh = Math.ceil($scope.listDh.length/$scope.limit)
		$scope.numberOfPageDh = Array.from(Array($scope.totalPageDh).keys())
		$scope.changePageDh(1)
		console.log("so trang: "+$scope.numberOfPageDh)
	}
	
	$scope.changePageDh = function(i){
		console.log("page: "+i)
		$scope.page = i;
		$scope.startDh = ($scope.page - 1)* $scope.limit
	}

    $scope.loadByIdDh = function(maDh) {
        var url = `${host}/DonHang/${maDh}`
        $http.get(url).then(resp => {
            $scope.dhDetail = resp.data
            $scope.dhDetail.trangThai =
                $scope.listTt.find(tt => tt.displayName === $scope.dhDetail.trangThai.displayName);
            $scope.originalStatusCode = $scope.dhDetail.trangThai.code
            console.log($scope.dhDetail.trangThai)
        })
    }

    $scope.isAllowedTransition = function(currentCode, targetCode) {

        // Cho hiển thị current state
        if (currentCode === targetCode) {
            return true;
        }

        if (currentCode === 'CHO_XAC_NHAN') {
            return targetCode === 'DANG_GIAO' || targetCode === 'DA_HUY';
        }

        if (currentCode === 'DANG_GIAO') {
            return targetCode === 'DA_GIAO';
        }

        return false;
    };

    $scope.updateDh = function(maDh, trangThai) {
        var url = `${host}/DonHang/${maDh}/${trangThai}`
        $http.put(url, $scope.dhDetail).then(resp => {
            Swal.fire({
                title: "Cập nhật đơn hàng thành công !",
                icon: "success",
                draggable: true
            });
            $scope.initDh()
        })
    }
    /*Quản lý đơn hàng end*/

    //Chay khi mới mở trang
    $scope.initDh()
    $scope.initTt()
    $scope.initLh()

})