function productPaganition(res) {
    let html = "";
    let listRes = res.list;
    let htmlpagination = "";
    for (let i = 1; i <= res.totalPage; i++) {
        if (i == res.page) {
            htmlpagination += "<li class=\"page-item active\"><span class=\"page-link\">" + (i < 10 ? "0" : "") + i + ".</span></li>";
        } else {
            htmlpagination += "<li class=\"page-item\"><span class=\"page-link\"  onclick='getNextPage(" + i + ")' >" + (i < 10 ? "0" : "") + i + ".</span></li>";
        }
    }
    document.querySelector("#pagination").innerHTML = htmlpagination;
    for (let i in listRes) {
        html +="<tr>\n" +
            "                                    <td>"+(Number(i)+1)+"</td>\n" +
            "                                    <td>"+listRes[i].productName+"</td>" +
            "                                    <td>"+listRes[i].unit+"</td>\n" +
            "                                    <td>"+listRes[i].soluong +"</td>\n" +
            "                                    <td>"+listRes[i].tienTonKho.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                </tr>";
    }
    $("#InventoryTable").html(html);
}

function getNextPage(page) {
    return AjaxQuery(productPaganition, "/api/employee/inventory/" + page);
}
function AjaxQuery(callback,url){
    $.ajax({
        url:url,
        type:"GET",
        success:callback,
        error:function (e){
            console.log(e);
        }
    });
}
AjaxQuery(productPaganition,"/api/employee/inventory/1");