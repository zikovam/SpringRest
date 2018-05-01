package com.zikovam.rest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();

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

        if (individualsMockedData.getChangeChecker() == 4){
            log.info("We reached the limit of changes, starting delay");
            //setting delay for a fewer time than in the task, you could change it
            Thread.sleep(6000);
            individualsMockedData.resetChangeChecker();
            log.info("Completed task");
        }
    }
}
