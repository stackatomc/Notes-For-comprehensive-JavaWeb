package model;

/**
 * @author MayerFang
 * @file BankService
 * @Description
 * @date 2018/10/1
 */
public class BankService {

    private int bankServiceID;
    private String serviceName="";

    public int getBankServiceID() {
        return bankServiceID;
    }

    public void setBankServiceID(int bankServiceID) {
        this.bankServiceID = bankServiceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
