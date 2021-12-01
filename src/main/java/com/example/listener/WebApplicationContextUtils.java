package com.example.listener;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

public class WebApplicationContextUtils {

    // 这里是避免每次都要去写应用上下文存储在servletContext域中的键名，增加维护成本
    // 统一每次返回固定的
    public static ApplicationContext getWebApplicationContext(ServletContext servletContext) {
        return (ApplicationContext) servletContext.getAttribute("app");
    }
}
