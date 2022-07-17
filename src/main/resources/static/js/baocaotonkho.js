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
        html += " <div onclick='addToCombo(" + listRes[i].id + ")' class=\"card col-lg-4 col-md-3\">\n" +
            "                            <img class=\"card-img-top\" src='/img/product-img/" + listRes[i].img + ".jpg' width='189' height='120' alt=\"Card image cap\">\n" +
            "                            <div class=\"card-body\">\n" +
            "                                <h5 class=\"card-title\" style='font-weight: bold'>" + listRes[i].productName + "</h5>\n" +
            "                                <p class=\"card-text\" style=\"color:red\">" + listRes[i].price.toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }) + "</p>\n" +
            "                            </div>\n" +
            "                        </div>";
    }
    $("#listProduct").html(html);
}

function getNextPage(page) {
    return AjaxQuery(productPaganition, "/api/getproduct/" + page);
}


document.getElementById("validCheck").addEventListener("change", () => {
    if (document.getElementById("validCheck").checked) {
        document.querySelectorAll(".cate-record").forEach(item => item.checked = true);
    } else {
        document.querySelectorAll(".cate-record").forEach(item => item.checked = false);
    }
});