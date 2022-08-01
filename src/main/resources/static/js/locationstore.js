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
function LCPagination(res){
    let htmlpagination = "";
    for (let i = 1; i <= res.totalPage; i++) {
        if (i == res.page) {
            htmlpagination += "<li class=\"page-item active\"><span class=\"page-link\">" + (i < 10 ? "0" : "") + i + ".</span></li>";
        } else {
            htmlpagination += "<li class=\"page-item\"><span class=\"page-link\"  onclick='getNextPage(" + i + ")' >" + (i < 10 ? "0" : "") + i + ".</span></li>";
        }
    }
    document.querySelector("#pagination").innerHTML = htmlpagination;
    getCategory(res.list);

}
function getCategory(res){
    let html="";
    for (let i in res){
        html+=" <tr>\n" +
            "                                    <th scope=\"row\"><input type='checkbox' class='lc-record' value="+res[i].id+" /></th>\n" +
            "                                    <td>"+(Number(i)+1)+"</td>\n" +
            "                                    <td>"+res[i].code+"</td>\n" +
            "                                    <td>"+res[i].name+"</td>\n" +
            "                                    <td>"+(res[i].mota==null?" ":res[i].mota)+"</td>\n" +
            "                                    <td><i class=\"fa fa-pencil\"></i> " +
            "<a href='/admin/LocationStore/"+res[i].id+"/edit'>Edit</a></td>\n" +
            "                                </tr>";
    }
    document.getElementById("categoryTable").innerHTML=html;
}
function addCategory(){
    let code=$("#code");
    let cateName=$("#cateName");
    let cateDes= $("#cateDes");

    $.ajax({
        url:"/api/admin/LocationStore/add",
        type:"POST",
        data:JSON.stringify({
            "id":null,
            "code":code.val(),
            "name":cateName.val(),
            "mota":cateDes.val()
        }),
        contentType:"application/json",
        dataType:'json',
        success:function(res){
            code.val("");
            cateName.val("");
            cateDes.val("");
            alert("Thêm thành công!");
            let html=" <tr>\n" +
                "                                    <th scope=\"row\"><input type='checkbox' class='lc-record' value="+res.id+" />  </th>\n" +
                "                                    <td>"+(Number($("tbody tr").length)+1)+"</td>\n" +
                "                                    <td>"+res.code+"</td>\n" +
                "                                    <td>"+res.name+"</td>\n" +
                "                                    <td>"+(res.mota==null?" ":res.mota)+"</td>\n" +
                "                                    <td><i class=\"fa fa-pencil\"></i> " +
                "<a href='/admin/LocationStore/"+res.id+"/edit'>Edit</a></td>\n" +
                "                                </tr>";

            document.getElementById("categoryTable").innerHTML+=html;
        },
        error:function(e){
            console.log("Có biến rồi đại nhân ơi!");
        }
    });
}
AjaxQuery(LCPagination,"/api/employee/LocationStore/1");
function deleteCate(){
    let res=[];
    document.querySelectorAll(".cate-record").forEach(item=>{
        if(item.checked) res.push(Number(item.value));
    });
    if(res.length>0){
        const par = res.join(',');
        $.ajax({
            url: "/api/admin/LocationStore/delete?listId=" + par,
            type: "DELETE",
            success: function (res) {
                alert("Xóa thành công!");
                AjaxQuery(LCPagination,"/api/employee/LocationStore/1");
                $("#formDeleteCategory").hide();
            },
            error: function (error) {
                alert("Đã xảy ra lỗi");
            }
        });
    }
    else {
        alert("Vui lòng chọn nhóm hàng muốn xóa!"); $("#formDeleteCategory").hide();
    }
}
(function(){
    document.getElementById("validCheck").addEventListener("change",()=>{  if(document.getElementById("validCheck").checked){
        document.querySelectorAll(".lc-record").forEach(item=>item.checked=true);
    }
    else{
        document.querySelectorAll(".lc-record").forEach(item=>item.checked=false);
    }});
})();

function getNextPage(page) {
    $.ajax({
        url: "/api/employee/LocationStore/" + page,
        type: "GET",
        success: CategoryPagination,
        error: function (e) {
            console.log(e);
        }
    });
}
