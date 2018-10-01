package service;

import model.Account;
import model.BankService;
import model.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author MayerFang
 * @file HibernateMain
 * @Description
 * @date 2018/10/1
 */
public class HibernateMain {


    public static void main(String[] args) throws InterruptedException {
        StandardServiceRegistry registry=null;
        SessionFactory sessionFactory=null;
        Transaction tx=null;

        try{

            registry=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();

        }catch(Exception e){

            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);

        }

        Session session=sessionFactory.openSession();
        tx=session.beginTransaction();

//        Card card=new Card();
//        card.setCardID(002);
//        card.setAccID(002);
//        card.setType("TypeA");
//        card.setBalance(200f);

        List<BankService> bankServiceList=new ArrayList<BankService>();

        BankService bankService1=new BankService();
        bankService1.setBankServiceID(17);
        bankService1.setServiceName("Service017");

        BankService bankService2=new BankService();
        bankService1.setBankServiceID(18);
        bankService1.setServiceName("Service018");

        BankService bankService3=new BankService();
        bankService1.setBankServiceID(19);
        bankService1.setServiceName("Service019");

        bankServiceList.add(bankService1);
        bankServiceList.add(bankService2);
        bankServiceList.add(bankService3);




        Account account=new Account();
        account.setAccountID(13);
        account.setName("User013");
        account.setPassword("Pwd013");
//        account.setCard(card);
        account.setBankServices(bankServiceList);

        session.save(account);
        session.flush();
        tx.commit();

        tx=session.beginTransaction();

//        Account account2=(Account)session.load(Account.class,002);
//        Card oneCard= account2.getCard();
//        System.out.println(oneCard.getCardID()+"    "+oneCard.getBalance());

        Account account1=(Account) session.get(Account.class,13);
        List<BankService> bankServiceList2=account1.getBankServices();
        for(int i=0;i<bankServiceList2.size();++i){
            BankService b=bankServiceList2.get(i);
            System.out.println(b.getBankServiceID()+"   "+b.getServiceName());
        }

        tx.commit();
    }
}
