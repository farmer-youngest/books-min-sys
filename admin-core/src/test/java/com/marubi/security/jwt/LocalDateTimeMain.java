package com.marubi.security.jwt;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HtmlUtil;
import org.apache.commons.codec.Charsets;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 拿icon
 */
public class LocalDateTimeMain {
    public static void main(String[] args) {
        String url = "C:\\Users\\tangmingze.MAIL39\\Desktop\\1.html";
        String c = "<ul class=\"site-doc-icon\">\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-heart-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe68f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-heart-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-heart\"></i>\n" +
                "        <div class=\"doc-icon-name\">空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe68c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-heart</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-light\"></i>\n" +
                "        <div class=\"doc-icon-name\">亮度/晴</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe748;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-light</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-time\"></i>\n" +
                "        <div class=\"doc-icon-name\">时间/历史</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe68d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-time</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-bluetooth\"></i>\n" +
                "        <div class=\"doc-icon-name\">蓝牙</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe689;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-bluetooth</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-at\"></i>\n" +
                "        <div class=\"doc-icon-name\">@艾特</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe687;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-at</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-mute\"></i>\n" +
                "        <div class=\"doc-icon-name\">静音</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe685;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-mute</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-mike\"></i>\n" +
                "        <div class=\"doc-icon-name\">录音/麦克风</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6dc;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-mike</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-key\"></i>\n" +
                "        <div class=\"doc-icon-name\">密钥/钥匙</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe683;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-key</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-gift\"></i>\n" +
                "        <div class=\"doc-icon-name\">礼物/活动</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe627;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-gift</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-email\"></i>\n" +
                "        <div class=\"doc-icon-name\">邮箱</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe618;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-email</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-rss\"></i>\n" +
                "        <div class=\"doc-icon-name\">RSS</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe808;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-rss</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-wifi\"></i>\n" +
                "        <div class=\"doc-icon-name\">WiFi</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe7e0;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-wifi</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-logout\"></i>\n" +
                "        <div class=\"doc-icon-name\">退出/注销</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe682;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-logout</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-android\"></i>\n" +
                "        <div class=\"doc-icon-name\">Android 安卓</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe684;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-android</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-ios\"></i>\n" +
                "        <div class=\"doc-icon-name\">Apple IOS 苹果</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe680;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-ios</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-windows\"></i>\n" +
                "        <div class=\"doc-icon-name\">Windows</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-windows</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-transfer\"></i>\n" +
                "        <div class=\"doc-icon-name\">穿梭框</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe691;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-transfer</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-service\"></i>\n" +
                "        <div class=\"doc-icon-name\">客服</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe626;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-service</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-subtraction\"></i>\n" +
                "        <div class=\"doc-icon-name\">减</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-subtraction</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-addition\"></i>\n" +
                "        <div class=\"doc-icon-name\">加</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe624;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-addition</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-slider\"></i>\n" +
                "        <div class=\"doc-icon-name\">滑块</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe714;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-slider</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-print\"></i>\n" +
                "        <div class=\"doc-icon-name\">打印</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-print</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-export\"></i>\n" +
                "        <div class=\"doc-icon-name\">导出</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-export</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-cols\"></i>\n" +
                "        <div class=\"doc-icon-name\">列</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe610;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-cols</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-screen-restore\"></i>\n" +
                "        <div class=\"doc-icon-name\">退出全屏</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe758;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-screen-restore</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-screen-full\"></i>\n" +
                "        <div class=\"doc-icon-name\">全屏</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe622;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-screen-full</div>\n" +
                "      </li>\n" +
                "    \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-rate-half\"></i>\n" +
                "        <div class=\"doc-icon-name\">半星</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6c9;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-rate-half</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-rate\"></i>\n" +
                "        <div class=\"doc-icon-name\">星星-空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-rate</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-rate-solid\"></i>\n" +
                "        <div class=\"doc-icon-name\">星星-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-rate-solid</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-cellphone\"></i>\n" +
                "        <div class=\"doc-icon-name\">手机</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe678;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-cellphone</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-vercode\"></i>\n" +
                "        <div class=\"doc-icon-name\">验证码</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe679;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-vercode</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-login-wechat\"></i>\n" +
                "        <div class=\"doc-icon-name\">微信</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe677;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-login-wechat</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-login-qq\"></i>\n" +
                "        <div class=\"doc-icon-name\">QQ</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe676;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-login-qq</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-login-weibo\"></i>\n" +
                "        <div class=\"doc-icon-name\">微博</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe675;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-login-weibo</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-password\"></i>\n" +
                "        <div class=\"doc-icon-name\">密码</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe673;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-password</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-username\"></i>\n" +
                "        <div class=\"doc-icon-name\">用户名</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-username</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-refresh-3\"></i>\n" +
                "        <div class=\"doc-icon-name\">刷新-粗</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe9aa;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-refresh-3</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-auz\"></i>\n" +
                "        <div class=\"doc-icon-name\">授权</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe672;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-auz</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-spread-left\"></i>\n" +
                "        <div class=\"doc-icon-name\">左向右伸缩菜单</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-spread-left</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-shrink-right\"></i>\n" +
                "        <div class=\"doc-icon-name\">右向左伸缩菜单</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe668;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-shrink-right</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-snowflake\"></i>\n" +
                "        <div class=\"doc-icon-name\">雪花</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6b1;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-snowflake</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-tips\"></i>\n" +
                "        <div class=\"doc-icon-name\">提示说明</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe702;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-tips</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-note\"></i>\n" +
                "        <div class=\"doc-icon-name\">便签</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-note</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-home\"></i>\n" +
                "        <div class=\"doc-icon-name\">主页</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe68e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-home</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-senior\"></i>\n" +
                "        <div class=\"doc-icon-name\">高级</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe674;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-senior</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-refresh\"></i>\n" +
                "        <div class=\"doc-icon-name\">刷新</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe669;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-refresh</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-refresh-1\"></i>\n" +
                "        <div class=\"doc-icon-name\">刷新</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe666;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-refresh-1</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-flag\"></i>\n" +
                "        <div class=\"doc-icon-name\">旗帜</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-flag</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-theme\"></i>\n" +
                "        <div class=\"doc-icon-name\">主题</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe66a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-theme</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-notice\"></i>\n" +
                "        <div class=\"doc-icon-name\">消息-通知</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe667;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-notice</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-website\"></i>\n" +
                "        <div class=\"doc-icon-name\">网站</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe7ae;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-website</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-console\"></i>\n" +
                "        <div class=\"doc-icon-name\">控制台</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe665;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-console</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-face-surprised\"></i>\n" +
                "        <div class=\"doc-icon-name\">表情-惊讶</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe664;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-face-surprised</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-set\"></i>\n" +
                "        <div class=\"doc-icon-name\">设置-空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe716;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-set</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-template-1\"></i>\n" +
                "        <div class=\"doc-icon-name\">模板</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe656;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-template-1</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-app\"></i>\n" +
                "        <div class=\"doc-icon-name\">应用</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe653;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-app</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-template\"></i>\n" +
                "        <div class=\"doc-icon-name\">模板</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe663;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-template</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-praise\"></i>\n" +
                "        <div class=\"doc-icon-name\">赞</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6c6;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-praise</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-tread\"></i>\n" +
                "        <div class=\"doc-icon-name\">踩</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6c5;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-tread</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-male\"></i>\n" +
                "        <div class=\"doc-icon-name\">男</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe662;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-male</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-female\"></i>\n" +
                "        <div class=\"doc-icon-name\">女</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe661;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-female</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-camera\"></i>\n" +
                "        <div class=\"doc-icon-name\">相机-空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe660;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-camera</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-camera-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">相机-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-camera-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-more\"></i>\n" +
                "        <div class=\"doc-icon-name\">菜单-水平</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-more</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-more-vertical\"></i>\n" +
                "        <div class=\"doc-icon-name\">菜单-垂直</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe671;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-more-vertical</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-rmb\"></i>\n" +
                "        <div class=\"doc-icon-name\">金额-人民币</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-rmb</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-dollar\"></i>\n" +
                "        <div class=\"doc-icon-name\">金额-美元</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe659;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-dollar</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-diamond\"></i>\n" +
                "        <div class=\"doc-icon-name\">钻石-等级</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe735;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-diamond</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fire\"></i>\n" +
                "        <div class=\"doc-icon-name\">火</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe756;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fire</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-return\"></i>\n" +
                "        <div class=\"doc-icon-name\">返回</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-return</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-location\"></i>\n" +
                "        <div class=\"doc-icon-name\">位置-地图</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe715;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-location</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-read\"></i>\n" +
                "        <div class=\"doc-icon-name\">办公-阅读</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe705;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-read</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-survey\"></i>\n" +
                "        <div class=\"doc-icon-name\">调查</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6b2;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-survey</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-face-smile\"></i>\n" +
                "        <div class=\"doc-icon-name\">表情-微笑</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6af;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-face-smile</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-face-cry\"></i>\n" +
                "        <div class=\"doc-icon-name\">表情-哭泣</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe69c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-face-cry</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-cart-simple\"></i>\n" +
                "        <div class=\"doc-icon-name\">购物车</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe698;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-cart-simple</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-cart\"></i>\n" +
                "        <div class=\"doc-icon-name\">购物车</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe657;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-cart</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-next\"></i>\n" +
                "        <div class=\"doc-icon-name\">下一页</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-next</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-prev\"></i>\n" +
                "        <div class=\"doc-icon-name\">上一页</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe65a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-prev</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-upload-drag\"></i>\n" +
                "        <div class=\"doc-icon-name\">上传-空心-拖拽</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe681;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-upload-drag</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-upload\"></i>\n" +
                "        <div class=\"doc-icon-name\">上传-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe67c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-upload</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-download-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">下载-圆圈</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe601;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-download-circle</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-component\"></i>\n" +
                "        <div class=\"doc-icon-name\">组件</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe857;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-component</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-file-b\"></i>\n" +
                "        <div class=\"doc-icon-name\">文件-粗</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe655;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-file-b</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-user\"></i>\n" +
                "        <div class=\"doc-icon-name\">用户</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe770;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-user</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-find-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">发现-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe670;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-find-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop\"></i>\n" +
                "        <div class=\"doc-icon-name\">loading</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-loading</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-loading-1 layui-anim layui-anim-rotate layui-anim-loop\"></i>\n" +
                "        <div class=\"doc-icon-name\">loading</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-loading-1</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-add-1\"></i>\n" +
                "        <div class=\"doc-icon-name\">添加</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe654;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-add-1</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-play\"></i>\n" +
                "        <div class=\"doc-icon-name\">播放</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe652;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-play</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-pause\"></i>\n" +
                "        <div class=\"doc-icon-name\">暂停</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe651;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-pause</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-headset\"></i>\n" +
                "        <div class=\"doc-icon-name\">音频-耳机</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6fc;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-headset</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-video\"></i>\n" +
                "        <div class=\"doc-icon-name\">视频</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe6ed;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-video</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-voice\"></i>\n" +
                "        <div class=\"doc-icon-name\">语音-声音</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe688;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-voice</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-speaker\"></i>\n" +
                "        <div class=\"doc-icon-name\">消息-通知-喇叭</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe645;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-speaker</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-del\"></i>\n" +
                "        <div class=\"doc-icon-name\">删除线</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-del</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-code\"></i>\n" +
                "        <div class=\"doc-icon-name\">代码</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-code</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-html\"></i>\n" +
                "        <div class=\"doc-icon-name\">HTML</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-html</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-strong\"></i>\n" +
                "        <div class=\"doc-icon-name\">字体加粗</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-strong</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-unlink\"></i>\n" +
                "        <div class=\"doc-icon-name\">删除链接</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-unlink</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-picture\"></i>\n" +
                "        <div class=\"doc-icon-name\">图片</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-picture</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-link\"></i>\n" +
                "        <div class=\"doc-icon-name\">链接</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe64c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-link</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-face-smile-b\"></i>\n" +
                "        <div class=\"doc-icon-name\">表情-笑-粗</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe650;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-face-smile-b</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-align-left\"></i>\n" +
                "        <div class=\"doc-icon-name\">左对齐</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe649;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-align-left</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-align-right\"></i>\n" +
                "        <div class=\"doc-icon-name\">右对齐</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe648;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-align-right</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-align-center\"></i>\n" +
                "        <div class=\"doc-icon-name\">居中对齐</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe647;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-align-center</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-u\"></i>\n" +
                "        <div class=\"doc-icon-name\">字体-下划线</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe646;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-u</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-i\"></i>\n" +
                "        <div class=\"doc-icon-name\">字体-斜体</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe644;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-i</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-tabs\"></i>\n" +
                "        <div class=\"doc-icon-name\">Tabs 选项卡</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-tabs</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-radio\"></i>\n" +
                "        <div class=\"doc-icon-name\">单选框-选中</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe643;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-radio</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">单选框-候选</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-circle</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-edit\"></i>\n" +
                "        <div class=\"doc-icon-name\">编辑</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe642;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-edit</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-share\"></i>\n" +
                "        <div class=\"doc-icon-name\">分享</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe641;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-share</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-delete\"></i>\n" +
                "        <div class=\"doc-icon-name\">删除</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe640;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-delete</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-form\"></i>\n" +
                "        <div class=\"doc-icon-name\">表单</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-form</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-cellphone-fine\"></i>\n" +
                "        <div class=\"doc-icon-name\">手机-细体</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-cellphone-fine</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-dialogue\"></i>\n" +
                "        <div class=\"doc-icon-name\">聊天 对话 沟通</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe63a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-dialogue</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-fonts-clear\"></i>\n" +
                "        <div class=\"doc-icon-name\">文字格式化</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe639;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-fonts-clear</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-layer\"></i>\n" +
                "        <div class=\"doc-icon-name\">窗口</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe638;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-layer</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-date\"></i>\n" +
                "        <div class=\"doc-icon-name\">日期</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe637;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-date</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-water\"></i>\n" +
                "        <div class=\"doc-icon-name\">水 下雨</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe636;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-water</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-code-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">代码-圆圈</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe635;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-code-circle</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-carousel\"></i>\n" +
                "        <div class=\"doc-icon-name\">轮播组图</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe634;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-carousel</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-prev-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">翻页</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe633;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-prev-circle</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-layouts\"></i>\n" +
                "        <div class=\"doc-icon-name\">布局</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe632;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-layouts</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-util\"></i>\n" +
                "        <div class=\"doc-icon-name\">工具</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe631;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-util</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-templeate-1\"></i>\n" +
                "        <div class=\"doc-icon-name\">选择模板</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe630;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-templeate-1</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-upload-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">上传-圆圈</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-upload-circle</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-tree\"></i>\n" +
                "        <div class=\"doc-icon-name\">树</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-tree</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-table\"></i>\n" +
                "        <div class=\"doc-icon-name\">表格</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-table</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-chart\"></i>\n" +
                "        <div class=\"doc-icon-name\">图表</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe62c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-chart</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-chart-screen\"></i>\n" +
                "        <div class=\"doc-icon-name\">图标 报表 屏幕</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe629;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-chart-screen</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-engine\"></i>\n" +
                "        <div class=\"doc-icon-name\">引擎</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe628;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-engine</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-triangle-d\"></i>\n" +
                "        <div class=\"doc-icon-name\">下三角</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe625;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-triangle-d</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-triangle-r\"></i>\n" +
                "        <div class=\"doc-icon-name\">右三角</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe623;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-triangle-r</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-file\"></i>\n" +
                "        <div class=\"doc-icon-name\">文件</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe621;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-file</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-set-sm\"></i>\n" +
                "        <div class=\"doc-icon-name\">设置-小型</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe620;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-set-sm</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-reduce-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">减少-圆圈</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe616;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-reduce-circle</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-add-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">添加-圆圈</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe61f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-add-circle</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-404\"></i>\n" +
                "        <div class=\"doc-icon-name\">404</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe61c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-404</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-about\"></i>\n" +
                "        <div class=\"doc-icon-name\">关于</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60b;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-about</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-up\"></i>\n" +
                "        <div class=\"doc-icon-name\">箭头 向上</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe619;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-up</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-down\"></i>\n" +
                "        <div class=\"doc-icon-name\">箭头 向下</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe61a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-down</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-left\"></i>\n" +
                "        <div class=\"doc-icon-name\">箭头 向左</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe603;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-left</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-right\"></i>\n" +
                "        <div class=\"doc-icon-name\">箭头 向右</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe602;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-right</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-circle-dot\"></i>\n" +
                "        <div class=\"doc-icon-name\">圆点</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe617;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-circle-dot</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-search\"></i>\n" +
                "        <div class=\"doc-icon-name\">搜索</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe615;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-search</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-set-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">设置-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe614;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-set-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-group\"></i>\n" +
                "        <div class=\"doc-icon-name\">群组</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe613;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-group</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-friends\"></i>\n" +
                "        <div class=\"doc-icon-name\">好友</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe612;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-friends</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-reply-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">回复 评论 实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe611;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-reply-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-menu-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">菜单 隐身 实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60f;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-menu-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-log\"></i>\n" +
                "        <div class=\"doc-icon-name\">记录</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60e;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-log</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-picture-fine\"></i>\n" +
                "        <div class=\"doc-icon-name\">图片-细体</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60d;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-picture-fine</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-face-smile-fine\"></i>\n" +
                "        <div class=\"doc-icon-name\">表情-笑-细体</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60c;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-face-smile-fine</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-list\"></i>\n" +
                "        <div class=\"doc-icon-name\">列表</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe60a;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-list</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-release\"></i>\n" +
                "        <div class=\"doc-icon-name\">发布 纸飞机</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe609;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-release</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-ok\"></i>\n" +
                "        <div class=\"doc-icon-name\">对 OK</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe605;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-ok</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-help\"></i>\n" +
                "        <div class=\"doc-icon-name\">帮助</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe607;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-help</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-chat\"></i>\n" +
                "        <div class=\"doc-icon-name\">客服</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe606;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-chat</div>\n" +
                "      </li>\n" +
                "      \n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-top\"></i>\n" +
                "        <div class=\"doc-icon-name\">top 置顶</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe604;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-top</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-star\"></i>\n" +
                "        <div class=\"doc-icon-name\">收藏-空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe600;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-star</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-star-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">收藏-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe658;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-star-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-close-fill\"></i>\n" +
                "        <div class=\"doc-icon-name\">关闭-实心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#x1007;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-close-fill</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-close\"></i>\n" +
                "        <div class=\"doc-icon-name\">关闭-空心</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#x1006;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-close</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-ok-circle\"></i>\n" +
                "        <div class=\"doc-icon-name\">正确</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#x1005;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-ok-circle</div>\n" +
                "      </li>\n" +
                "      <li>\n" +
                "        <i class=\"layui-icon layui-icon-add-circle-fine\"></i>\n" +
                "        <div class=\"doc-icon-name\">添加-圆圈-细体</div>\n" +
                "        <div class=\"doc-icon-code\">&amp;#xe608;</div>\n" +
                "        <div class=\"doc-icon-fontclass\">layui-icon-add-circle-fine</div>\n" +
                "      </li>\n" +
                "    </ul>";

        System.out.println(HtmlUtil.unwrapHtmlTag(c, "div"));
        ArrayList<String> div = ReUtil.findAll("#x\\w{4};", HtmlUtil.unwrapHtmlTag(c, "div"), 0, new ArrayList<String>());
        FileUtil.writeLines(div,"D:\\test-project\\xadmintest\\admin-core\\src\\main\\resources\\tempfile\\icon.txt", Charsets.UTF_8);
    }
}
