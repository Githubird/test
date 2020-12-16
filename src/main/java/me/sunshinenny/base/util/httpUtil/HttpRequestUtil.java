package me.sunshinenny.base.util.httpUtil;

/**
 * 获取访问者ip地址
 *
 * @author sunshinenny
 * @create 2019-10-28 12:39 下午
 */

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

/**
 * @author : JCccc
 * @CreateTime : 2018-11-23
 **/
public class HttpRequestUtil {
    /**
     * 从Request中获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    /**
     * 获取Header中携带的用户名
     *
     * @param request
     * @return
     */
    public static String getRequestUserName(HttpServletRequest request) {
        // 从请求头中获取token
        String editUserName = request.getHeader("loginUserName");
        //WebUtils.toHttp(request).getHeader("Authorization");
        // 判断是否有值
        if (editUserName != null && editUserName.length() > 0) {
            try {
                return URLDecoder.decode(editUserName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "解析失败";
            }
            // jwtParam(editUserName,"username");
        }
        return "无法获取,请让前端在Header中添加loginUserName属性";
    }
}


//————————————————
//        版权声明：本文为CSDN博主「沧浪之水0818」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/cx1006784951/article/details/84108894




