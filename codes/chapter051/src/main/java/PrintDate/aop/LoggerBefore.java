package PrintDate.aop;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;


/**
 * @author MayerFang
 * @file LoggerBefore
 * @Description
 * @date 2018/9/27
 */
public class LoggerBefore implements MethodBeforeAdvice {

    Logger logger= Logger.getLogger(LoggerBefore.class);
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("before method is running...");
        logger.error("执行方法前增强"+o+"中的方法名："+method);
    }
}
