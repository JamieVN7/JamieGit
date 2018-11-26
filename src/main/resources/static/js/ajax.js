;(function (window) {
    var ajax = {
        ajaxBase: function (url, data, success_fn, error_fn) {
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                data: data || {},
                async: false,
                success: function (resp) {
                    if (resp){
                        success_fn(resp);
                    } else {
                        console.log(resp);
                    }
                },
                error: function (resp) {
                    if (error_fn){
                        error_fn(resp);
                    }else {
                        console.log(resp);
                    }
                }
            });
        }
    };

    window.ajaxBase = function (url, data, success, error) {
        return ajax.ajaxBase(url, data, success, error);
    }
})(window || {});