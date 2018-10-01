package com.spring;

import com.dao.UserInfoDao;
import com.model.UserInfo;

/**
 * @author MayerFang
 * @file helloWorld
 * @Description
 * @date 2018/10/1
 */
public class helloWorldService {

    private UserInfoDao userInfoDao;

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public UserInfo getUserInfoByLoginName(String loginName,String pwd){
        return userInfoDao.getUserInfoByLoginName(loginName,pwd);
    }
}
