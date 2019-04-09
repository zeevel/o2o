$(function(){
    var loading = false;
    var maxItems = 999;
    var pageSize = 10;
    var listUrl = '/o2o/frontend/listshops';
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    var pageNum = 1;
    var parentId = getQueryString('parentId');
    var areaId = '';
    var shopCategoryId = '';
    var shopName = '';
    getSearchDivData();
    addItems(pageSize,pageNum);

    function getSearchDivData(){
        var url = searchDivUrl + '?' + 'parentId' + parentId;
        $.getJSON(url,function(data){
           if(data.success){
               var shopCategoryList = data.shopCategoryList;
               var html = '';

           }
        });
    }


    $(document).on("click", "#me", function() {
        $.openPanel("#panel-left-demo");
    });


})