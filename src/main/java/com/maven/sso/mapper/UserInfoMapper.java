package com.maven.sso.mapper;

import java.util.List;
import java.util.Map;

import com.maven.sso.model.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    List<UserInfo> selectUserInfoByNameAndPwd(Map<String,Object> paramMap);
}