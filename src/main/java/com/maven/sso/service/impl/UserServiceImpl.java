package com.maven.sso.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.sso.mapper.UserInfoMapper;
import com.maven.sso.model.UserInfo;
import com.maven.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public UserInfo queryUserInfo(String username,String password) {
		Map<String,Object> paramMap = new HashMap<>();
		
		List<UserInfo> list = userInfoMapper.selectUserInfoByNameAndPwd(paramMap);
		if(list == null || list.size() <= 0) {
			return null;
		}
		return userInfoMapper.selectUserInfoByNameAndPwd(paramMap).get(0);
	}
	
}
