package com.javen.shirofilter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomRealm extends AuthorizingRealm {
    /**
     * 用户和密码记录
     * userList
     */
    static public Map<String, String> userList = new HashMap<String, String>();

    /**
     * 静态代码块
     * userList传入key与value
     */
    static {
        userList.put("admin", "123456");
        userList.put("test", "123456");
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();//获取主键userName
        
        List<String> permissionList = new ArrayList<String>();//数组集合permissionList
        
        permissionList.add("user:query");
        if (userName.equals("admin")) {//如果userName为admin，则可授权更多操作
            permissionList.add("user:insert");
            permissionList.add("user:update");
            permissionList.add("user:delete");
        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();//权限信息
        
        info.addStringPermissions(permissionList);
        
        if (userName.equals("admin")) {
            info.addRole("admin");//权限绑定admin
        }
        
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();//获取主键userName
        
        if ("".equals(userName)) {//userName为空的情况
            return null;
        }
        if (!userList.containsKey(userName)) {//userName不存在数据集合的情况
            return null;
        }
        
        //以下说明存在数据集合的情况
        String passWord = userList.get(userName);//根据userName获取密码
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, passWord, this.getName());//权限信息
        return info;
    }
}