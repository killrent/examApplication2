/**
 * Created by ssHss on 2017/6/13.
 */
//题库页面的试卷接收器
var PReceiver ={
    name:"paper",
    //this paperId just for View ,it don`t mean the id in DataBase!
    paperId:1,
    paperSet:null,
    target:"#paper-area",
    setTarget:function (target) {
        this.target=target;
    },
    getPaperId:function () {
        return this.paperId++;
    },
    getPapers:function (result) {
        this.paperSet = result;
    },
    insertPapers:function () {
        //要在主结果面板插入试卷卡片
        //先将搜索面板清空
        $(this.target).empty();
        var card="";

        for(var i =0;i<this.paperSet.length;i++){

            card = "\
            <div class=\"col-xs-12 col-sm-6 col-md-4 col-lg-4\">\
                <div class =\"paperCard\">\
                    <div class=\"imgWrapper\" data-paper-id=\""+this.paperSet[i].id+"\">\
                        <div class=\"fade-text\">"+this.paperSet[i].brief+"</div>\
                    </div>\
                    <div class=\"paperInf\">\
                        <div class=\"paperTitle text-center\"><h4><a target=\"_blank\" href=\"search.html?keyword="+this.paperSet[i].name+"\">"+this.paperSet[i].name+"</a></h4></div>\
                        <div class=\"row\">\
                            <div class=\"questionNum col-xs-6 text-center\"><h5>20道题</h5></div>\
                            <div class=\"answerNum col-xs-6 text-center\"><h5>"+this.paperSet[i].click+"人已做</h5></div>\
                        </div>\
                    </div>\
                </div>\
            </div>";

            $(this.target).append(card);
        }

        $(".imgWrapper").each(function () {

            $(this).css("background-image","url(image/paperImg/"+$(this).attr("data-paper-id")+".jpg)")
        })
    }
}



$(function () {
    //直接获取试卷
    $.ajax({
        type:"POST",
        url:"/SearchServlet.do",
        timeout:5000,
        data:{
            type:"paper",
            //空字符串搜索所有试卷
            keyword:""
        },
        dataType:"json",
        success:function(result){
            PReceiver.getPapers(result);
            PReceiver.insertPapers();
        }
    });
})