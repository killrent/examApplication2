$(document).ready(function () {

    var $ul0 = $('.ul-state:eq(0)');
    var $ul1 = $('.ul-state:eq(1)');
    var $a = $('#user-href');

    $.ajax({
        url:"controlServlet.do",
        data:{type:'getAccess'}, //传输键值对
        aysnc:true,
        cache:false,
        type:'POST',
        dataType:'json',
        success:function (data) {

            var email = data.email;

            var obj = data.userObj;

            console.log(obj);

            if(email !== undefined){
                $ul0.css("display","none");
                $ul1.css("display","block");
                $a.html(obj.name);
            }else {
                $ul0.css("display","block");
                $ul1.css("display","none");
            }
        },
        error:function () {
            alert('服务器无响应，错误信息为'+ arguments[1]);
        }
    });
});