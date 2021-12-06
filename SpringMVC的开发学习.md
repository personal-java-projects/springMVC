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

#### 6、请求数据乱码问题

当post请求时，数据会出现乱码，我们可以设置一个过滤器来进行编码过滤。

```xml
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### 7、参数绑定注解@RequestParam

当请求的参数名称和Controller的业务方法参数名称不一致时，就需要通过@RequestParam注解显式的绑定。

```javascript
<form action="${pageContext.request.contextPath}/user/quick15" method="post">
    <input type="text" name="name"><br />
    <button type="submit">提交</button>
</form>
```

```java
@RequestMapping("/quick15")
@ResponseBody
public void save16(@RequestParam(value = "name") String username) {
    System.out.println(username);
}
```

```apl
http://localhost:8080/SpringMVC_war_exploded/user/quick15?name=猎人吗
```

注解@RequestParam还有如下参数可以使用：

* value: 请求参数名称
* required: 在指定的请求参数是否必须包括，默认是true，提交时如果没有对应的参数则会报错。
* defaultValue: 当没有指定请求参数时，则使用指定的默认值赋值

```java
@RequestMapping("/quick15")
@ResponseBody
public void save16(@RequestParam(value = "name", required=false, defaultValue="鹅城警官") String username) {
    System.out.println(username);
}
```

#### 8、获得Restful风格的参数

restful是一种软件架构风格、设计风格，而不是标准，只是提供了一组设计原则和约束条件。它主要用于客户端和服务器交互类的软件。基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。

Restful风格的请求是使用"url+请求方式"表示一次请求的目的的，HTTP协议里的四个表示操作方式的动词如下：

* GET：用于获取资源
* POST：用于新建资源
* PUT：用于更新资源
* DELETE：用于删除资源

例如：

* /user/1 GET：得到id=1的user
* /user/1 DELETE：删除id=1的user
* /user/1 PUT：更新id=1的user
* /user POST：新增user

上述url地址/user/1中的1就是要获得的请求参数，在springMVC中可以使用占位符进行参数绑定。地址`/user/1`可以写成`/user/{id}`，占位符{id}对应的就是1的值。在业务方法中我们可以使用`@PathVariable`注解进行占位符的匹配获取工作。

```apl
http://localhost:8080/SpringMVC_war_exploded/user/quick15/{张三}
```

```java
@RequestMapping("/quick16/{name}")
@ResponseBody
public void save17(@PathVariable(value = "name", required = true) String username) {
    System.out.println(username);
}
```

#### 9、自定义类型转换器

* SpringMVC默认已经提供了一些常用的类型转换器，例如客户端提交的字符串转换成int型进行参数设置。
* 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据就需要自定义转换器。

自定义类型转换器的开发步骤：

1. 定义转换器类实现Converter接口
2. 在spring-mvc.xml配置文件中声明转换器 
3. 在<annotation-driven>中引用转换器

```java
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String dateStr) {
        // 将日期字符串转换成真正的日期对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
```

```xml
<!-- mvc的注解驱动 -->
<mvc:annotation-driven conversion-service="conversionService" />

<!-- 声明Date转换器 -->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <property name="converters">
        <list>
            <bean class="com.example.converter.DateConverter"></bean>
        </list>
    </property>
</bean>

```

#### 10、获得Servlet相关API

springMVC支持使用原始ServletAPI对象作为控制器方法的参数进行注入，常用的对象如下：

* HttpServletRequest
* HttpServletResponse
* HttpSession

```java
@RequestMapping(value = "/quick18")
@ResponseBody
public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
   System.out.println(request);
   System.out.println(response);
   System.out.println(session);
}
```

#### 11、获取请求头

1. **@RequestHeader**

   使用**@RequestHeader**可以获得请求头信息，相当于web阶段学习的request.getHeader(name)

   @RequestHeader注解的属性如下：

   * value：请求头的名称
   * required：是否必须携带此请求头

   ```java
   @RequestMapping(value = "/quick19")
   @ResponseBody
   public void save20(@RequestHeader(value = "User-Agent", required = false) String headerValue) {
       System.out.println(headerValue);
   }
   ```

2. **@CookieValue**

   使用**@CookieValue**可以获得指定cookie的值。

   @CookieValue注解的属性如下：

   * value：指定cookie的名称
   * required：是否必须携带此cookie

   ```java
   Cookie: JSESSIONID=13F1A3B1FF678E3CC6D84D9EBB5D2864
   ```

   ```java
   @RequestMapping(value = "/quick20")
   @ResponseBody
   public void save21(@CookieValue(value = "JSESSIONID", required = false) String cookie) {
       System.out.println(cookie);
   }
   ```

#### 12、文件上传

1. **文件上传客户端三要素**

   * 表单项type="file"
   * 表单的提交方式是post
   * 表单的enctype属性是多部分表单形式，及enctype="multipart/form-data"

2. **文件上传原理**

   * 当form表单修改为多部分表单时，request.getParameter()将失效。

   * enctype="application/x-www-form-urlencoded"时，form表单的正文内容格式是：

     **key=value&key=value&key=value**

   * 当form表单的enctype取值为multipart/form-data时，请求正文内容就变成多部分形式：

     ```html
     <FORM method="POST" action="http://w.sohu.com/t2/upload.do" enctype="multipart/form-data">
     <INPUT type="text" name="city" value="Santa colo">
     <INPUT type="text" name="desc">
     <INPUT type="file" name="pic">
     </FORM>
     ```

     ```kotlin
     POST /t2/upload.do HTTP/1.1
     User-Agent: SOHUWapRebot
     Accept-Language: zh-cn,zh;q=0.5
     Accept-Charset: GBK,utf-8;q=0.7,*;q=0.7
     Connection: keep-alive
     Content-Length: 60408
     Content-Type:multipart/form-data; boundary=ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC
     Host: w.sohu.com
     
     --ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC
     Content-Disposition: form-data; name="city"
     
     Santa colo
     --ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC
     Content-Disposition: form-data;name="desc"
     Content-Type: text/plain; charset=UTF-8
     Content-Transfer-Encoding: 8bit
      
     ...
     --ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC
     Content-Disposition: form-data;name="pic"; filename="photo.jpg"
     Content-Type: application/octet-stream
     Content-Transfer-Encoding: binary
      
     ... binary data of the jpg ...
     --ZnGpDtePMx0KrHh_G0X99Yef9r8JZsRJSXC--
     ```

#### 13、单文件上传的步骤

1. 导入fileupload和io依赖

   ```xml
   <dependency>
     <groupId>commons-fileupload</groupId>
     <artifactId>commons-fileupload</artifactId>
     <version>1.2.2</version>
   </dependency>
   <dependency>
     <groupId>commons-io</groupId>
     <artifactId>commons-io</artifactId>
     <version>2.4</version>
   </dependency>
   ```

2. 配置文件上传解析器

   ```xml
   <!-- 配置上传文件解析器 -->
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
       <!-- 上传文件总大小 -->
       <property name="maxUploadSize" value="5242800"></property>
       <!-- 上传单个文件的大小 -->
       <property name="maxUploadSizePerFile" value="5242800"></property>
       <!-- 上传文件的编码类型 -->
       <property name="defaultEncoding" value="UTF-8"></property>
   </bean>
   ```

3. 编写文件上传代码

   ```java
   @RequestMapping(value = "/upload")
   @ResponseBody
   public void upload(String name, MultipartFile uploadFile, MultipartFile uploadFile2) throws IOException {
       // 获得文件名称
       String originalFilename = uploadFile.getOriginalFilename();
       // 保存文件
       uploadFile.transferTo(new File("c:\\upload\\"+originalFilename));
       
       // 获得文件名称
       String originalFilename2 = uploadFile2.getOriginalFilename();
       // 保存文件
       uploadFile2.transferTo(new File("c:\\upload\\"+originalFilename2));
   }
   ```

```jsp
<form action="${pageContext.servletContext.contextPath}/user/upload" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="name"><br />
    文件：<input type="file" name="uploadFile"><br />
    文件2：<input type="file" name="uploadFile2"><br />
    <button type="submit">提交</button>
