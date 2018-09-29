package Test511;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MayerFang
 * @file SpringMain
 * @Description
 * @date 2018/9/27
 */
public class SpringMain {


    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-*.xml");

        SayHello sayHello=(SayHello)ctx.getBean("SayHello");
        sayHello.sayHello();
    }

}
