<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crawler</title>

    <link href="../static/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/common.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">管理平台</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a>用户</a></li>
                <li><a>登录</a></li>
                <li><a>推出</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search..."/>
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a class="content-right" href="/message/sms">管理</a></li>
                <#--<li><a class="content-right" href="/message/mail">邮件消息</a></li>-->
                <#--<li><a class="content-right" href="/message/app">APP通知</a></li>-->
            </ul>
        </div>
        <div class="col-md-10 col-md-offset-2 main">
            <h1 class="page-header"></h1>
            <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" name="main" id="main"  src="#"></iframe>
            </div>
        </div>
    </div>
</div>
<footer id="footer">

</footer>

<div class="modal fade" id="divModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<!-- Javascript Libraries -->
<script src="../static/js/jquery-2.1.1.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script>

</script>
</body>
</html>