<!DOCTYPE HTML>
<html>
    <head>
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="${cdn}/css/login.style.css">
        <script src="${cdn}/js/jquery-3.3.1.min.js"></script>
        <script src="${cdn}/js/vue.mini.js"></script>
        <script src="${cdn}/js/ajax.js"></script>
        <script src="${cdn}/js/login.js"></script>
    </head>
    <body>
        <div id="content">
            <div class="login-header">
                <img src="${cdn}/img/welcome.jpg">
            </div>
            <form>
                <div class="login-input-box">
                    <input type="text" name="registName" placeholder="用户名">
                </div>
                <div class="login-input-box">
                    <input type="password" name="rPassword" placeholder="密码">
                </div>
                <div class="login-input-box">
                    <input type="password" name="confirm" placeholder="确认密码">
                </div>
            </form>
            <div class="login-button-box">
                <button id="regist">注册</button>
            </div>
            <div class="return-box">
                <a id="return">返回登录页</a>
            </div>
        </div>
    </body>
</html>