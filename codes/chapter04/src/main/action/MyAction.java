package main.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MayerFang
 * @file MyAction
 * @Description
 * @date 2018/9/23
 */
public class MyAction extends ActionSupport {

    private int num1;
    private int num2;

    public String execute() throws Exception {
        if (getSum() > 0) {
            return "positive";
        } else {
            return "negative";
        }
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getSum() {
        return num1 + num2;
    }

}
