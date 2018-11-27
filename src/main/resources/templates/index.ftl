<!DOCTYPE HTML>
<html>
    <head>
        <title>登录</title>
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
                    <input type="text" name="userName" placeholder="用户名">
                </div>
                <div class="login-input-box">
                    <input type="password" name="password" placeholder="密码">
                </div>
            </form>
            <div class="login-button-box">
                <button id="login">登录</button>
            </div>
            <div class="login-box">
                <a id="registPage">注册</a>
            </div>
        </div>
    </body>
</html>