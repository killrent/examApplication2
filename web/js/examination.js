(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!= null)
            return decodeURI(r[2]);
        return null;
    }
})(jQuery);

var userId;
var paperId;
var finishTime;
var accuracy;

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

            userId = obj.id;

            var javaObj = data.javaObj;

            console.log(javaObj);

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

//是否有试卷的缓存并读取
function initStoragePage() {
    if (window.sessionStorage.getItem("testTime")) {
        cur_time = parseInt(window.sessionStorage.getItem("testTime"));
    }
    if (window.sessionStorage.getItem("questions")) {
        var questions = JSON.parse(window.sessionStorage.getItem("questions"));
        $("#ques-list").empty();
        $("#quest-board").empty();
        QReceiver.getQuestions(questions);
        QReceiver.insertQuestions();
        start_test();
        getSummary();
        init_quest_view();
        //隐藏试卷概要
        $("#quest-preview").hide();
    }else{//假如没有题目的本地缓存的话加载试卷概要
       paperId =  $.getUrlParam("paperId");
        $.ajax({
            type: "POST",
            url: "/ExaminationServlet.do",
            timeout: 5000,
            data: {
                paperId: paperId,
                forDescription:true
            },
            dataType: "json",
            success: function (result) {
                $("#paper-name").text(result.name);
                $("#paper-brief").text(result.brief);
                $("#paper-click").text(result.click);
                $("#paper-create-time").text(result.createTime)
            }

        });
    }
    if (window.sessionStorage.getItem("userAnswer")) {
        var userAnswer = JSON.parse(window.sessionStorage.getItem("userAnswer"));
        var i = 0;
        console.log(window.sessionStorage.getItem("userAnswer"));
        $(".quest-footer").each(function () {
            for (var j = 0; j < userAnswer[i].answer.length; j++) {
                $(this).find("[value='" + userAnswer[i].answer[j] + "']").addClass("active");
            }
            i++;
        })
    }
}

function setQuestionsStorage(result) {
    var jsonStr = JSON.stringify(result);
    window.sessionStorage.setItem("questions", jsonStr);
}
//
var QReceiver = {
    name: "ques-card-",
    quesId: 0,
    quesSet: null,
    target: "",
    setTarget: function (selector) {
        this.target = selector;
    },
    getQuesId: function () {
        return this.quesId++;
    },
    getQuestions: function (result) {
        this.quesSet = result;
    },
    insertQuestions: function () {
        //在主结果页面或试卷页面插入问题卡片
        // 并为每个卡片分配一个id
        var questCard = "";
        var questLink = "";
        var len = this.quesSet.length;
        //问题section标签id
        var id = "ques-card-" + this.getQuesId();
        var quesType = "";
        var quesClass = "";

        function printOptionsText(options) {
            var chars = ["A", "B", "C", "D", "E", "F", "G"];
            var template = "";
            var result = "";
            for (var j = 0; j < options.length; j += 1) {
                template = "\
                <tr>\
                    <td>" + chars[j] + ". " + options[j] + " &nbsp;&nbsp;&nbsp;&nbsp;</td>\
                </tr>";
        result += template;
    }
    return result;
}
        function printOptions(options) {
            var chars = ["A", "B", "C", "D", "E", "F", "G"];
            var result = "";
            var template = "";
            for (var j = 0; j < options.length; j++) {
                template = "<button class=\"btn btn-default\" value=\"" + j + "\">" + chars[j] + "</button>";
                result += template;
            }
            return result;
        }

        for (var i = 0; i < len; i++) {
            quesType = (this.quesSet[i].type == 1) ? "单项" : "不定项";
            quesClass = (this.quesSet[i].type == 1) ? "opt-single" : "opt-multiple";
            questCard = "\
            <section class=\"quest\" id=\"" + id + "\">\
            <div class=\"quest-header\">\
                <h3>第 " + this.quesId + " 题 <small>" + quesType + "选择题</small></h3>\
            </div>\
            <hr/>\
            <div class=\"quest-content\">\
                <h4>" + this.quesSet[i].content + "</h4>\
                <table>\
                    " + printOptionsText(this.quesSet[i].items) + "\
                </table>\
            </div>\
            <hr/>\
            <div class=\"quest-footer " + quesClass + "\" data-quest-id=\"" + this.quesSet[i].id + "\">\
               " + printOptions(this.quesSet[i].items) + "\
            </div>\
            </section>";

            questLink = "<li class=\"list-group-item\"><a href=\"#" + id + "\">" + this.quesId + ". " + quesType + "选择题 <i class=\"fa fa-fw fa-chevron-right\"></i></a></li>";

            $("#quest-board").append(questCard);
            $("#ques-list").append(questLink);
            id = "ques-card-" + this.getQuesId();
        }

        quest_footer_nodes = $('.quest-footer');
    }
}

