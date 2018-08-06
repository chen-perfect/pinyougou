/** 定义首页控制器层 */
app.controller("indexController", function($scope, baseService){

    /** 根据内容分类id查询广告内容 */
    $scope.findContentByCategoryId = function(categoryId){
        baseService.sendGet("/content/findContentByCategoryId?categoryId=" + categoryId)
            .then(function (response) {
                // 获取响应数据 [{},{}]
                $scope.contentList = response.data;
        });
    };

});