function productPaganition(res) {
    let html = "";
    let listRes = res.listReceipt;
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
        html+=" <tr>\n" +
            "                                    <th scope=\"row\"><input type='checkbox' class='receipt-record' value="+listRes[i].id+" /></th>\n" +
            "                                    <td>"+(Number(i)+1)+"</td>\n" +
            "                                    <td>"+listRes[i].shd+"</td>\n" +
            "                                    <td>"+new Date(listRes[i].ngay).toLocaleString()+"</td>\n" +
            "                                    <td>"+listRes[i].khachHang+"</td>\n" +
            "                                    <td>"+listRes[i].tienHang.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                    <td>"+listRes[i].giamGia.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                    <td>"+(listRes[i].tienHang-listRes[i].giamGia).toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                    <td><i class=\"fa fa-folder-open\"></i> " +
            "<a href='/admin/receipt/"+listRes[i].id+"/edit?shd="+listRes[i].shd+"'>View</a></td>\n" +
            "                                </tr>";
    }
    $("#ReceiptTable").html(html);
    let sum=0;
    if(listRes.length>1){
        sum=listRes.map(e=>e.tienHang-e.giamGia).reduce((e1,e2)=>e1+e2);
    }
    else{
        if(listRes.length===1){
            sum=listRes[0].tienHang-listRes[0].giamGia;
        }
    }
    document.getElementById("tongcong").innerText=sum.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

function getNextPage(page) {
    let start=$("#start").val();
    let end=$("#end").val();
    if(!start&&!end){
        return AjaxQuery(productPaganition, "/api/employee/receipt/" + page);
    }
    else{
        return AjaxQuery(productPaganition, "/api/employee/receipt/" + page +"?start="+start+"&end="+end);
    }

}
$("#btn-dateSearch").click(function (){
    getNextPage(1);
});
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
(function(){
    document.getElementById("validCheck").addEventListener("change",()=>{  if(document.getElementById("validCheck").checked){
        document.querySelectorAll(".receipt-record").forEach(item=>item.checked=true);
    }
    else{
        document.querySelectorAll(".receipt-record").forEach(item=>item.checked=false);
    }});
})();
function deleteReceipt(){
    let res=[];
    document.querySelectorAll(".receipt-record").forEach(item=>{
        if(item.checked) res.push(Number(item.value));
    });
    if(res.length>0){
        const par = res.join(',');
        $.ajax({
            url: "/api/admin/receipt/delete?listId=" + par,
            type: "DELETE",
            success: function (res) {
                alert("Xóa thành công!");
                AjaxQuery(productPaganition,"/api/employee/receipt/1");
                $("#formDelete").hide();
            },
            error: function (error) {
                alert("Đã xảy ra lỗi");
            }
        });
    }
    else {
        alert("Vui lòng chọn nhóm hàng muốn xóa!"); $("#formDelete").hide();
    }
}
