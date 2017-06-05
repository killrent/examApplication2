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

            var record = obj.userRPapers;
            var paper = obj.paperBean;

            $a.html(obj.name);
            $userTitle.html(obj.name+"<small>@"+obj.email+"</small>");
            $userTitle.next("p").html(obj.signature);

            $.each($('.exam-name'),function (i, val) {
                $(this).html(paper[i].name);
            });

        },
        error:function () {
            alert('服务器无响应，错误信息为'+ arguments[1]);
        }
    });
});