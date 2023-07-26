package sherry.taobao.gmall.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取ip地址
 */
public class IpUtil {
        //当Web服务器收到一个HTTP请求时，它会创建一个HttpServletRequest对象，
        // 其中包含了与请求相关的所有信息，例如请求方法（GET、POST等）、请求参数、请求头部、请求URI等等。
        // 这些信息可以用于从客户端接收数据、处理请求、获取请求参数和头部信息、跟踪会话等操作。
    public static String getIpAddress(HttpServletRequest request) {
        //声明一个String 类为ip地址
        String ipAddress = null;
        //获取Http信息头字段中用户的真实ip
        //如果用户使用了代理服务器访问后端服务器,后端服务器会记录真实的ip
        //该字段的值通常是一个逗号分隔的IP地址列表，其中最左边的IP地址是客户端的真实IP地址。如果请求经过了多个代理服务器，那么每个代理服务器都会在"X-Forwarded-For"字段中添加它的IP地址，从而形成一个逗号分隔的列表，记录请求的路径。
        //
        //例如，如果一个HTTP请求经过两个代理服务器，客户端的真实IP地址是192.168.1.100，那么"X-Forwarded-For"字段的值可能会是：192.168.1.100, proxy1_ip, proxy2_ip。
        //后端服务器可以通过读取"X-Forwarded-For"字段，提取最左边的IP地址
        try {
            //在用常用的代理ip头信息来遍历获取用户真实id
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
                        //表示当前主机的网络地址信息，即主机名和IP地址。
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            //  已经获取了真实ip地址的情况下
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    //获取ip从第一个字母到,
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
    // 网关中获取Ip地址
    public static String getGatwayIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress().getAddress().getHostAddress();
        }
        return ip;
    }
}