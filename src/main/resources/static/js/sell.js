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
        html+=" <div onclick='addToCart("+listRes[i].id+")' class=\"card col-lg-4 col-md-3\">\n" +
            "                            <img class=\"card-img-top\" src='/img/product-img/"+listRes[i].img+".jpg' width='189' height='120' alt=\"Card image cap\">\n" +
            "                            <div class=\"card-body\">\n" +
            "                                <h5 class=\"card-title\" style='font-weight: bold'>"+listRes[i].productName+"</h5>\n" +
            "                                <p class=\"card-text\" style=\"color:red;font-weight: bold\">"+listRes[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</p>\n" +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#listProduct").html(html);
}
function addToCart(id){
    $.ajax({
        url:"/api/cart/product/add/"+id,
        type:"GET",
        success:ajaxrendercart,
        error:function (exception) {
            alert("Co loi xay ra");
        }
    });
}
function addToCartC(id){
    $.ajax({
        url:"/api/cart/combo/add/"+id,
        type:"GET",
        success:ajaxrendercart,
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

$("#comboCa").click(function(){
    $(".dropdown-item").removeClass("active");
    $.ajax({
        url:"/api/productsell/combo/1",
        type:"GET",
        success:comboPaganition,
        error:function (e){
            console.log(e);
        }

    });
});
function comboPaganition(res){
    let html="";
    let listRes = res.list;
    let htmlpagination = "";
    for (let i = 1; i <= res.totalPage; i++) {
        if (i == res.page) {
            htmlpagination += "<li class=\"page-item active\"><span class=\"page-link\">" + (i < 10 ? "0" : "") + i + ".</span></li>";
        } else {
            htmlpagination += "<li class=\"page-item\"><span class=\"page-link\"  onclick='getNextPageC("+i+")' >" + (i < 10 ? "0" : "") + i + ".</span></li>";
        }
    }
    document.querySelector("#pagination").innerHTML = htmlpagination;
    for (let i in listRes) {
        html+=" <div onclick='addToCartC("+listRes[i].id+")' class=\"card col-lg-4 col-md-3\">\n" +
            "                            <img class=\"card-img-top\" src='/img/product-img/comboDefault.jpg' width='189' height='120' alt=\"Card image cap\">\n" +
            "                            <div class=\"card-body\">\n" +
            "                                <h5 class=\"card-title\" style='font-weight: bold'>"+listRes[i].name+"</h5>\n" +
            "                                <p class=\"card-text\" style=\"color:red;font-weight: bold\">"+listRes[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</p>\n" +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#listProduct").html(html);
}
function getNextPageC(page){
    $.ajax({
        url: "/api/productsell/combo/"+page,
        type:"GET",
        success:comboPaganition,
        error:function (e){
            console.log(e);
        }

    });
}
function renderCart(res){
    let listProduct=res.listProduct;
    let listCombo=res.listCombo;
    let html="";
    for(let i in listProduct){
        html+=" <tr>\n" +
            "                                <td class=\"cart_product_desc\">\n" +
            "                                    <h5>"+listProduct[i].productName+"</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"qty\">\n" +
            "                                            <input type=\"text\" class=\"qty-text form-control\" id='qty"+listProduct[i].productID+"' step=\"1\" min=\"1\" max=\"300\" name=\"quantity\" value="+listProduct[i].qty+">\n" +
            "                                </td>\n" +

            "                                <td class=\"price\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+listProduct[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +

            "                                <td class=\"discount\">\n" +
            "                                            <input type=\"text\"  class=\"qty-text form-control\" id='discount"+listProduct[i].productId+"' step=\"1\" min=\"1\" max=\"300\" name=\"discount\" value="+listProduct[i].discount+">\n" +
            "                                </td>\n" +
            "                                <td class=\"total\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+((listProduct[i].price*listProduct[i].qty)*((100-listProduct[i].discount)/100)).toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +
            "                                 <td>\n" +
            "                                  <i class=\"fa fa-remove fa-lg\" style=\"position:relative;top:8px ;color:red\" onclick='removeProduct("+listProduct[i].productId+")'></i>\n" +
            "                                </td>"+

            "                            </tr>";
    }
    for(let i in listCombo){
        html+=" <tr>\n" +
            "                                <td class=\"cart_product_desc\">\n" +
            "                                    <h5>"+listCombo[i].comboName+"</h5>\n" +
            "                                </td>\n" +

            "                                <td class=\"qty\">\n" +

            "                                            <input type=\"text\" class=\"qty-text form-control\" id='qty"+listCombo[i].comboId+"' step=\"1\" min=\"1\" max=\"300\" name=\"quantity\" value="+listCombo[i].qty+">\n" +

            "                                </td>\n" +

            "                                <td class=\"price\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+listCombo[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +

            "                                <td class=\"discount\">\n" +
            "                                            <input type=\"text\"  class=\"qty-text form-control\" id='discount"+listCombo[i].comboId+"' step=\"1\" min=\"1\" max=\"300\" name=\"discount\" value="+listCombo[i].discount+">\n" +
            "                                </td>\n" +
            "                                <td class=\"total\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+((listCombo[i].price*listCombo[i].qty)*((100-listCombo[i].discount)/100)).toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +
            "                                 <td>\n" +
            "                                  <i class=\"fa fa-remove fa-lg\" style=\"position:relative;top:8px ;color:red\" onclick='removeCombo("+listCombo[i].comboId+")'></i>\n" +
            "                                </td>"+
            "                            </tr>";
    }
    document.getElementById("listCartTemp").innerHTML=html;
}
function ajaxrendercart(){
    $.ajax({
        url:"/api/cart/show",
        type:"GET",
        success:renderCart,
        error:function (e){
        }
    });
}
function removeCombo(id){
    $.ajax({
        url:"/api/cart/combo/remove/"+id,
        type:"GET",
        success:ajaxrendercart,
        error:function (e){
            console.log(e);
        }

    });
}
function removeProduct(id){
    $.ajax({
        url:"/api/cart/product/remove/"+id,
        type:"GET",
        success:ajaxrendercart,
        error:function (e){
            console.log(e);
        }

    });
}
$("#luuhoadon").click(function(){
    $.ajax({
        url:"/api/cart/save/",
        type:"GET",
        success:ajaxrendercart,
        error:function (e){
            console.log(e);
        }

    });
})