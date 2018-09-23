package main.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MayerFang
 * @file MyAction
 * @Description
 * @date 2018/9/23
 */
public class MyAction1 extends ActionSupport {

    private String num1;
    private String num2;

    private int numI1;
    private int numI2;
    public String execute() throws Exception{
        if(getSum()>0){
            return "positive";
        }else{
            return "negative";
        }
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public int getSum(){
        return numI1+numI2;
    }

    //加入validate方法
    public void validate(){
        try{
            numI1=Integer.valueOf(num1);
        }catch(Exception e){
            addFieldError("num1","num1 is not a number");
        }
        try{
            numI2=Integer.valueOf(num2);
        }catch(Exception e){
            addFieldError("num2","num2 is not a number");
        }
    }
}
