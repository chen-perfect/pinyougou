/** 定义控制器层 */
app.controller('goodsController', function($scope, $controller, baseService){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});


    /** 添加或修改 */
    $scope.saveOrUpdate = function(){

        // 获取富文本编辑器中内容
        $scope.goods.goodsDesc.introduction = editor.html();

        /** 发送post请求 */
        baseService.sendPost("/goods/save", $scope.goods)
            .then(function(response){
                if (response.data){
                    /** 清空数据*/
                    $scope.goods = {};
                    // 清空富文本编辑器中的内容
                    editor.html("");
                }else{
                    alert("操作失败！");
                }
            });
    };

    // 定义图片存储的数据格式
    $scope.goods = {goodsDesc : {itemImages : [], specificationItems : []}};

    // 文件异步上传
    $scope.uploadFile = function(){
        // 调用服务层方法
        baseService.uploadFile().then(function(response){
            // 获取响应数据{status : 200, url : 'http://192.'}
            if (response.data.status){
                // {color:'',url:''}
                $scope.image_entity.url = response.data.url;
            }
        });
    };

    // 保存上传的图片
    $scope.add_image_entity = function(){
        $scope.goods.goodsDesc.itemImages.push($scope.image_entity);
    };

    // 从数组中删除图片
    $scope.remove_image_entity = function(idx){
        $scope.goods.goodsDesc.itemImages.splice(idx, 1)
    };

    /** 查询商品的分类 */
    $scope.findItemCatByParentId = function (parentId, name) {
        baseService.sendGet('/itemCat/findItemCatByParentId',
            "parentId=" + parentId).then(function (response) {
            // 获取响应数据 List<ItemCat> [{},{}]
            $scope[name] = response.data;
        });
    };

    /** $watch方法监听goods.category1Id变量的值，发生改变需要调用函数 */
    $scope.$watch('goods.category1Id', function (newValue, oldValue) {
        // 判断newValue如果是undefined就直接返回
        if (!newValue){
            $scope.itemCatList2 = [];
            return;
        }
        // 用newValue值作用父级id查询二级分类
        $scope.findItemCatByParentId(newValue, 'itemCatList2');

    });
    /** $watch方法监听goods.category2Id变量的值，发生改变需要调用函数 */
    $scope.$watch('goods.category2Id', function (newValue, oldValue) {
        // 判断newValue如果是undefined就直接返回
        if (!newValue){
            $scope.itemCatList3 = [];
            return;
        }
        // 用newValue值作用父级id查询三级分类
        $scope.findItemCatByParentId(newValue, 'itemCatList3');
    });


    /** $watch方法监听goods.category3Id变量的值，发生改变需要调用函数 */
    $scope.$watch('goods.category3Id', function (newValue, oldValue) {
        // 判断newValue如果是undefined就直接返回
        if (!newValue){
           // 清空类型模版的id
            $scope.goods.typeTemplateId = 0;
            return;
        }
        // 用newValue值从三级商品分类数组中找一个商品分类，再取类型模版id
        // 循环三级分类数组
        for (var i = 0; i < $scope.itemCatList3.length; i++){
            // 取一个数组元素 ItemCat: {"id":1,"name":"图书、音像、电子书刊","parentId":0,"typeId":35}
            var itemCat = $scope.itemCatList3[i];
            // 判断
            if (itemCat.id == newValue){
                // 取类型模版id
                $scope.goods.typeTemplateId = itemCat.typeId;
                break;
            }

        }
    });

    /** $watch方法监听goods.typeTemplateId变量的值，发生改变需要调用函数 */
    $scope.$watch('goods.typeTemplateId', function (newValue, oldValue) {
        // 判断newValue如果是undefined就直接返回
        if (!newValue){
            // 清空品牌下拉列表

            return;
        }


        // 用newValue值查询一个类型模版对象
        baseService.findOne("/typeTemplate/findOne", newValue)
            .then(function (response) {
                // response.data : {} TypeTemplate
                // 获取品牌数据 json字符串  转化成 json对象 {} [{},{}]
                $scope.brandIds = JSON.parse(response.data.brandIds);

                // 获取扩展属性 (把json字符串转化成 json对象)
                // [{\"text\":\"观看距离\"},{\"text\":\"分辨率\"},{\"text\":\"电视类型\"}]
                // 保存到tb_goods_desc 表
                // [{"text":"分辨率","value":"1920*1080(FHD)"},{"text":"摄像头","value":"1200万像素"}]
                $scope.goods.goodsDesc.customAttributeItems = JSON.parse(response.data.customAttributeItems);

            });

        // 根据类型模版id查询规格及规格选项
        baseService.sendGet("/typeTemplate/findSpecByTemplateId?id=" + newValue)
            .then(function(response){
                // response.data: [{"id":27,"text":"网络", options : [{},{}]},{"id":32,"text":"机身内存",options : [{},{}]}]
                $scope.specList = response.data;
            });

    });


    /** 为选中规格选项的checkbox绑定点击事件 */
    $scope.updateSpecAttr = function($event, text, optionName){
        /**
         * goods.goodsDesc.specificationItems
         * [{"attributeValue":["移动4G","联通4G"],"attributeName":"网络"},
           {"attributeValue":["16G"],"attributeName":"机身内存"}]
         */

        // 从数组中根据key对应的值搜索一个json对象
        var obj = searchJsonByKey($scope.goods.goodsDesc.specificationItems, "attributeName", text);
        // 判断obj是否为空
        if (obj){
            // obj : {"attributeValue":["移动4G"],"attributeName":"网络"}
            // 判断checkbox是否选中
            if ($event.target.checked){
                obj.attributeValue.push(optionName);
            }else{
                // 取消了checkbox选中，从数组中删除一个元素
                obj.attributeValue.splice(obj.attributeValue.indexOf(optionName),1);
                // 判断 obj.attributeValue数组的长度
                if (obj.attributeValue.length == 0){
                    // specificationItems删除数组元素
                    $scope.goods.goodsDesc.specificationItems.splice(
                        $scope.goods.goodsDesc.specificationItems.indexOf(obj),1);
                }
            }
        }else {
            $scope.goods.goodsDesc.specificationItems
                .push({attributeValue: [optionName], attributeName: text});
        }
    };

    // 从数组中根据key对应的值搜索一个json对象
    var searchJsonByKey = function(jsonArr, key, value){
        /**
         * [{"attributeValue":["移动4G","联通4G"],"attributeName":"网络"},
            {"attributeValue":["16G"],"attributeName":"机身内存"}]
         */
        for (var i = 0; i < jsonArr.length; i++){
            // jsonArr[i] {"attributeValue":["移动4G","联通4G"],"attributeName":"网络"}
            // 取一个数组元素
            var item = jsonArr[i];
            if (item[key] == value){
                return item;
            }
        }
        return null;
    };


    // 生成SKU列表
    $scope.createItems = function () {
        /** 定义SKU数组变量，并初始化 */
        $scope.goods.items = [{spec:{}, price:0, num:9999, status:'0', isDefault:'0'}];

        // 获取用户选中的规格选项
        // [{"attributeValue":["联通4G","电信4G","联通3G","移动4G"],"attributeName":"网络"}
        //  {"attributeValue":["联通4G","电信4G"],"attributeName":"网络"}]
        var specItems = $scope.goods.goodsDesc.specificationItems;
        for (var i = 0; i < specItems.length; i++){
            $scope.goods.items = swapItems($scope.goods.items,
                specItems[i].attributeName,specItems[i].attributeValue);
        }
    };


    // 转化sku商品方法
    var swapItems = function(items, attributeName, attributeValue){
        // attributeName: 网络
        // attributeValue: ["联通4G","电信4G","联通3G","移动4G"]
        // 定义一个新的SKU数组
        var newItemArr = [];

        for (var i = 0; i < items.length; i++){ // 3
            var item = items[i];
            // "attributeValue":["联通4G","电信4G","联通3G","移动4G"]
            for (var j = 0; j < attributeValue.length; j++){ // 2
                // 得到新得item对象
                var newItem = JSON.parse(JSON.stringify(item));
                // {"网络":"电信4G","机身内存":"128G"}
                newItem.spec[attributeName] = attributeValue[j];
                // 添加数组元素
                newItemArr.push(newItem);
            }
        }
        return newItemArr;
    };

















    /** 查询条件对象 */
    $scope.searchEntity = {};
    // 定义状态码的数组
    /** 分页查询(查询条件) */
    $scope.search = function(page, rows){
        baseService.findByPage("/goods/findByPage", page,
			rows, $scope.searchEntity)
            .then(function(response){
                /** 获取分页查询结果 */
                $scope.dataList = response.data.rows;
                /** 更新分页总记录数 */
                $scope.paginationConf.totalItems = response.data.total;
            });
    };
    // 定义状态码的数组
    $scope.status = ["未审核","已审核","审核未通过","关闭"];



    /** 显示修改 */
    $scope.show = function(entity){
       /** 把json对象转化成一个新的json对象 */
       $scope.entity = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除 */
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            baseService.deleteById("/goods/delete", $scope.ids)
                .then(function(response){
                    if (response.data){
                        /** 重新加载数据 */
                        $scope.reload();
                    }else{
                        alert("删除失败！");
                    }
                });
        }else{
            alert("请选择要删除的记录！");
        }
    };
});