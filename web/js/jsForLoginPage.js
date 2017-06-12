
var $signInSubmit;
var $signUpSubmit;

var flag = true;

$(document).ready(function () {
    var $reminder = $('.help-block:eq(0)');
    var $reminder1 = $('.help-block:eq(1)');
    var $reminder2 = $('.help-block:eq(2)');
    var $reminder3 = $('.help-block:eq(3)');
    var $reminder4 = $('.help-block:eq(4)');

    var $signUpEmail = $('.status-span:eq(0)');
    var $signUpEmailDiv = $('#sign-up-email-div');
    var $signUpPassword = $('.status-span:eq(1)');
    var $signUpPasswordDiv = $('#sign-up-password-div');
    var $signUpPassword_confirm = $('.status-span:eq(2)');
    var $signUpPasswordDiv2 = $('#sign-up-password-div-confirm');

    var $signInEmailDiv = $('#sign-in-email-div');
    var $signInPasswordDiv = $('#sign-in-password-div');

    $signInSubmit = $('button[type=button]:eq(0)');
    $signUpSubmit = $('button[type=button]:eq(1)');

    var signInEmailValue;
    var signInPasswordValue;
    var signUpEmailValue;
    var signUpPasswordValue;
    var signUpPasswordConfirmValue;

    $('#example').popover('show');


    const myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

    //初始化
    $reminder.hide();
    $reminder1.hide();
    $reminder2.hide();
    $reminder3.hide();
    $reminder4.hide();

    var g1 = $('#sign-in-panel').find('.input-group');
    var g2 = $('#sign-up-panel').find('.input-group');

    //标签页事件
    $('a[data-toggle=tab]').on('shown.bs.tab',function (e) {
        var t = $(e.target);
        var ot = $(e.relatedTarget);

        ot.addClass('text-center');
        if(t.attr('id') === 'a1'){
            flag = true;
            t.html('登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录');
            ot.html('注册');
            g2.popover('hide');
        }else {
            flag = false;
            ot.html('登录');
            t.html('注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册');
            g1.popover('hide');
            g1.popover('hide');

        }
    });

    //查看密码文本
    $('.mySpan').hover(
        function () {
            $(this).next().attr('type','text');
            $(this).css('background-color','#d4d4d4');
        },
        function () {
            $(this).next().attr('type','password');
            $(this).css('background-color','#eee');
        }
    );

    //对登录邮箱地址进行本地校验
    $('#sign-in-email').keyup(function () {
        signInEmailValue = $(this).val();
        if(myreg.test(signInEmailValue)){
            $reminder.addClass('ok');
            $signInEmailDiv.popover('hide');
        }else {
            $reminder.removeClass('ok');
            $signInEmailDiv.attr('data-content','请输入正确的邮箱地址。');
            $signInEmailDiv.popover('show');
        }
    });

    //对登录密码进行本地校验
    function passwordCheck(password) {
        return !password || (!(password.length < 6 || password.length > 12));
    }

    $('#sign-in-password').keyup(function () {
       signInPasswordValue = $(this).val();
       if(signInPasswordValue === null){
           $reminder1.removeClass('ok');
       }
       if(passwordCheck(signInPasswordValue)){
           $reminder1.addClass('ok');
           $signInPasswordDiv.popover('hide');
       }else {
           $reminder1.removeClass('ok');
           $signInPasswordDiv.attr('data-content','请输入6到12位字符作为密码。');
           $signInPasswordDiv.popover('show');
       }
    });

    //对注册邮箱地址进行本地校验+服务器校验
    function signUpStrategy(result) {
        console.log(result);
        if(result.success){
            $signUpEmail.removeClass('glyphicon-remove');
            $signUpEmail.addClass('glyphicon-ok');
            $signUpEmailDiv.removeClass('has-error');
            $signUpEmailDiv.addClass('has-success');
            $signUpEmailDiv.popover('hide');
        }else{
            $signUpEmail.removeClass('glyphicon-ok');
            $signUpEmail.addClass('glyphicon-remove');
            $signUpEmailDiv.removeClass('has-success');
            $signUpEmailDiv.addClass('has-error');
            $signUpEmailDiv.attr('data-content','该邮箱地址已被占用。');
            $signUpEmailDiv.popover('show');
        }
    }

    $('#sign-up-email').change(function () {
        signUpEmailValue = $(this).val();
        if(myreg.test(signUpEmailValue)){
            $signUpEmail.removeClass('glyphicon-remove');
            $signUpEmail.addClass('glyphicon-ok');
            $signUpEmailDiv.removeClass('has-error');
            $signUpEmailDiv.addClass('has-success');
            useAjax(signUpStrategy);
            $signUpEmailDiv.popover('hide');
        }else{
            $signUpEmail.removeClass('glyphicon-ok');
            $signUpEmail.addClass('glyphicon-remove');
            $signUpEmailDiv.removeClass('has-success');
            $signUpEmailDiv.addClass('has-error');
            $signUpEmailDiv.popover('show');
            $signUpEmailDiv.attr('data-content','请输入正确的邮箱地址。');
        }
    });
    //对注册密码进行本地校验
    $('#sign-up-password').change(function () {
        signUpPasswordValue = $(this).val();
        if(passwordCheck(signUpPasswordValue)){
            $signUpPassword.removeClass('glyphicon-remove');
            $signUpPassword.addClass('glyphicon-ok');
            $signUpPasswordDiv.removeClass('has-error');
            $signUpPasswordDiv.addClass('has-success');
            confirm($(this).val(),$('#sign-up-password-confirm').val());
            $signUpPasswordDiv.popover('hide');
        }else {
            $signUpPassword.addClass('glyphicon-remove');
            $signUpPassword.removeClass('glyphicon-ok');
            $signUpPasswordDiv.addClass('has-error');
            $signUpPasswordDiv.removeClass('has-success');
            $signUpPasswordDiv.attr('data-content','密码长度不能少于6位或多于12位');
            $signUpPasswordDiv.popover('show');
            confirm($(this).val(),$('#sign-up-password-confirm').val());
        }
    });

    //对重复密码进行本地校验
    $('#sign-up-password-confirm').change(function () {
        signUpPasswordConfirmValue = $(this).val();
        confirm(signUpPasswordConfirmValue,$('#sign-up-password').val());
    });

    function confirm (password1, password2) {
        if(password1 === password2){
            $signUpPassword_confirm.removeClass('glyphicon-remove');
            $signUpPassword_confirm.addClass('glyphicon-ok');
            $signUpPasswordDiv2.removeClass('has-error');
            $signUpPasswordDiv2.addClass('has-success');

            $signUpPasswordDiv2.popover('hide');
        }else {
            $signUpPassword_confirm.addClass('glyphicon-remove');
            $signUpPassword_confirm.removeClass('glyphicon-ok');
            $signUpPasswordDiv2.addClass('has-error');
            $signUpPasswordDiv2.removeClass('has-success');
            $signUpPasswordDiv2.attr('data-content','两次密码输入不一致');
            $signUpPasswordDiv2.popover('show');
        }
    }

    function useAjax(strategy){
        $.ajax({
            url:"controlServlet.do",
            data:{type:'check',tempEmail:signUpEmailValue},     //传输键值对
            aysnc:true,
            cache:false,
            type:'POST',
            dataType:'json',
            success:strategy,
            error:function () {
                alert('服务器无响应，错误信息为'+ arguments[1]);
            }
        });
    }

    var $signInLoader = $('.loader:eq(0)');
    var $signUpLoader = $('.loader:eq(1)');

    var $modal = $('.modal');
    var $modelBody = $('.modal-body');
    var $moddelTitle = $('.modal-title');
    var $loader = $('.loader-inner');

    //设置提交策略
    function signStrategy(result) {

        var code = result.code;

        $modal.modal('show');
        switch (code) {
            case 1000:
                $moddelTitle.html('登录成功！');
                $loader.html("转至个人中心...");
                self.location = 'user-center.html';
                break;
            case 1001:
                $moddelTitle.html('登录失败！');
                $loader.html('用户名或密码错误。');
                $signInSubmit.css('display','block');
                $signInLoader.css('display','none');
                break;
            case 1010:
                $moddelTitle.html('注册成功！');
                $loader.html('转至个人中心...');
                self.location = 'user-center.html';
                break;
            case 1011:
                $moddelTitle.html('注册失败！');
                $loader.html('请刷新页面后重试。');
                $signUpSubmit.css('display','block');
                $signUpLoader.css('display','none');
                break;
            default:
                alert('success!' + result + "  code=" + code );
        }




    }

    $signInSubmit.click(signInClickCheck);
    $signUpSubmit.click(signUpClickCheck);


    function signInClickCheck() {

        if(signInEmailValue === undefined){
            $reminder.removeClass('ok');
            $signInEmailDiv.attr('data-content','邮箱地址不能为空。');
            $signInEmailDiv.popover('show');
        }

        if(signInPasswordValue === undefined){
            $reminder1.removeClass('ok');
            $signInPasswordDiv.attr('data-content','密码不能为空。');
            $signInPasswordDiv.popover('show');
        }

        if($reminder.hasClass('ok')&& $reminder1.hasClass('ok')) {

            $signInSubmit.css('display','none');
            $signInLoader.css('display','block');

            //
            // $modal.on('shown.bs.modal',function () {
                $.ajax({
                    url:"controlServlet.do",
                    data:{type:'signIn',signInEmail:signInEmailValue, signInPassword:signInPasswordValue}, //传输键值对
                    aysnc:true,
                    cache:false,
                    type:'POST',
                    dataType:'json',
                    success:signStrategy,
                    error:function () {
                        alert('服务器无响应，错误信息为'+ arguments[1]);
                    }
                });
            //
            // });
        }
    }
    //
    function signUpClickCheck() {
        if(signUpEmailValue === undefined){
            $reminder2.removeClass('ok');

            $signUpEmailDiv.popover('show');
            $signUpEmailDiv.attr('data-content','邮箱地址不能为空！');
        }

        if(signUpPasswordValue === undefined){
            $reminder3.removeClass('ok');

            $signUpPasswordDiv.popover('show');
            $signUpPasswordDiv.attr('data-content','密码不能为空！');
        }

        if(signUpPasswordConfirmValue === undefined){
            $reminder4.removeClass('ok');

            $signUpPasswordDiv2.popover('show');
            $signUpPasswordDiv2.attr('data-content','请再一次确认密码。');
        }

        if($signUpPassword.hasClass('glyphicon-ok') && $signUpPassword_confirm.hasClass('glyphicon-ok') && $signUpEmail.hasClass('glyphicon-ok')){

            $signUpSubmit.css('display','none');
            $signUpLoader.css('display','block');

            $.ajax({
                url:"controlServlet.do",
                data:{type:'signUp',signUpEmail:signUpEmailValue, signUpPassword:signUpPasswordValue}, //传输键值对
                aysnc:true,
                cache:false,
                type:'POST',
                dataType:'json',
                success:signStrategy,
                error:function () {
                    alert('服务器无响应，错误信息为'+ arguments[1]);
                }
            });
        }

    }

});


$(document).keyup(function(event){

    if(event.which ===13){
        if(flag){
            $signInSubmit.trigger('click');
        }else{
            $signUpSubmit.trigger('click');
        }
    }
});