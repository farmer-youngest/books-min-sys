数据库更改：
1.jk_backend_admin
    1)last_login_time、updated_at、created_at改为timestamp类型
    2)last_login_ip改为字符串varchar(20)
    3)last_login_ip默认值改为'0.0.0.0'（新增用户并不更新登录IP）
    4)updated_at根据当前时间戳更新
2.jk_auth_rule
    1）添加对应父级规则ID—— parent_id  int(11)
3.

4.jk_shop_goods
    1)updated_at改为bigint类型