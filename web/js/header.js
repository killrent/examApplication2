$('head').append('<link rel="stylesheet" href="css/header.css"/>');

$('body').append('<header></header>');

$('header').html('<nav class="navbar navbar-inverse" style="margin-bottom: 0" > <div class="container-fluid" ><div class="navbar-header"> <a class="navbar-brand" href="index.html">Enimaxe</a> </div> <div class="collapse navbar-collapse" id="header-contents"> <form class="navbar-form navbar-left"> <div class="form-group"> <input id="search-keyword" type="text" class="form-control" placeholder="搜索试题、考试及用户"> </div> </form><ul class="nav navbar-nav"> <li><a href="search.html">搜索</a></li> <li><a href="paper.html">题库</a></li> <li><a href="#">留言板</a> </li> </ul> <ul class="ul-state nav navbar-nav navbar-right"> <li><a href="loginPage.html">登录</a></li> <li><a href="loginPage.html">注册</a> </li> </ul> <ul class="ul-state nav navbar-nav nav-pills navbar-right" style="display: none" > <li><a href="#" style="padding-top: 4px; padding-bottom: 0; padding-left: 0"> <img class="img-circle" src="image/fu.jpg"/></a> </li> <li><a id="user-href" href="user-center.html">昵称</a></li> <li><a id="signOut" href="/signOutServlet.do" style="text-decoration: underline">注销</a></li> </ul> </div> </div> </nav>');
