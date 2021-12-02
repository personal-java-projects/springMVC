package com.example.controller;

import com.example.pojo.User;
import com.example.pojo.VO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    // 请求映射，前端可通过该映射访问该逻辑，就是前端所说的接口
    // 访问：拼接/user/quick
    // http://localhost:8080/SpringMVC_war_exploded/user/quick
    @RequestMapping(value = "/quick", method = RequestMethod.GET, params = {"username"})
    public String save() {
        System.out.println("controller save running ....");
// 这里return的是你要跳转的视图，不加，会根据相对路径找资源，
// 这里相对于user,而success.jsp存在于webapp下面，所以找不到
//        return "success.jsp";
        // 加/表示从当前web应用下面找资源
        // 这里默认省略了：forward:（转发功能）
        // 这样写，因为在spring-mvc.xml中配置了前缀和后缀
        return "success";
        // redirect: （重定向功能）
//        return "redirect:success";
    }

    @RequestMapping("/quick1")
    public ModelAndView save2() {
        /**
         * Model: 模型，用来封装数据
         * View：视图，用来展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        // 设置模型数据
        modelAndView.addObject("username", "itcast");
        // 设置视图名称
        modelAndView.setViewName("success");

        return  modelAndView;
    }

    @RequestMapping("/quick2")
    public ModelAndView save3(ModelAndView modelAndView) {
        // 设置模型数据
        modelAndView.addObject("username", "发的空间的");
        // 设置视图名称
        modelAndView.setViewName("success");

        return  modelAndView;
    }

    @RequestMapping("/quick3")
    public String save4(Model model) {
        model.addAttribute("username", "你好好呀？");

        return "success";
    }

    @RequestMapping("/quick4")
    public String save5(HttpServletRequest request) {
        request.setAttribute("username", "飞机扩大刷卡积分");
        return "success";
    }

    @RequestMapping("/quick5")
    public void save6(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello????");
    }

    @RequestMapping("/quick6")
    @ResponseBody // 告知springMVC框架，该方法不进行视图跳转，而是直接回写数据
    public String save7() {
        return "葫芦娃救爷爷";
    }

    @RequestMapping("/quick7")
    @ResponseBody // 告知springMVC框架，该方法不进行视图跳转，而是直接回写数据
    public String save8() {
        return "{\"username\":\"张三\", \"age\":14}";
    }

    @RequestMapping("/quick8")
    @ResponseBody // 告知springMVC框架，该方法不进行视图跳转，而是直接回写数据
    public String save9() throws JsonProcessingException {
        User user = new User();

        user.setUsername("历史");
        user.setAge(10);

        // 使用json的转换工具将json转换成字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return json;
    }

    @RequestMapping("/quick9")
    @ResponseBody // 告知springMVC框架，该方法不进行视图跳转，而是直接回写数据
    // 期望springMVC自动将User转换成json格式的字符串
    public User save10() throws JsonProcessingException {
        User user = new User();

        user.setUsername("fri");
        user.setAge(100);

        return user;
    }

    @RequestMapping("/quick10")
    @ResponseBody
    public void save11(String username, int age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping("/quick11")
    @ResponseBody
    public void save12(User user) throws IOException {
        System.out.println(user);
    }

    @RequestMapping("/quick12")
    @ResponseBody
    public void save13(String[] str) throws IOException {
        System.out.println(Arrays.asList(str));
    }

    @RequestMapping("/quick13")
    @ResponseBody
    public void save14(VO vo) throws IOException {
        System.out.println(Arrays.asList(vo));
    }

    @RequestMapping("/quick14")
    @ResponseBody
    public void save14(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }
}