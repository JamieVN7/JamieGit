;(function(window){

    var user = {
        //事件注册器
        eventRegist: function () {
            $("#login").on("click", function () {
                user.login();
            });

            $("#regist").on("click", function () {
                user.regist();
            });

            $("#registPage").on("click", function () {
               window.location.href = "/login/regist/";
            });
        },

        //用户登录
        login: function () {
            var userName = $("input[name='userName']").val();
            var password = $("input[name='password']").val();
            if (!userName && !password){
                alert("input参数为空");
            } else {
                var data = {
                    userName: userName,
                    password: password
                };
                window.ajaxBase("/login/check/", data, function (resp) {
                    if (resp.status == 1){
                        window.location.href = "/login/checkIn/";
                    }
                });
            }
        },

        //用户注册
        regist: function () {
            var userName = $("input[name='registName']").val();
            var password = $("input[name='rPassword']").val();
            var confirm = $("input[name='confirm']").val();//确认密码

            if (!userName && !password){
                alert("注册的用户名和密码不能为空");
            }
            else if (password != confirm){
                alert("两次密码不一致");
            }
            else {
                var param = {
                    userName: userName,
                    password: password
                };
                window.ajaxBase("/login/regist-insert/", param, function (resp) {
                    if (resp.status == 1){
                        alert("注册成功");
                        //返回登录页
                        window.location.href = "/login/index/";
                    }
                });
            }
        }
    };
    
    $(document).ready(function () {
        user.eventRegist();
    });

})(window || {});