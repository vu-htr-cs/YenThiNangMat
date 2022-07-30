function renderReceipt(res) {
    let listProduct = res.listProduct;
    let listCombo = res.listCombo;
    let html = "";
    let d = new Date();
    let tongcong = 0;
    listProduct.forEach(item => tongcong += (item.price * item.qty * (100 - item.discount) / 100));
    listCombo.forEach(item => tongcong += (item.price * item.qty * (100 - item.discount) / 100));
    for (let i in listProduct) {
        html += " <tr>\n" +
            "                                <td> " + (Number(i) + 1) + " </td>" +
            "                                <td class=\"cart_product_desc\">\n" +
            "                                    <h5>" + listProduct[i].productName + "</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"qty qty-product text-center\">\n" +
            "                                            <span style=\"position:relative;top:10px;\">" + listProduct[i].qty + "</span> \n" +
            "                                </td>\n" +

            "                                <td class=\"price text-center\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">" + listProduct[i].price.toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }) + "</span>\n" +
            "                                </td>\n" +

            "                                <td class=\"discount-product text-center\">\n" +
            "                                     <span style=\"position:relative;top:10px;\">" + listProduct[i].discount + "</span>\n" +
            "                                </td>\n" +
            "                                <td class=\"total text-right\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">" + ((listProduct[i].price * listProduct[i].qty) * ((100 - listProduct[i].discount) / 100)).toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }) + "</span>\n" +
            "                                </td>\n" +
            "                            </tr>";
    }
    for (let i in listCombo) {
        html += " <tr>\n" +
            "                                <td> " + (i + 1) + " </td>" +
            "                                <td class=\"cart_product_desc\">\n" +
            "                                    <h5>" + listCombo[i].comboName + "</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"qty qty-combo text-center\">\n" +
            "                                            <span style=\"position:relative;top:10px;\">" + listCombo[i].qty + "</span> \n" +
            "                                </td>\n" +
            "                                <td class=\"price text-center\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">" + listCombo[i].price.toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }) + "</span>\n" +
            "                                </td>\n" +
            "                                <td class=\"discount-combo text-center\">\n" +
            "                                     <span style=\"position:relative;top:10px;\">" + listCombo[i].discount + "</span>\n" +
            "                                </td>\n" +
            "                                <td class=\"total text-right\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">" + ((listCombo[i].price * listCombo[i].qty) * ((100 - listCombo[i].discount) / 100)).toLocaleString('it-IT', {
                style: 'currency',
                currency: 'VND'
            }) + "</span>\n" +
            "                                </td>\n" +
            "                            </tr>";
    }
    html += "<tr> <td colspan='5'></td></tr>";
    html += " <tr>\n" +
        "                                        <td colspan=\"1\"></td>\n" +
        "                                        <td >Giờ in  " + (d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear() + "  " + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds() + "</td>\n" +
        "                                        <td colspan=\"1\"></td>\n" +
        "                                        <td class=\"text-left\"><strong>Thuế</strong></td>\n" +
        "                                        <td></td>\n" +
        "                                        <td class=\"text-right\"><strong>N/A</strong></td>\n" +
        "                                    </tr>";
    html += "   <tr>\n" +
        "                                        <td colspan=\"3\"></td>\n" +
        "                                        <td class=\"text-left\"><strong>Tổng cộng</strong></td>\n" +
        "                                        <td></td>\n" +
        "                                        <td class=\"text-right\"><strong>" + tongcong.toLocaleString('it-IT', {
            style: 'currency',
            currency: 'VND'
        }) + "</strong></td>\n" +
        "                                    </tr>";
    document.getElementById("listProductReceipt").innerHTML = html;
    $("#ngaylap").text((d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear());
    $("#sohoadon").text("HDL" + d.getFullYear() + (d.getMonth() + 1) + d.getDate() + d.getHours() + d.getMinutes() + d.getSeconds());
}
$("#SelectCustomer").change(function (){
    $("#khachhang").text($(this).val());
});
function printInvoice() {
    let el=$("#SelectCustomer")[0];
    let dl=$("#allcustomer")[0];
    if(el.value.trim()!=''){
        var opSelected = dl.querySelector(`[value="${el.value}"]`);
        var customeID= opSelected.getAttribute('id');
    }
    $.ajax({
        url: "/api/cart/show",
        type: "GET",
        success: function (res) {
            renderReceipt(res);
            printJS({
                printable: "receipt",
                type: "html",
                targetStyles: ['*'],
            });
            $.ajax({
                url:"/api/cart/insertListInvoice",
                type:"POST",
                contentType:"application/json",
                data:JSON.stringify({
                    soHoaDon:$("#sohoadon").text(),
                    customerId:customeID?Number(customeID):null
                }),
                success:function(res){
                    ajaxrendercart();
                },
                error:function(exception){
                    console.log(exception);
                }
            });
        },
        error: function (e) {
            console.log(e);
        }
    });//In hoa don ra ngoai
}
