function AjaxQuery(url){
    $.ajax({
        url:url,
        type:"GET",
        success:productPaganition,
        error:function (e){
            console.log(e);
        }
    });
}

function productPaganition(res){
    let html="";
    let listRes = res.list;
    let htmlpagination = "";
    for (let i = 1; i <= res.totalPage; i++) {
        if (i == res.page) {
            htmlpagination += "<li class=\"page-item active\"><span class=\"page-link\">" + (i < 10 ? "0" : "") + i + ".</span></li>";
        } else {
            htmlpagination += "<li class=\"page-item\"><span class=\"page-link\"  onclick='getNextPage("+i+")' >" + (i < 10 ? "0" : "") + i + ".</span></li>";
        }
    }
    document.querySelector("#pagination").innerHTML = htmlpagination;
    for (let i in listRes) {
        html+=" <div onclick='addToCombo("+listRes[i].id+")' class=\"card col-lg-4 col-md-3\">\n" +
            "                            <img class=\"card-img-top\" src='/img/product-img/"+listRes[i].img+".jpg' width='189' height='120' alt=\"Card image cap\">\n" +
            "                            <div class=\"card-body\">\n" +
            "                                <h5 class=\"card-title\" style='font-weight: bold'>"+listRes[i].productName+"</h5>\n" +
            "                                <p class=\"card-text\" style=\"color:red;font-weight: bold\">"+listRes[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</p>\n" +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#listProduct").html(html);
}
function addToCombo(id){
    $.ajax({
        url:"/api/combotemp/add/"+id,
        type:"GET",
        success:function (result) {
            AjaxQuery(renderComboTemp,"/api/combotemp/show");
        },
        error:function (exception) {
            alert("Co loi xay ra");
        }

    });
}
function getNextPage(page){
    let id=0;
    if($(".dropdown-item.active").length!=0){
        id=$(".dropdown-item.active").attr("value");
    }
    AjaxQuery("/api/productsell/"+page+"?categoryId="+id);
}
$("#allProduct").click(function(){
    $(".dropdown-item").removeClass("active");
    AjaxQuery("/api/productsell/1");
});
$(".dropdown-item").click(function(){
    $(".dropdown-item").removeClass("active");
    $(this).toggleClass("active");
    let categoryId=$(this).attr("value");
    AjaxQuery("/api/productsell/1?categoryId="+categoryId);
});//done
AjaxQuery("/api/productsell/1");//done