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
     * �û��������¼
     * userList
     */
    static public Map<String, String> userList = new HashMap<String, String>();

    /**
     * ��̬�����
     * userList����key��value
     */
    static {
        userList.put("admin", "123456");
        userList.put("test", "123456");
    }

    /**
     * ��Ȩ
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();//��ȡ����userName
        
        List<String> permissionList = new ArrayList<String>();//���鼯��permissionList
        
        permissionList.add("user:query");
        if (userName.equals("admin")) {//���userNameΪadmin�������Ȩ�������
            permissionList.add("user:insert");
            permissionList.add("user:update");
            permissionList.add("user:delete");
        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();//Ȩ����Ϣ
        
        info.addStringPermissions(permissionList);
        
        if (userName.equals("admin")) {
            info.addRole("admin");//Ȩ�ް�admin
        }
        
        return info;
    }

    /**
     * ��֤
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();//��ȡ����userName
        
        if ("".equals(userName)) {//userNameΪ�յ����
            return null;
        }
        if (!userList.containsKey(userName)) {//userName���������ݼ��ϵ����
            return null;
        }
        
        //����˵���������ݼ��ϵ����
        String passWord = userList.get(userName);//����userName��ȡ����
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, passWord, this.getName());//Ȩ����Ϣ
        return info;
    }
}