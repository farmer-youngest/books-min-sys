//init
layui.use(['form', 'table', 'jquery'], function () {
    var form = layui.form, table = layui.table
        , $ = layui.jquery
    ;

    let param = {
        name: '1213',
        id: 1123,
        version: 123
    }
    table.render({
        elem: '#demo',
        url: '/test/testPage',
        where: param,
        method: 'post',
        contentType: 'application/json',
        cols: [[
            {
                field: 'id', title: 'ID', width: 100, templet: function (d) {
                    console.log(d)
                    return d.id;
                }
            },
            {field: 'name', title: '名字', width: 100},
            {fixed: 'right', title: '版本号', field: 'version', width: 150, align: 'center'},
            {title: 'time', field: 'createTime', width: 150, align: 'center'}
            , {fixed: 'right', width: 150, align: 'center', toolbar: '#barDemo'}
        ]]
        , parseData: function (res) { //res 即为原始返回的数据
            console.log(res);
            return {
                "code": res.code, //解析接口状态
                "msg": res.info, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.records //解析数据列表
            };
        }
        , request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            , statusCode: 200 //规定成功的状态码，默认：0
            , msgName: 'info' //规定状态信息的字段名称，默认：msg
            , countName: 'total' //规定数据总数的字段名称，默认：count
            , dataName: 'data' //规定数据列表的字段名称，默认：data
        }

    });
    //监听提交
    form.on('submit(formDemo)', function (data) {
        console.log(data);
        layer.msg(JSON.stringify(data.field));
        // return false; 注释掉 走 form的action  不注释掉 会走action 而且会跳转 显示返回值

        //推荐 ajax请求 不走action
        $.ajax({
            url: '/test/demo1',
            // type:'post',
            method: 'post',
            data: data.field,
            dataType: "json",
            success: function (data) {
                console.log(data)
                if (data) {
                    layer.alert("提交成功！")
                } else {
                    layer.alert("提交失败！")
                }
            },
            error: function (e) {
                layer.alert("提交失败！")
            },

        });
        return false;
    });
//工具条事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'detail') { //查看
            //do somehing
        } else if (layEvent === 'del') { //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if (layEvent === 'edit') { //编辑
            //do something

            //同步更新缓存对应的值
            obj.update({
                username: '123'
                , title: 'xxx'
            });
        } else if (layEvent === 'LAYTABLE_TIPS') {
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });

    /* form.on('submit(formDemo)', function(data){
         console.log(data);
         console.log(JSON.stringify(data.field))
         return false;
     });*/
});