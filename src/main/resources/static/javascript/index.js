let host = "/api"

var app = angular.module('myApp', []);
app.controller('myCtrl', function($http, $scope) {

    $scope.inform = {}

    $scope.cartInform = {}

    $scope.noAction = function() {
        Swal.fire({
            title: "Tò mò ư ?",
            text: "Tính năng vẫn chưa khả dụng",
            icon: "question"
        });
    }

    $scope.scrollTo = function(id) {
        document.getElementById(id).scrollIntoView({ behavior: 'smooth' });
    }

    $scope.sendMail = function() {
        var url = `${host}/Mail`
        $http.post(url, $scope.inform).then(resp => {
            console.log($scope.inform)
            Swal.fire({
                title: "Gửi thành công",
                text: "Chúng tôi sẽ liên lạc với bạn sớm nhất có thể",
                icon: "success",
                showClass: {
                    popup: `
					      animate__animated
					      animate__fadeInUp
					      animate__faster
					    `
                },
                hideClass: {
                    popup: `
					      animate__animated
					      animate__fadeOutDown
					      animate__faster
					    `
                }
            }).then(result => {
                if (result.isConfirmed) {
                    //Chuyển về trang chủ
                    window.location.href = "/";
                }
            });
        })

    }


    //Trang giỏ hàng
    $scope.RETAIL_PRICE = 40000;
    $scope.WHOLESALE_PRICE = 35000;
    $scope.WHOLESALE_QTY = 100;

    $scope.cartInform = {
        soLuongMua: 1
    };

    // 👇 LẤY QTY TỪ URL
    const params = new URLSearchParams(window.location.search);
    const urlQty = parseInt(params.get("qty"));

    if (!isNaN(urlQty) && urlQty > 0) {
        $scope.cartInform.soLuongMua = urlQty;
    }
    //

    $scope.updatePrice = function() {

        let qty = Math.max(1, $scope.cartInform.soLuongMua || 1);
        $scope.cartInform.soLuongMua = qty;

        $scope.isWholesale = qty >= $scope.WHOLESALE_QTY;

        $scope.unitPrice = $scope.isWholesale
            ? $scope.WHOLESALE_PRICE
            : $scope.RETAIL_PRICE;

        $scope.subtotal = qty * $scope.unitPrice;

        $scope.saving = $scope.isWholesale
            ? qty * ($scope.RETAIL_PRICE - $scope.WHOLESALE_PRICE)
            : 0;

        $scope.progressPercent = Math.min(
            (qty / $scope.WHOLESALE_QTY) * 100,
            100
        );

        $scope.remaining = $scope.WHOLESALE_QTY - qty;
    };

    $scope.changeQty = function(delta) {
        $scope.cartInform.soLuongMua += delta;
        $scope.updatePrice();
    };

    $scope.updatePrice();

    $scope.order = function() {
        var url = `${host}/DonHang/DatHang`
        $http.post(url, $scope.cartInform).then(resp => {
            Swal.fire({
                title: "Đặt hàng thành công",
                text: "Chúng tôi sẽ liên lạc với bạn sớm nhất có thể",
                icon: "success",
                showClass: {
                    popup: `
								      animate__animated
								      animate__fadeInUp
								      animate__faster
								    `
                },
                hideClass: {
                    popup: `
								      animate__animated
								      animate__fadeOutDown
								      animate__faster
								    `
                }
            }).then(result => {
                if (result.isConfirmed) {
                    //Chuyển về trang chủ
                    window.location.href = "/";
                }
            });
        })
    }

})

AOS.init({
    duration: 1000, // thời gian animation (ms) 
    once: true
    // chỉ chạy 1 lần khi scroll tới 
});

