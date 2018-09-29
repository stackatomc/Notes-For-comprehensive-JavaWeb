package Test511;

/**
 * @author MayerFang
 * @file HelloWorldSpring
 * @Description
 * @date 2018/9/27
 */
public class HelloWorldSpring {

    private String context;
    public HelloWorldSpring(){
        System.out.println("HelloWorldSpring init");
    }
    public String sayHello(){
        System.out.println("It is HelloWorldSpring");
        return "Hello World Spring";
    }
}
