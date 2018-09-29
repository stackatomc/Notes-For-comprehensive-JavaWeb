package PrintDate.dao;

import PrintDate.model.User;

/**
 * @author MayerFang
 * @file UserDao
 * @Description
 * @date 2018/9/27
 */
public class UserDao {

    public void getUser(User user){
        System.out.println("this is"+user);
        System.out.println("id:"+user.getId());
        System.out.println("name:"+user.getName());
    }
}
