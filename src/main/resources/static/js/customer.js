function addCustomer() {
    let name = $("#name");
    let birthday = $("#birthday");
    let address = $("#address");
    let phone = $("#phone");
    let groupCustomer = $("#groupCustomer");
    let description = $("#description");
    $.ajax({
        url: "/api/admin/customer/add",
        type: "POST",
        data: JSON.stringify({
            id: null,
            name: name.val(),
            address: address.val(),
            phone: phone.val(),
            birthday: birthday.val(),
            groupCustomer: groupCustomer.val(),
            point: null,
            discount: null,
            description: description.val()
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (res) {
            name.val("");
            address.val("");
            phone.val("");
            birthday.val("");
            groupCustomer.val("");
            description.val("");
            alert("Thêm thành công!");
            let html="<tr>\n" +
                "                                    <th scope=\"row\"><input type='checkbox' class='customer-record' value=" + res.id + " />  </th>\n" +
                "                                    <td>" + (Number($("tbody tr").length)+1) + "</td>\n" +
                "                                    <td>" + res.name + "</td>" +
                "                                    <td>" + res.address + "</td>\n" +
                "                                    <td>" + res.phone + "</td>\n" +
                "                                    <td>" + res.birthday + "</td>\n" +
                "                                    <td>" + res.groupCustomer + "</td>\n" +
                "                                    <td>" + res.point + "</td>\n" +
                "                                    <td>" + res.discount + "</td>\n" +
                "                                    <td>" + res.description + "</td>\n" +
                "                                    <td><i class=\"fa fa-pencil\"></i> " +
                "                                    <a href='/admin/category/" + res.id + "/edit'>Edit</a></td>\n" +
                "                                </tr>";
            document.getElementById("CustomerTable").innerHTML+=html;
        },
        error: function (e) {
            console.log(e);
        }

    });
}

$.ajax({
    url: "/api/admin/customer/1",
    type: "GET",
    success: customerPaganition,
    error: function (e) {
        console.log(e);
    }
});

function customerPaganition(res) {
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
        html += "<tr>\n" +
            "                                    <th scope=\"row\"><input type='checkbox' class='customer-record' value=" + listRes[i].id + " />  </th>\n" +
            "                                    <td>" + (Number(i) + 1) + "</td>\n" +
            "                                    <td>" + listRes[i].name + "</td>" +
            "                                    <td>" + listRes[i].address + "</td>\n" +
            "                                    <td>" + listRes[i].phone + "</td>\n" +
            "                                    <td>" + listRes[i].birthday + "</td>\n" +
            "                                    <td>" + listRes[i].groupCustomer + "</td>\n" +
            "                                    <td>" + listRes[i].point + "</td>\n" +
            "                                    <td>" + listRes[i].discount + "</td>\n" +
            "                                    <td>" + listRes[i].description + "</td>\n" +
            "                                    <td><i class=\"fa fa-pencil\"></i> " +
            "                                    <a href='/admin/category/" + listRes.id + "/edit'>Edit</a></td>\n" +
            "                                </tr>";
    }
    $("#CustomerTable").html(html);
}

function getNextPage(page) {
    $.ajax({
        url: "/api/admin/customer/" + page,
        type: "GET",
        success: customerPaganition,
        error: function (e) {
            console.log(e);
        }
    });
}

(function(){
    document.getElementById("validCheck").addEventListener("change",()=>{  if(document.getElementById("validCheck").checked){
        document.querySelectorAll(".customer-record").forEach(item=>item.checked=true);
    }
    else{
        document.querySelectorAll(".customer-record").forEach(item=>item.checked=false);
    }});
})();

function deleteCustomer(){
    let res=[];
    document.querySelectorAll(".customer-record").forEach(item=>{
        if(item.checked) res.push(Number(item.value));
    });
    if(res.length>0){
        const par = res.join(',');
        $.ajax({
            url: "/api/admin/customer/delete?listId=" + par,
            type: "DELETE",
            success: function (res) {
                alert("Xóa thành công!");
                window.location.href="/admin/customer";
                $("#formDelete").hide();
            },
            error: function (error) {
                alert("Đã xảy ra lỗi");
            }
        });
    }
    else {
        alert("Vui lòng chọn khách hàng muốn xóa!"); $("#formDelete").hide();
    }
}