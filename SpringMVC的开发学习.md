## Spring集成Web环境
#### 1、ApplicationContext应用上下文获取方式
应用上下文是通过new ClassPathXmlApplicationContext(配置文件)的方式获取的，但是每次从容器中获取Bean的时候都需要编写new ClassPathXmlApplicationContext(配置文件)，这样的弊端是配置文件加载多次，应用上下文对象创建多次。

在web项目中，可以使用ServletContextListener监听web应用的启动，我们可以在web应用启动时，就加载spring的配置文件，创建应用上下文对象ApplicationContext,再将其存储到最大的域servletContext域中，这样就可以在任意位置从域中获得应用上下文ApplicationContext对象了。

## SpringMVC的简介

#### 1、SpringMVC的概述

SpringMVC是一种基于Java的实现MVC设计模型的请求驱动类型的轻量级web框架，属于SpringFramework的后续产品，已经融合在Spring Web Flow中了。

**SpringMVC现在已经成为目前最主流的MVC框架之一，并随着Spring的一直优化更新，已经将Struts2框架淘汰了，继而成为最优秀的MVC框架。SpringMVC通过一套注解，让一个简单的Java类成为处理请求的控制器，而无需实现任何接口。同时它还支持RESTful编程风格的请求**

#### 2、SpringMVC快速入门

需求：客户端发起请求，服务端接收请求，执行逻辑并进行试图跳转。

开发步骤：

1. 导入SpringMVC相关依赖
2. 配置SpringMVC核心控制器DispatcherServlet
3. 创建Controller类和视图页面
4. 使用注解配置Controller类中业务方法的映射地址。
5. 配置springMVC核心配置文件spring-mvc.xml
6. 客户端发请求测试

#### 3、springMVC的流程图示

