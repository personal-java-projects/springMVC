package com.example.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class SpringJunitTest {
    @Test
    public void testJdbcTemplate() throws PropertyVetoException, SQLException {
        // 创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        // 设置数据源对象，知道数据库在哪儿
        jdbcTemplate.setDataSource(dataSource);

        // 执行操作
        int row = jdbcTemplate.update("insert into account values(?, ?)", "tom", 29.22);
        System.out.println(row);
    }

    @Test
    public void testSpringGenerateJdbcTemplate() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");

        // 执行操作
        int row = jdbcTemplate.update("insert into account values(?, ?)", "zhangsan", 29.22);
        System.out.println(row);
    }
}
