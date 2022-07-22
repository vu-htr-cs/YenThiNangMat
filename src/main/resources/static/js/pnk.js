
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
$("#addProduct").click(addToTemp);
function addToTemp(e){
    e.preventDefault();
    let el = $("#SelectProduct")[0];  //used [0] is to get HTML DOM not jquery Object
    let dl = $("#AllProduct")[0]
    let gia=$("#gia");
    let soluong=$("#soluong");
    if (el.value.trim() != '') {
        var opSelected = dl.querySelector(`[value="${el.value}"]`);
        let productId= opSelected.getAttribute('id');
        $.ajax({
            url:"/api/pnkitem/add?productId="+productId +"&giavon="+gia.val() +"&soluong="+soluong.val() ,
            type:"GET",
            success:function (result) {
                AjaxQuery(renderItem,"/api/pnkitem/show");
                $("#SelectProduct").val("");
                gia.val("");
                soluong.val("");

            },
            error:function (exception) {
                alert("Co loi xay ra");
            }

        });
    }
}
function renderItem(res){
    let html="";
    for(let i in res){
        html+=" <tr>\n" +
            "                                <th scope='row' class=\"cart_product_desc\">\n" +
            "                                    <h5>"+res[i].productName+"</h5>\n" +
            "                                </th>\n" +
            "                                <td class=\"unit\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+res[i].unit+"</span>\n" +
            "                                </td>\n" +

            "                                <td class=\"price\">\n" +
            "                                    <span style=\"position:relative;top:10px;\">"+res[i].giavon.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</span>\n" +
            "                                </td>\n" +
            "                                <td class=\"qty\">\n" +
            "                                    <h5>"+res[i].soluong+"</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"thanhtien\">\n" +
            "                                    <h5>"+(res[i].soluong*res[i].giavon).toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</h5>\n" +
            "                                </td>\n" +
            "                                <td class=\"remove\">\n" +
            "                                    <h5><i class=\"fa fa-remove fa-lg\" style=\"position:relative;bottom:2px ;color:red\" onclick='removeProduct("+res[i].productId+")'></i></h5>\n" +
            "                                </td>\n" +
            "                            </tr>";
    }
    document.getElementById("listProductTemp").innerHTML=html;
    let sum=0;
    if(res.length>1){
        sum= res.map(e => e.giavon * e.soluong).reduce((e1, e2) => e1 + e2);
    }else{
        if(res.length===1){
            sum=res[0].giavon*res[0].soluong;
        }
    }
    document.getElementById("tongcong").innerText=sum.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});

}
function removeProduct(id){
    $.ajax({
        url:"/api/pnkitem/remove/"+id,
        type:"GET",
        success:function(result){
            AjaxQuery(renderItem,"/api/pnkitem/show");
        },
        error:function(e){
            console.log(e);
        }
    });
}
function addPNK(){
    let khoID=$("#khoID");
    let nccID=$("#nccID");
    let ghiChu=$("#ghiChu");
    let nguoiNhan=$("#nguoiNhan");
    let ngayGhiSo=$("#ngayGhiSo");
    let loaiNhapKho=$("#loaiNhapKho");
    let d=new Date();
    data={
        "id":null,
        "khoID":khoID.val(),
        "nccID":nccID.val(),
        "ghiChu":ghiChu.val(),
        "nguoiNhan":nguoiNhan.val(),
        "ngayGhiSo":ngayGhiSo.val(),
        "loaiNhapKho":loaiNhapKho.val(),
        "soChungTu":"PNK"+$("#ngayGhiSo").val().split('-').join('').slice(2)+"-"+d.getHours()+d.getMinutes(),
        "sotien":null
    }
    $.ajax({
        url:"/api/pnkitem/save",
        type:"POST",
        data:JSON.stringify(data),
        contentType:'application/json',
        success:function(res){
            ghiChu.val("");
            nguoiNhan.val("");
            AjaxQuery(renderItem,"/api/pnkitem/show");
        },
        error:function (e){
            console.log(e);
        }
    });
}
function RenderPNK(res){
    let html="";
    for(let i in res){
        html+=" <tr>\n" +
            "                                    <th scope=\"row\"><input type='checkbox' class='pnk-record' value="+res[i].id+" /></th>\n" +
            "                                    <td>"+(Number(i)+1)+"</td>\n" +
            "                                    <td>"+res[i].soChungTu+"</td>\n" +
            "                                    <td>"+res[i].ngayGhiSo+"</td>\n" +
            "                                    <td>"+res[i].loaiNhapKho+"</td>\n" +
            "                                    <td>"+res[i].ncc+"</td>\n" +
            "                                    <td>"+res[i].kho+"</td>\n" +
            "                                    <td>"+res[i].sotien.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                    <td>"+res[i].ghiChu+"</td>\n" +
            "                                    <td><i class=\"fa fa-pencil\"></i> " +
            "<a href='/admin/category/"+res[i].id+"/edit'>Edit</a></td>\n" +
            "                                </tr>";
    }
    document.querySelector("#PNKTable").innerHTML=html;
}
AjaxQuery(RenderPNK,"/api/employee/pnk/show");
(function(){
    document.getElementById("validCheck").addEventListener("change",()=>{  if(document.getElementById("validCheck").checked){
        document.querySelectorAll(".pnk-record").forEach(item=>item.checked=true);
    }
    else{
        document.querySelectorAll(".pnk-record").forEach(item=>item.checked=false);
    }});
})();