</form>
```

#### 14、多文件上传

多文件上传，只需要将页面改成多个文件上传项，将方法参数MultipartFile类型改成MultipartFile[]即可。

```
<form action="${pageContext.servletContext.contextPath}/user/uploadFiles" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="name"><br />
    文件1：<input type="file" name="uploadFiles"><br />
    文件2：<input type="file" name="uploadFiles"><br />
    文件3：<input type="file" name="uploadFiles"><br />
    <button type="submit">提交</button>
</form>
```

```java
@RequestMapping(value = "/uploadFiles")
@ResponseBody
public void upload(String name, MultipartFile[] uploadFiles) throws IOException {
    for (MultipartFile uploadFile : uploadFiles) {
        // 获得文件名称
        String originalFilename = uploadFile.getOriginalFilename();
        // 保存文件, 我已经在d盘根目录新建upload文件夹
        uploadFile.transferTo(new File("d:\\upload\\"+originalFilename));
    }
}
```

## Spring JdbcTemplate基本使用

#### 1、JdbcTemplate概述

它是spring框架中提供的一个对象，是对原始繁琐的Jdbc API对象的简单封装。spring框架为我们提供了很多的操作模板类。例如：操作关系型数据的JdbcTemplate和HibernateTemplate，操作nosql数据库的RedisTemplate，操作消息队列的JmsTemplate等等。

#### 2、JdbcTemplate的开发步骤

1. 导入spring-jdbc和spring-tx(事务)依赖
2. 创建数据库表和实体
3. 创建JdbcTemplate对象
4. 执行数据库操作

```java
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
```

#### 3、Spring产生JdbcTemplate对象

我们可以将JdbcTemplate对象的创建权交给Spring，将数据源DataSource的创建权也交给Spring，在Spring容器内部将数据源DataSource注入到JdbcTemplate模板对象中，配置如下：

```xml
<!-- 创建c3p0 数据源对象 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test"></property>
    <property name="user" value="root"></property>
    <property name="password" value="root"></property>
