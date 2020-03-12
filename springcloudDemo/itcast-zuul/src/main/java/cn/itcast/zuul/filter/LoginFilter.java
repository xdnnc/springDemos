package cn.itcast.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //初始化context上下文对象
        RequestContext Context = RequestContext.getCurrentContext();
        HttpServletRequest request = Context.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){  //没有token说明没有权限
            //拦截 不转发请求
            Context.setSendZuulResponse(false);
            //响应状态码
            Context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);//未认证
            Context.setResponseBody("error");
        }
        //返回值null,代表什么都不做
        return null;
    }
}
