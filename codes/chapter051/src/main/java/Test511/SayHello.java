package Test511;

/**
 * @author MayerFang
 * @file SayHello
 * @Description
 * @date 2018/9/27
 */
public class SayHello {

    private HelloWorldSpring helloWorldSpring;

    public HelloWorldSpring getHelloWorldSpring() {
        return helloWorldSpring;
    }

    public void setHelloWorldSpring(HelloWorldSpring helloWorldSpring) {
        this.helloWorldSpring = helloWorldSpring;
    }

    public SayHello(){
        System.out.println("SayHello init");
    }
    public void sayHello(){
        System.out.println("It is sayHello.");
        System.out.println(helloWorldSpring.sayHello());
    }
}
