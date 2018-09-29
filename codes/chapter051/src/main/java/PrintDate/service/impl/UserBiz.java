package PrintDate.service.impl;

import PrintDate.dao.UserDao;
import PrintDate.model.User;
import PrintDate.service.IUserBiz;

/**
 * @author MayerFang
 * @file UserBiz
 * @Description
 * @date 2018/9/27
 */
public class UserBiz implements IUserBiz{

    public UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser(User user){
        userDao.getUser(user);
    }


}
