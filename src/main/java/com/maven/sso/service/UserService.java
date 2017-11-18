package com.maven.sso.service;

import com.maven.sso.model.UserInfo;

public interface UserService {

	public UserInfo queryUserInfo(String username,String password);
}
