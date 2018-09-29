package PrintDate;

import PrintDate.model.User;
import PrintDate.service.IUserBiz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MayerFang
 * @file TestPrintDate
 * @Description 通过AOP实现前置日志打印，结合log4j
 * @date 2018/9/27
 */
public class TestPrintDate {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-printdate-api.xml");
        IUserBiz ubiz=(IUserBiz)ctx.getBean("userBiz");
        User user=new User();
        user.setId(1);
        user.setName("name1");
        ubiz.getUser(user);
    }
}
