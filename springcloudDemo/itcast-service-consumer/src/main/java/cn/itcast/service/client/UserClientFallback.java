package cn.itcast.service.client;

import cn.itcast.service.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class UserClientFallback implements UserClient{
    @Override
    @ResponseBody
    public User queryUserById(Long id) {
        return new User();
    }
}