</bean>

<!-- 创建JdbcTemplate对象，注入c3p0 -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

#### 4、JdbcTemplate的常用操作

1. 修改操作

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)
   @ContextConfiguration("classpath:applicationContext.xml")
   // SpringConfiguration配置类的形式配置Spring容器
   public class JdbcTemplateCRUDTest {
   
       @Autowired
       private JdbcTemplate jdbcTemplate;
   
       @Test
       public void testUpdate() {
       	jdbcTemplate.update("update account set money=?where name=?", 1000, "tom")
       }
   }
   ```

2. 删除操作

   ```java
   @Test
       public void testDelete() {
           // double类型的必须指定和数据库中小数点位数一样的小数
           jdbcTemplate.update("delete from account where name=?", "tom");
       }
   ```

3. 单个查询操作

   ```java
   @Test
   public void testQueryOne() {
       try {
           Account account = jdbcTemplate.queryForObject("select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class), "zhangsan");
   
           System.out.println(account);
       } catch (Exception e) {
           System.out.println(e);
       }
   }
   ```

4. 批量查询操作

   ```java
   @Test
   public void testQueryAll() {
       List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
   
       System.out.println(accountList);
   }@Test
   public void testQueryAll() {
       List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
   
       System.out.println(accountList);
   }
   ```

5. 查询总数操作

   ```java
   @Test
   public void testQueryCount() {
      Long count = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
   
       System.out.println(count);
   }
   ```

   