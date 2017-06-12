$(document).ready(function () {
    var $ul0 = $('.ul-state:eq(0)');
    var $ul1 = $('.ul-state:eq(1)');

    $ul0.css('display','none');
    $ul1.css('display','block');

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
            var paper = obj.paperBeans;
            var record = obj.userRPapers;

            $a.html(obj.name);
            $userTitle.html(obj.name+"<small>"+obj.email+"</small>");
            $userTitle.next("p").html(obj.signature);

            $('#inputName').val(obj.name);
            $('#inputEmail').val(obj.email);
            $('#inputSignature').html(obj.signature);

            $('.status-bar:eq(0)').find('p:eq(0)').html(paper.length);
            $('.status-bar:eq(1)').find('p:eq(0)').html(paper.length * 20);


            //加载前三个考试记录
            $.each($('.exam-item'),function (i, val) {
                if(paper[i] !== undefined){
                    $(this).css('display','block');
                    $(this).find('.exam-name').html(paper[i].name);
                    $(this).find('li[title=点击量]').append(paper[i].click);
                    $(this).find('li[title=正确率]').append(record[i].accuracy * 100 + '%');

                    var $time = $(this).find('.exam-time');
                    var stringTime = record[i].time;
                    var timestamp = Date.parse(new Date(stringTime)) / 1000;
                    var currentTime = Date.parse(new Date()) / 1000;

                    var timeGap = currentTime - timestamp;

                    if(timeGap < 300){
                        $time.html("提交于 刚刚");
                    }else if(timeGap < 1800){
                        $time.html("提交于 5分钟前");
                    }else if(timeGap < 7200){
                        $time.html("提交于 30分钟前");
                    }else if(timeGap < 86400){
                        $time.html("提交于 2小时前");
                    }else{
                        $time.html("提交于 1天前");
                    }
                }else{
                    $(this).attr('display','none');
                }
            });

            //加载全部考试记录

            var $table = $('#record-infor').find('table');
            $.each(record,function (i, val) {
                $table.append("<tr><td>"+ (i + 1) +"</td>" + "<td>"+ paper[i].name +"</td>" + "<td>"+ record[i].timeUsed +"</td>"+ "<td>"+record[i].time +"</td>"+ "<td>"+(record[i].accuracy*100) +"%</td>"+ "<td>"+paper[i].click +"</td></tr>");
            });
        },
        error:function () {
            alert('服务器无响应，错误信息为'+ arguments[1]);
        }
    });


    $('#arranged-test').find('.panel-content').html('<span class="不存在的">不存在的</span>');
    $('#favourite').find('.panel-content').html('<span class="不存在的">不存在的</span>');


});