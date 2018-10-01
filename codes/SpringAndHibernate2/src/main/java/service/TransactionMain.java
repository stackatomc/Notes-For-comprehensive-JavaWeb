package service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author MayerFang
 * @file TransactionMain
 * @Description
 * @date 2018/10/1
 */
public class TransactionMain {

    public JdbcTemplate jdbcTemplate;


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void updateUsers(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        jdbcTemplate=(JdbcTemplate)context.getBean("jdbcTemplate");
        String sql="INSERT INTO userInfo VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,"3","Mike","123","VIP");
        jdbcTemplate.update(sql,"2","Peter","456","VIP");
    }

    public static void main(String[] args) {

        try{
            TransactionMain m=new TransactionMain();
            m.updateUsers();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