![在这里插入图片描述](https://img-blog.csdnimg.cn/bb38375c69f9485b8075e11d59b0fb5c.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5bCP546L55qE6L-b6Zi25LmL6Lev,size_20,color_FFFFFF,t_70,g_se,x_16)

#### 4、知识要点

**springMVC开发步骤**

1. 导入SpringMVC相关依赖
2. 配置SpringMVC核心控制器DispatcherServlet
3. 创建Controller类和视图页面
4. 使用注解配置Controller类中业务方法的映射地址。
5. 配置springMVC核心配置文件spring-mvc.xml
6. 客户端发请求测试

## springMVC的组件解析

#### 1、springMVC的执行流程

1. 客户端发送请求至前端控制器DispatcherServlet
2. DispatcherServlet收到请求调用HandlerMapping处理器映射器
3. 处理器映射器找到具体的处理器（可以根据xml配置、注解进行查找），生成处理器对象及处理器拦截器（如果有则生成）一并返回给DispatcherServlet
4. DispatcherServlet调用HandlerAdapter处理器适配器
5. HandlerAdapter经过适配器调用具体的处理器（Controller，也叫后端控制器）
6. Controller执行完成返回ModelAndView
7. HandlerAdapter将Controller执行结果ModelAndView返回给DispatcherServlet
8. DispatcherServlet将ModelAndView传给ViewReslover视图解析器
9. ViewReslover解析后返回具体View
10. DispatcherServlet根据View进行视图渲染（即将模型数据填充到视图中）。DispatcherServlet响应用户

#### 2、springMVC注解解析

@RequestMapping: 请求映射

作用：用于建立请求URL和处理方法之间的对应关系

位置：

* 类上，请求URL的第一级访问目录。此处不写的话，就相当于应用的根目录。
* 方法上，请求URL的第二级访问目录，与类上的使用＠RequestMapping标注的一级目录一起组成访问虚拟路径

属性：

* value：用于指定请求的URL。它和path属性的作用是一样的
* method：用于指定请求的方法
* params：用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数key和value必须和配置的一模一样

例如：

* params = { “accountName” }：表示请求参数必须有accountName
* params = { "money!100" }：表示请求中的money不能是100，这里用了表达式

#### 3、springMVC的xml配置解析

#### 4、知识要点

**springMVC的相关组件**

* 前端控制器：DispatcherServlet
* 处理器映射器：HandlerMapping
* 处理器适配器：HandlerAdapter
* 处理器：Handler
* 视图解析器：ViewReslover
* 视图：View

**SpringMVC的注解和配置**

* 请求映射注解：@RequestMapping

* 视图解析器配置：

  ```java
  REDIRECT_URL_PREFIX = "redirect:"
  FORWARD_URL_PREFIX = "forward:"
  String prefix = ""
  String suffix = "
  ```

## SpringMVC的数据响应

#### 1、SpringMVC的数据响应方式

1. 页面跳转
   * 直接返回字符串
   * 通过ModelAndView对象返回
2. 回写数据
   * 直接返回字符串
   * 返回对象或集合

#### 2、页面跳转

1. **返回字符串：**

   直接返回字符串：此种方式会将返回的字符串与视图解析器的前后缀进行拼接后跳转。

   ```java
   <bean id="viewReslover" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       <property name="prefix" value="/WEB-INF/view/"></property>
       <property name="suffix" value=".jsp"></property>
   </bean>
   ```

   ```java
   @RequestMapping(value="/quick", method = RequestMethod.GET, params = {"username"})
   public String save() {
       System.out.println("controller save running ....");
       return "success";
   }
   ```

   上面两段合起来的资源url: /WEB-INF/view/success.jsp

   

   返回带有前缀的字符串：

   转发：`forward:/WEB-INF/success.jsp`

   重定向：`redirect:/success.jsp`

   

2. **返回ModelAndView对象：**

#### 3、回写数据

​	1、**直接返回字符串：**

​			web基础阶段，客户端访问服务器端，如果想直接回写字符串作为响应体返回的话，只需要使用				`response.getWriter().print("hello world")`即可，那么在Controller中想直接回写字符串该怎样呢？

 - 通过springMVC框架注入的response对象，使用`response.getWriter().print("hello world")`回写数据，此时不需要视图跳转，业务方法返回值为void。

 - 将需要回写的字符串直接返回，但此时需要通过@ResponseBody注解告知，springMVC框架，方法返回的字符串不是跳转而是直接在http响应体中返回

   ```java
   @RequestMapping("/quick6")
   @ResponseBody
   public String save7() {
       return "葫芦娃救爷爷";
   }
   ```

   2.  **返回对象或集合：**

      ```java
      <!-- 配置处理器适配器, 使得方法返回的对象或集合能通过jackson自动转换成json格式的字符串 -->
      <bean id="requestMappingHandlerAdapter" class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
          <property name="messageConverters">
              <list>
                  <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
              </list>
          </property>
      </bean>
      ```

      

      在方法上添加@ResponseBody注解就可以返回一个json格式的字符串，但这样配置比较麻烦，配置的代码比较多，因此，我们可以使用mvc的注解驱动代替上述配置。

      beans中需要添加

      ```xml-dtd
      xmlns:mvc="http://www.springframework.org/schema/mvc"
      ```

      xsi:schemaLocation后面需要追加：

      ```xml-dtd
      http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
      ```

      

      ```java
      <!-- mvc的注解驱动 -->
      <mvc:annotation-driven />
      ```

      在springmvc的各个组件中，处理器映射器、处理器适配器、视图解析器被称作springMVC的三大组件。使用`<mvc:annotation-driven />`自动加载RequestMappingHandlerMapping(处理器映射器)和RequestMappingHandlerAdapter(处理器适配器)，可在spring-mvc.xml配置文件中使用<mvc:annotation-driven />替代注解处理器和适配器的配置。同时，使用<mvc:annotation-driven />默认底层就会集成jackson进行对象或集合的json格式的字符串转换。

#### 4、知识要点

**SpringMVC的数据响应方式**

1. 页面跳转
   * 直接返回字符串
   * 通过ModelAndView对象返回
2. 回写数据
   * 直接返回字符串
   * 返回对象或集合

## SpringMVC获得请求数据

#### 1、获得请求参数

客户端请求参数的格式：name=value&name=value...

服务器端要获得请求的参数，有时还需要进行数据的封装，SpringMVC可以接收如下类型的参数：

* 基本类型的参数
* POJO类型的参数
* 数组类型的参数
* 集合类型的参数

#### 2、获得基本类型参数

Controller中的业务方法的参数名称要与请求参数的name一致，参数值会自动映射匹配。

```apl
http://localhost:8080/SpringMVC_war_exploded/user/quick?username=123&age=55
```

```java
@RequestMapping("/quick9")
@ResponseBody
public void save10(String username, int age) throws IOException {
    System.out.println(username);
    System.out.println(age);
}
```

#### 3、获得POJO类型的参数

Controller中的业务方法的POJO参数的属性名与请求参数的name一致，参数值会自动映射匹配。

```apl
http://localhost:8080/SpringMVC_war_exploded/user/quick10?username=zhangsan&age=19
```

```java
public class User {
    private String username;
    private int age;
    getter/setter
}

@RequestMapping("/quick11")
@ResponseBody
public void save11(User user) throws IOException {
    System.out.println(user);
}
```

#### 4、获得数组类型的参数

Controller中的业务方法的数组名与请求参数的name一致，参数值会自动映射匹配。

```apl
http://localhost:8080/SpringMVC_war_exploded/user/quick12?str=111&str=222&str=333
```

```java
@RequestMapping("/quick12")
@ResponseBody
public void save13(String[] str) throws IOException {
    System.out.println(Arrays.asList(str));
}
```

#### 5、获得集合类型的参数

1.情况一：获得集合参数时，要将集合参数包装到一个POJO中才可以。

```java
public class VO {
    private List<User> userList;

    getter/setter
}

@RequestMapping("/quick13")
@ResponseBody
public void save14(VO vo) throws IOException {
    System.out.println(Arrays.asList(vo));
}
```

2.情况二：当使用ajax提交时，可以指定contentType为json形式时，那么在方法参数位置使用@RequestBody可以直接接收集合数据而无需使用POJO进行包装。

```java
@RequestMapping("/quick14")
@ResponseBody
public void save14(@RequestBody List<User> userList) throws IOException {
    System.out.println(userList);
}

<script>
    var userList = new Array();
    userList.push({
        username: '张三',
        age: 18
    }, {
        username: '李四',
        age: 11
    })

    $.ajax({
        method: 'POST',
        url: "${pageContext.request.contextPath}/user/quick14",
        data: JSON.stringify(userList),
        contentType: "application/json;charset=utf-8"
    })
</script>
```

