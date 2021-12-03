package com.example.test;

import com.example.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testQueryAll() {
        List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));

        System.out.println(accountList);
    }

    @Test
    public void testQueryOne() {
        try {
            Account account = jdbcTemplate.queryForObject("select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class), "zhangsan");

            System.out.println(account);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testQueryCount() {
       Long count = jdbcTemplate.queryForObject("select count(*) from account", Long.class);

        System.out.println(count);
    }

    @Test
    public void testUpdate() {
        // double类型的必须指定和数据库中小数点位数一样的小数
        jdbcTemplate.update("update account set money=?where name=?", 1000.00, "tom");
    }

    @Test
    public void testDelete() {
        // double类型的必须指定和数据库中小数点位数一样的小数
        jdbcTemplate.update("delete from account where name=?", "tom");
    }
}
