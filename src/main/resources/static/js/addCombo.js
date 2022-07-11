function AjaxQuery(callbackf,url) {
    const request = new XMLHttpRequest();
    request.open("GET", url, true);
    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                let res=JSON.parse(request.responseText);
                callbackf(res);
            }
        }
        request.send();
    } catch (e) {
        alert("Unable to connect server!")
    }
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
            "                                <p class=\"card-text\" style=\"color:red\">"+listRes[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</p>\n" +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#listProduct").html(html);
}
function getNextPage(page){
    return AjaxQuery(productPaganition,"/api/getproduct/"+page);
}
AjaxQuery(productPaganition,"/api/getproduct/1");

function renderComboTemp(res){
    let html="";
    for(let i in res){
        html+=" <tr>\n" +
            "                                <td class=\"cart_product_img\">\n" +
            "                                    <a href='#'><img width='120' height='76' src='/img/product-img/"+res[i].img+".jpg' alt=\"Product\"></a>\n" +
            "                                </td>\n" +
            "                                <td class=\"cart_product_desc\">\n" +
            "                                    <h5>"+res[i].productName+"</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"price\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+res[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +
            "                                <td class=\"qty\">\n" +
            "                                    <div class=\"qty-btn d-flex\">\n" +
            "                                        <p>Qty</p>\n" +
            "                                        <div class=\"quantity\">\n" +
            "                                            <span class=\"qty-minus\" onclick='minusClick("+res[i].productID+")' ><i class=\"fa fa-minus\" aria-hidden=\"true\"></i></span>\n" +
            "                                            <input type=\"text\" readonly class=\"qty-text\" id='qty"+res[i].productID+"' step=\"1\" min=\"1\" max=\"300\" name=\"quantity\" value="+res[i].qty+">\n" +
            "                                            <span class=\"qty-plus\" onclick='plusClick("+res[i].productID+")' ><i class=\"fa fa-plus\" aria-hidden=\"true\"></i></span>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </td>\n" +
            "                            </tr>";
    }
    document.getElementById("listComboTemp").innerHTML=html;
}
function plusClick(id){
    let effect = document.getElementById('qty'+id);
    let qty = effect.value;
    if( !isNaN( qty )) {
        $.ajax({
            url:"/api/combotemp/add/"+id,
            type:"GET",
            success:function(result){
                effect.value++;
                AjaxQuery(renderComboTemp,"/api/combotemp/show");
            },
            error:function(error){
                console.log(error);
            }
        });
    }
}
function minusClick(id){
    let effect = document.getElementById('qty'+id);
    let qty = effect.value;
    if( !isNaN( qty ) && qty> 1 )
    {

        $.ajax({
            url:"/api/combotemp/subtract/"+id,
            type:"GET",
            success:function(result){
                effect.value--;
                AjaxQuery(renderComboTemp,"/api/combotemp/show");
            },
            error:function(error){
                console.log(error);
            }
        });
    }
    else {
        $.ajax({
            url:"/api/combotemp/remove/"+id,
            type:"GET",
            success:function (res){

            },
            error:function (e){
                console.log(e);
            }
        });

        AjaxQuery(renderComboTemp,"/api/combotemp/show");
    }
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

$("#huy-btn").click(function (){
    $.ajax({
        url:"/api/combotemp/clear",
        type:"GET",
        success:function (res){
            AjaxQuery(renderComboTemp,"/api/combotemp/show");
            $("#comboName").val("");
            $("#price").val("");
        },
        error:function (e){console.log(e);}
    });
});
function addCombo(){
    let comboName=$("#comboName");
    let price=$("#price");
    $.ajax({
        url:"/api/combotemp/save?"+"comboName="+comboName.val()+"&price="+price.val(),
        type:"GET",
        success:function(result){
            comboName.val("");
            price.val("");
            AjaxQuery(renderComboTemp,"/api/combotemp/show");

        },
        error:function (exception){
            console.log(exception);
        }
    });

}
