$.ajaxSetup({
    complete: function(xhr, status) {
        // 在此处处理响应数据
        var response = xhr.responseJSON || xhr.responseText;
        if (response && response.code === 'A0004') {
            // 处理特定的响应码，例如跳转到登录页面
            alert('请先登录');
            window.location.href = '/login';
        }
    }
});