function getSummary() {
    $('#quest-overview').find('ul li a').each(function () {
        const qid = $(this).attr('href');
        var brief = $(qid + ' .quest-content').find('h4').text();
        if (brief.length > 9) {
            brief = brief.substr(0, 9) + ' ...';
        }

        $(this).append('<br/><span>' + brief + '</span>');
    });
}

function getUserAnswer(ans) {
    $(".quest-footer").each(function () {
        var singleAns = {"qid": 0, "answer": []};
        singleAns.qid = parseInt($(this).attr("data-quest-id"));
        singleAns.answer = [];
        $(this).find("button").filter(".active").each(function () {
            singleAns.answer.push(parseInt($(this).attr("value")));

        });
        ans.push(singleAns);
    });
}

function saveUserAnswer() {
    if ($(".quest-footer").length > 0) {
        var ansJson = [];
        getUserAnswer(ansJson);
        var jsonStr = JSON.stringify(ansJson);
        window.sessionStorage.setItem("userAnswer", jsonStr);
    }
}

function correctionResultToView(result) {
    function printCorrectChoice(correctChoice) {
        var str = "";
        var chioce = ["A", "B", "C", "D", "E", "F", "G"];
        for (var k = 0; k < correctChoice.length; k++) {
            str += chioce[correctChoice[k]];
        }
        return str;
    }

    var correctNum = 0;
    for (var i = 0; i < result.length; i++) {
        if (result[i].correct == true) {
            correctNum++;
        }
    }
    //在模态框中插入答题正确数
    $("#quest-accuracy").text("您答对了 " + correctNum + " / " + result.length + " 道题");
    var j = 0;
    accuracy = correctNum / result.length;

    $(".quest").each(function () {
        if (result[j].correct == true) {
            //插入正确标签
            $(this).find(".quest-header > h3").append("<span class=\"label right-top label-success\"> 正确答案: " + printCorrectChoice(result[j].correct_choice) + "</span>")
        } else {
            $(this).find(".quest-header > h3").append("<span class=\"label right-top label-danger\"> 正确答案: " + printCorrectChoice(result[j].correct_choice) + "</span>")
        }
        j++;
    });
    $(".loader").hide();
}

//解析URL
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!= null)
            return decodeURI(r[2]);
        return null;
    }
})(jQuery);

$(function () {
    initStoragePage();

    $("#quest-start").click(function () {

        $("#ques-list").empty();
        $("#quest-board").empty();

        //TODO
        var paperId = $.getUrlParam('paperId');

        //标签html
        $.ajax({
            type: "POST",
            url: "/ExaminationServlet.do",
            timeout: 5000,
            data: {
                paperId: paperId,
            },
            dataType: "json",
            success: function (result) {
                questions = result;
                QReceiver.getQuestions(result);
                QReceiver.insertQuestions();
                setQuestionsStorage(result)
                getSummary();
                init_quest_view();
                $("#quest-preview").hide()
            }
        });
    });

    $("#quest-submit").click(function () {
        var ans = [];
        getUserAnswer(ans);
        var ansStr = JSON.stringify(ans);

        $.ajax({
            type: "POST",
            url: "/SubmissionServlet.do",
            timeout: 5000,
            data: {
                userId: userId,
                paperId: paperId,
                secondUsed: 1200 - cur_time,
                submission: ansStr
            },
            dataType: "json",
            success: function (result) {
                setTimeout(function () {
                    correctionResultToView(result);
                    terminate_test();
                    window.sessionStorage.removeItem("questions");
                    window.sessionStorage.removeItem("testTime");
                    window.sessionStorage.removeItem("userAnswer");
                }, 1000);
            }
        });
    });

    $('#paper-submit').click(function () {
        self.location = 'user-center.html';
    });

    // $('#paper-submit').click(function () {
    //     $.ajax({
    //         type: "POST",
    //         url: "/infoServlet.do",
    //         timeout: 5000,
    //         data: {
    //             userId: userId,
    //             paperId: paperId,
    //             timeUsed: 1200 - cur_time,
    //             finishTime:getNowFormatDate(),
    //             accuracy:accuracy
    //         },
    //         dataType: "json",
    //         success: function (result) {
    //             alert('考试提交成功，正在转至个人中心...');
    //             self.location = 'user-center.html';
    //         },
    //         rror:function () {
    //             alert('服务器无响应，错误信息为'+ arguments[1]);
    //         }
    //     });
    // });
});

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}