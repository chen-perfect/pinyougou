/** 后台主页控制器 */
app.controller('indexController', function($scope, baseService){

    /** 获取登录用户名的方法 */
    $scope.showLoginName = function(){
        baseService.sendGet("/login/username").then(function (response) {
            // 获取响应数据
            $scope.loginName = response.data.loginName;
        });
    };
});