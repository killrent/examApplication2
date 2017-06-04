/**
 * Created by 10388 on 2017/6/4.
 */
$(document).ready(function () {

    var $a = $('#user-href');
    var $userTitle = $('h2:eq(0)');

    $.ajax({
        url:"controlServlet.do",
        data:{type:'getAccess'}, //传输键值对
        aysnc:true,
        cache:false,
        type:'POST',
        dataType:'json',
        success:function (data) {

            var obj = data.userObj;

            $a.html(obj.name);
            $userTitle.html(obj.name+"<small>@"+obj.email+"</small>");
            $userTitle.next("p").html(obj.signature);
        },
        error:function () {
            alert('服务器无响应，错误信息为'+ arguments[1]);
        }
    });
});