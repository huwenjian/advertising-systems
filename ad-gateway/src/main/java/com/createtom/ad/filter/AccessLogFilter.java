package com.createtom.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * By  : HuWenJ
 * Date: 2019/1/24 13:29
 * Description:
 * Modify:
 *
 * @author huwenjian
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {
    Logger logger;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Long startTime = (Long) requestContext.get("startTime");
        String url = request.getRequestURI();
        long duration = System.currentTimeMillis() - startTime;
        logger.info("uri: " + url + ", duration: " + duration / 100 + "ms");

        return null;
    }
}