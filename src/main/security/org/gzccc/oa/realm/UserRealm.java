package org.gzccc.oa.realm;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.gzccc.oa.bean.User;
import org.gzccc.oa.service.IUserService;

public class UserRealm extends AuthorizingRealm{
	@Inject
	private IUserService userService;
	/**
	 * 用户授权 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		User user = userService.login(username);
		if(null == user){
			throw new RuntimeException("不存在此用户");
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword()
				,this.getName());
		//这里盐值需要用字节byte
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		//这里返回后会和token里面的密码进行验证
		return info;
	}

}
