package com.springboot.simple.cache;

import com.springboot.simple.utils.QueryUserNameUtils;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义退出登陆后，删除redis记录
 */
public class CustomerLogoutFilter extends LogoutFilter {

    private String keyPrefix = "shiro_redis_session:";

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String key = keyPrefix + QueryUserNameUtils.getUserName();
        System.out.println("del redis data, key is:" + key);
        JedisClientSingle.getJedis().del(key);
        return super.preHandle(request, response);
    }
}