package cn.itcast.service.controller;

import cn.itcast.service.client.UserClient;
import cn.itcast.service.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("consumer/user")
//@DefaultProperties(defaultFallback = "queryUserByIdFallBack")//全局熔断方法
public class UserController {

    @Autowired
    private UserClient userClient;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping
    @ResponseBody
    @HystrixCommand
    public User queryUserById(@RequestParam("id") Long id){
////        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
////        ServiceInstance instance = instances.get(0);
//        return this.restTemplate.getForObject("http://service-provider/user/"+id, User.class);
        return this.userClient.queryUserById(id);
    }

//    @ResponseBody
//    public User queryUserByIdFallBack(){
//        return new User();
//    }
}
