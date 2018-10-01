package model;

import java.util.List;

/**
 * @author MayerFang
 * @file Account
 * @Description
 * @date 2018/10/1
 */
public class Account {

    private int accountID;
    private String name;
    private String password;
    private Card card;
    private List<BankService> bankServices;

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<BankService> getBankServices() {
        return bankServices;
    }

    public void setBankServices(List<BankService> bankServices) {
        this.bankServices = bankServices;
    }
}
