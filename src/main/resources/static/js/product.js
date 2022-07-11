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
function getProduct(res){
    let html="";
    for (let i in res){
        html+=" <tr>\n" +
            "                                    <th scope=\"row\"><input type='checkbox' class='product-record' value="+res[i].id+" /></th>\n" +
            "                                    <td>"+(Number(i)+1)+"</td>\n" +
            "                                    <td>"+res[i].productName+"</td>"+
            "                                    <td>"+res[i].unitName+"</td>\n" +
            "                                    <td>"+res[i].categoryName+"</td>\n" +
            "                                    <td>"+res[i].price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
            "                                    <td>"+(res[i].content==null?" ":res[i].content)+"</td>\n" +
            "                                    <td><i class=\"fa fa-pencil\"></i> " +
            "<a href='/admin/product/"+res[i].id+"/edit'>Edit</a></td>\n" +
            "                                </tr>";
    }
    document.getElementById("ProductTable").innerHTML=html;
}

$("#btn-add").click(function(e){
    e.preventDefault();
    let name=$("#name");
    let unitId=$("#unitId");
    let caId=$("#CaId");
    let price=$("#price");
    let mota=$("#mota");
    $.ajax({
        url:"/api/product/add",
        type:"POST",
        data:JSON.stringify({
            "id":null,
            "name":name.val(),
            "unitId":unitId.val(),
            "categoryId":caId.val(),
            "price":price.val(),
            "content":mota.val(),
            "img":fileupload.files[0].name.replace(".jpg","")
        }),
        contentType:"application/json",
        dataType:'json',
        success:function(res){
            uploadFile();
            name.val("");
            unitId.val("");
            caId.val("");
            price.val("");
            mota.val("");
            alert("Thêm thành công!");
            let html=" <tr>\n" +
                "                                    <th scope=\"row\"><input type='checkbox' class='product-record' value="+res.id+" /></th>\n" +
                "                                    <td>"+(Number($("tbody tr").length)+1)+"</td>\n" +
                "                                    <td>"+res.productName+"</td>"+
                "                                    <td>"+res.unitName+"</td>\n" +
                "                                    <td>"+res.categoryName+"</td>\n" +
                "                                    <td>"+res.price.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+"</td>\n" +
                "                                    <td>"+(res.content==null?" ":res.content)+"</td>\n" +
                "                                    <td><i class=\"fa fa-pencil\"></i> " +
                "<a href='/admin/product/"+res.id+"/edit'>Edit</a></td>\n" +
                "                                </tr>";

            document.getElementById("ProductTable").innerHTML+=html;
        },
        error:function(e){
            console.log("Có biến rồi đại nhân ơi");
        }
    });
});
AjaxQuery(getProduct,"/api/product");
(function(){
    document.getElementById("validCheck").addEventListener("change",()=>{  if(document.getElementById("validCheck").checked){
        document.querySelectorAll(".product-record").forEach(item=>item.checked=true);
    }
    else{
        document.querySelectorAll(".product-record").forEach(item=>item.checked=false);
    }});
})();
function uploadFile(){
    let formData = new FormData();
    formData.append("file", fileupload.files[0]);
    $.ajax({
        url:"/api/product/upload",
        type:"POST",
        data:formData,
        processData:false,
        contentType: false,
        success:function(data){
        }
    });
}
function deleteProduct(){
    let res=[];
    document.querySelectorAll(".product-record").forEach(item=>{
        if(item.checked) res.push(Number(item.value));
    });
    if(res.length>0){
        const par = res.join(',');
        $.ajax({
            url: "/api/product/delete?listId=" + par,
            type: "DELETE",
            success: function (res) {
                alert("Xóa thành công!");
                AjaxQuery(getProduct,"/api/product");
                $("#formDelete").hide();
            },
            error: function (exception) {
                alert("Đã xảy ra lỗi");
            }
        });
    }
    else {
        alert("Vui lòng chọn sản phẩm muốn xóa!"); $("#formDelete").hide();
    }
}
