
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

    var $signInSubmit = $('button[type=button]:eq(0)');
    var $signUpSubmit = $('button[type=button]:eq(1)');

    var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

    var signUpEmailValue ;
    var signUpPasswordValue;
    var signInEmailValue;
    var signInPasswordValue;

    //初始化
    $reminder.hide();
    $reminder1.hide();
    $reminder2.hide();
    $reminder3.hide();
    $reminder4.hide();
    $signInSubmit.attr('disabled',true);
    $signUpSubmit.attr('disabled',true);

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
            $reminder.hide();
        }else {
            $reminder.removeClass('ok');
            $reminder.html('请输入正确的邮箱地址。');
            $reminder.fadeTo('slow',1);
        }
        signInReliable();
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
           $reminder1.hide();
       }else {
           $reminder1.removeClass('ok');
           $reminder1.html('请输入6到12位字符作为密码。');
           $reminder1.fadeTo('slow',1);
       }
       signInReliable();
    });

    //对注册邮箱地址进行本地校验+服务器校验
    function signUpStrategy(result) {
        console.log(result);
        if(result.success){
            $reminder2.hide();
            $signUpEmail.removeClass('glyphicon-remove');
            $signUpEmail.addClass('glyphicon-ok');
            $signUpEmailDiv.removeClass('has-error');
            $signUpEmailDiv.addClass('has-success');
        }else{
            $signUpEmail.removeClass('glyphicon-ok');
            $signUpEmail.addClass('glyphicon-remove');
            $signUpEmailDiv.removeClass('has-success');
            $signUpEmailDiv.addClass('has-error');
            $reminder2.html('该邮箱已被占用。');
            $reminder2.fadeTo('slow',1);
        }
    }

    $('#sign-up-email').change(function () {
        signUpEmailValue = $(this).val();
        if(myreg.test(signUpEmailValue)){
            $signUpEmail.removeClass('glyphicon-remove');
            $signUpEmail.addClass('glyphicon-ok');
            $signUpEmailDiv.removeClass('has-error');
            $signUpEmailDiv.addClass('has-success');
            $reminder2.hide();
            useAjax(signUpStrategy);
        }else{
            $signUpEmail.removeClass('glyphicon-ok');
            $signUpEmail.addClass('glyphicon-remove');
            $signUpEmailDiv.removeClass('has-success');
            $signUpEmailDiv.addClass('has-error');
            $reminder2.html('请输入正确的邮箱地址。');
            $reminder2.fadeTo('slow',1);
        }
        signUpReliable();
    });
    //对注册密码进行本地校验
    $('#sign-up-password').change(function () {
        signUpPasswordValue = $(this).val();
        if(passwordCheck(signUpPasswordValue)){
            $signUpPassword.removeClass('glyphicon-remove');
            $signUpPassword.addClass('glyphicon-ok');
            $signUpPasswordDiv.removeClass('has-error');
            $signUpPasswordDiv.addClass('has-success');
            $reminder3.hide();
            confirm($(this).val(),$('#sign-up-password-confirm').val());
        }else {
            $signUpPassword.addClass('glyphicon-remove');
            $signUpPassword.removeClass('glyphicon-ok');
            $signUpPasswordDiv.addClass('has-error');
            $signUpPasswordDiv.removeClass('has-success');
            $reminder3.html('密码长度不能少于6位');
            $reminder3.fadeTo('slow',1);
            confirm($(this).val(),$('#sign-up-password-confirm').val());
        }
    });

    //对重复密码进行本地校验
    $('#sign-up-password-confirm').change(function () {
        confirm($(this).val(),$('#sign-up-password').val());
    });

    function confirm (password1, password2) {
        if(password1 === password2){
            $signUpPassword_confirm.removeClass('glyphicon-remove');
            $signUpPassword_confirm.addClass('glyphicon-ok');
            $signUpPasswordDiv2.removeClass('has-error');
            $signUpPasswordDiv2.addClass('has-success');
            $reminder4.hide();
        }else {
            $signUpPassword_confirm.addClass('glyphicon-remove');
            $signUpPassword_confirm.removeClass('glyphicon-ok');
            $signUpPasswordDiv2.addClass('has-error');
            $signUpPasswordDiv2.removeClass('has-success');
            $reminder4.html('两次密码输入不一致');
            $reminder4.fadeTo('slow',1);
        }
        signUpReliable();
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

    //设置注册页面的按钮激活状态
    function signUpReliable() {
        if($signUpPassword.hasClass('glyphicon-ok') && $signUpPassword_confirm.hasClass('glyphicon-ok') && $signUpEmail.hasClass('glyphicon-ok')){
            $signUpSubmit.attr('disabled',false);
        }else {
            $signUpSubmit.attr('disabled',true);
        }
    }

    //设置登录页面的按钮激活状态
    function signInReliable() {

        if($reminder.hasClass('ok')&& $reminder1.hasClass('ok')){
            $signInSubmit.attr('disabled',false);
        }else {
            $signInSubmit.attr('disabled',true);
        }
    }

    //设置提交策略
    function signStrategy(result) {

        var code = result.code;

        switch (code) {
            case 1000:
                alert('登录成功！页面跳转至个人中心...');
                self.location = 'user-center.html';
                break;
            case 1001:
                alert('登录失败！');
                break;
            case 1010:
                alert('注册成功！页面跳转至个人中心...');
                self.location = 'user-center.html';
                break;
            case 1011:
                alert('注册失败！');
                break;
            default:
                alert('success!' + result + "  code=" + code );
        }
    }

    $signInSubmit.click(function () {
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
    });

    $signUpSubmit.click(function () {
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
    });

});