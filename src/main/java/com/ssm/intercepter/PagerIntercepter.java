package com.ssm.intercepter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.common.pager.SystemContext;
import com.ssm.common.utils.CommonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 处理分页拦截器
 * <p>
 * 前台封装的分页条件数据格式如下：
 * options {"search":{},"sort":{},"page":{"totalCount":180,"currentPage":1,"pageSize":5}}
 * <p>
 * Created with IntelliJ IDEA.
 * User: zhaomengjie
 * Date: 2017/1/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class PagerIntercepter extends WebMvcConfigurerAdapter {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor = new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Integer curPage = 0;
                Integer pageSize = 15;

                String optionsMap = request.getParameter("options");
                if (CommonUtil.isEmpty(optionsMap))
                    return true;

                JSONObject optionJson = JSON.parseObject(optionsMap);
                JSONObject pageJson = (JSONObject) optionJson.get("page");
                JSONObject sortJson = (JSONObject) optionJson.get("sort");
                JSONObject columnJson = (JSONObject) optionJson.get("column");

                if (pageJson != null) {
                    curPage = (Integer) pageJson.get("currentPage");
                    pageSize = (Integer) pageJson.get("pageSize");
                    SystemContext.setCurrentPage(curPage);
                    SystemContext.setPageSize(pageSize);
                }

                if (sortJson != null) {
                    String order = (String) sortJson.get("name");
                    String sort = (String) sortJson.get("order");
                    SystemContext.setOrder(order);
                    SystemContext.setSort(sort);
                }

                if (columnJson != null) {
                    Map columnMap = JSON.parseObject(columnJson.toJSONString());
                    SystemContext.setColumnMap(columnMap);
                }

                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                SystemContext.removeCurrentPage();
                SystemContext.removePageSize();
                SystemContext.removeOrder();
                SystemContext.removeSort();
            }
        };
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
