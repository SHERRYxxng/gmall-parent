package sherry.taobao.gmall.common.util;

//import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息类
 */
public class AuthContextHolder {

    /**
     * 该方法首先从HttpServletRequest对象的请求头中获取名为"userId"的值，表示当前登录用户的ID。<br>
     * 如果"userId"对应的值为空或不存在，则返回空字符串；否则，返回"userId"对应的值。
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        return StringUtils.isEmpty(userId) ? "" : userId;
    }

    /**
     * 该方法类似于getUserId方法，但它获取的是名为"userTempId"的值，表示当前未登录临时用户的ID。
     * @param request
     * @return
     */
    public static String getUserTempId(HttpServletRequest request) {
        String userTempId = request.getHeader("userTempId");
        return StringUtils.isEmpty(userTempId) ? "" : userTempId;
    }
}
