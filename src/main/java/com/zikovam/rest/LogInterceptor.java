package com.zikovam.rest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@PropertySource ("classpath:config.properties")
public class LogInterceptor implements HandlerInterceptor {
    private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();

    @Autowired
    private Environment env;

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        long startTime = System.currentTimeMillis();

        log.info("Request URL::" + httpServletRequest.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());

        httpServletRequest.setAttribute("startTime", startTime);
        //if returned false, we need to make sure 'response' is sent
        return true;
    }

    @Override
    public void postHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        log.info("Method executed");
    }

    @Override
    public void afterCompletion (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long startTime = (Long) httpServletRequest.getAttribute("startTime");

        log.info("Request URL::" + httpServletRequest.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
        log.info("Request URL::" + httpServletRequest.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));

        //getting properties for delay
        int limit = Integer.parseInt(env.getProperty("com.zikovam.constant.changesLimit"));

        if (individualsMockedData.getChangeChecker() == limit) {
            log.info("We reached the limit of changes, starting delay");
            //setting delay for a fewer time than in the task, you could change it
            int delay = Integer.parseInt(env.getProperty("com.zikovam.constant.delay"));
            Thread.sleep(delay);
            individualsMockedData.resetChangeChecker();
            log.info("Completed task");
        }
    }
}
