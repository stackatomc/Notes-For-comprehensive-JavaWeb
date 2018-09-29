package PrintDate.service;

import PrintDate.dao.UserDao;
import PrintDate.model.User;

/**
 * @author MayerFang
 * @file IUserBiz
 * @Description
 * @date 2018/9/27
 */
public interface IUserBiz {
    UserDao userDao=null;
    void getUser(User user);
}
